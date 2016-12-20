package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.*;
import com.nexusy.glp.parser.GCLogParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class SimpleLogParser implements GCLogParser {

    private CMSConcurrentParser cmsConcParser = new CMSConcurrentParser();

    private CMSFinalRemarkParser cmsFinalRemarkParser = new CMSFinalRemarkParser();

    private CMSInitMarkParser cmsInitMarkParser = new CMSInitMarkParser();

    private ParNewParser parNewParser = new ParNewParser();

    private ParallelGCParser parallelGCParser = new ParallelGCParser();

    private SerialGCParser serialGCParser = new SerialGCParser();

    @Override
    public HighLevelData parse(File gcLogFile) {
        List<ParNewData> parNewDatas = null;
        List<CMSConcurrentData> cmsConcurrentDatas = null;
        List<CMSFinalRemarkData> cmsFinalRemarkDatas = null;
        List<CMSInitialMarkData> cmsInitialMarkDatas = null;
        List<ParallelGCData> parallelGCDatas = null;
        List<SerialGCData> serialGCDatas = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(gcLogFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ParNew:")) {
                    ParNewData data = parNewParser.parse(line);
                    if (parNewDatas == null) {
                        parNewDatas = new ArrayList<>();
                    }
                    parNewDatas.add(data);
                } else if (line.contains("CMS-concurrent-")) {
                    CMSConcurrentData data = cmsConcParser.parse(line);
                    if (cmsConcurrentDatas == null) {
                        cmsConcurrentDatas = new ArrayList<>();
                    }
                    cmsConcurrentDatas.add(data);
                } else if (line.contains("CMS-remark")) {
                    CMSFinalRemarkData data = cmsFinalRemarkParser.parse(line);
                    if (cmsFinalRemarkDatas == null) {
                        cmsFinalRemarkDatas = new ArrayList<>();
                    }
                    cmsFinalRemarkDatas.add(data);
                } else if (line.contains("CMS-initial-mark")) {
                    CMSInitialMarkData data = cmsInitMarkParser.parse(line);
                    if (cmsInitialMarkDatas == null) {
                        cmsInitialMarkDatas = new ArrayList<>();
                    }
                    cmsInitialMarkDatas.add(data);
                } else if (line.contains("PSYoungGen") || line.contains("ParOldGen")) {
                    ParallelGCData data = parallelGCParser.parse(line);
                    if (parallelGCDatas == null) {
                        parallelGCDatas = new ArrayList<>();
                    }
                    parallelGCDatas.add(data);
                } else if (line.contains("DefNew") || line.contains("Tenured")) {
                    SerialGCData data = serialGCParser.parse(line);
                    if (serialGCDatas == null) {
                        serialGCDatas = new ArrayList<>();
                    }
                    serialGCDatas.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        HighLevelData highLevelData = new HighLevelData();
        if (parNewDatas != null) {
            processParNewDatas(highLevelData, parNewDatas);
        }
        if (cmsInitialMarkDatas != null) {
            processCMSInitialMarkDatas(highLevelData, cmsInitialMarkDatas);
        }
        if (cmsFinalRemarkDatas != null) {
            processCMSFinalRemarkDatas(highLevelData, cmsFinalRemarkDatas);
        }
        if (parallelGCDatas != null) {
            processParallelGCDatas(highLevelData, parallelGCDatas);
        }
        if (serialGCDatas != null) {
            processSerialGCDatas(highLevelData, serialGCDatas);
        }
        return highLevelData;
    }

    private void processParNewDatas(HighLevelData highLevelData, List<ParNewData> parNewDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        int size = parNewDatas.size();
        highLevelData.setMinorGCTimes(size);
        long startTime = parNewDatas.get(0).getUptime();
        long endTime = parNewDatas.get(size - 1).getUptime();
        highLevelData.setMinorGCDuration(endTime - startTime);
        long promotionSize = 0;
        long promotionTimes = 0;
        for (ParNewData data : parNewDatas) {
            long yongCleanSize = data.getYoungGenUsageBfGC() - data.getYoungGenUsageAfGC();
            long heapCleanSize = data.getHeapUsageBfGC() - data.getHeadUsageAfGC();
            if (yongCleanSize - heapCleanSize > 0) {
                promotionSize += yongCleanSize - heapCleanSize;
                promotionTimes++;
            }
            if (data.getTotalDuration() > maxGCPause) {
                maxGCPause = data.getTotalDuration();
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setPromotionSize(promotionSize);
        highLevelData.setPromotionTimes(promotionTimes);
    }

    private void processCMSInitialMarkDatas(HighLevelData highLevelData, List<CMSInitialMarkData> cmsInitialMarkDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        for (CMSInitialMarkData data : cmsInitialMarkDatas) {
            if (data.getDuration() > maxGCPause) {
                maxGCPause = data.getDuration();
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
    }

    private void processCMSFinalRemarkDatas(HighLevelData highLevelData, List<CMSFinalRemarkData> cmsFinalRemarkDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        for (CMSFinalRemarkData data : cmsFinalRemarkDatas) {
            if (data.getDuration() > maxGCPause) {
                maxGCPause = data.getDuration();
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
    }

    private void processParallelGCDatas(HighLevelData highLevelData, List<ParallelGCData> parallelGCDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        long startTime = -1;
        long endTime = -1;
        long promotionSize = 0;
        long promotionTimes = 0;
        long minorGCTimes = 0;
        for (ParallelGCData data : parallelGCDatas) {
            if (data.getTotalDuration() > maxGCPause) {
                maxGCPause = data.getTotalDuration();
            }
            if (data.getOldSize() == 0) {  //Minor GC
                minorGCTimes++;
                if (startTime == -1) {
                    startTime = data.getUptime();
                }
                endTime = data.getUptime();
                long yongCleanSize = data.getYoungGenUsageBfGC() - data.getYoungGenUsageAfGC();
                long heapCleanSize = data.getHeapUsageBfGC() - data.getHeadUsageAfGC();
                if (yongCleanSize - heapCleanSize > 0) {
                    promotionSize += yongCleanSize - heapCleanSize;
                    promotionTimes++;
                }
                if (data.getTotalDuration() > maxGCPause) {
                    maxGCPause = data.getTotalDuration();
                }
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setMinorGCDuration(endTime - startTime);
        highLevelData.setPromotionSize(promotionSize);
        highLevelData.setPromotionTimes(promotionTimes);
        highLevelData.setMinorGCTimes(minorGCTimes);
    }

    private void processSerialGCDatas(HighLevelData highLevelData, List<SerialGCData> serialGCDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        long startTime = -1;
        long endTime = -1;
        long promotionSize = 0;
        long promotionTimes = 0;
        long minorGCTimes = 0;
        for (SerialGCData data : serialGCDatas) {
            if (data.getTotalDuration() > maxGCPause) {
                maxGCPause = data.getTotalDuration();
            }
            if (data.getOldSize() == 0) {  //Minor GC
                minorGCTimes++;
                if (startTime == -1) {
                    startTime = data.getUptime();
                }
                endTime = data.getUptime();
                long yongCleanSize = data.getYoungGenUsageBfGC() - data.getYoungGenUsageAfGC();
                long heapCleanSize = data.getHeapUsageBfGC() - data.getHeadUsageAfGC();
                if (yongCleanSize - heapCleanSize > 0) {
                    promotionSize += yongCleanSize - heapCleanSize;
                    promotionTimes++;
                }
                if (data.getTotalDuration() > maxGCPause) {
                    maxGCPause = data.getTotalDuration();
                }
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setMinorGCDuration(endTime - startTime);
        highLevelData.setPromotionSize(promotionSize);
        highLevelData.setPromotionTimes(promotionTimes);
        highLevelData.setMinorGCTimes(minorGCTimes);
    }

}
