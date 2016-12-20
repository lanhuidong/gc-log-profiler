package com.nexusy.glp.parser.impl;

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
    public BasicData parse(File gcLogFile) {
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
        BasicData basicData = new BasicData();
        basicData.setSerialGCDatas(serialGCDatas);
        basicData.setParallelGCDatas(parallelGCDatas);
        basicData.setParNewDatas(parNewDatas);
        basicData.setCmsInitialMarkDatas(cmsInitialMarkDatas);
        basicData.setCmsConcurrentDatas(cmsConcurrentDatas);
        basicData.setCmsFinalRemarkDatas(cmsFinalRemarkDatas);
        return basicData;
    }

}
