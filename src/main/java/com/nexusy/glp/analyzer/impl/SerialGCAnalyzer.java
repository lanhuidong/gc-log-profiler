package com.nexusy.glp.analyzer.impl;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;
import com.nexusy.glp.data.basic.SerialGCData;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class SerialGCAnalyzer extends AbstractGCAnalyzer {

    @Override
    public HighLevelData analyze(HighLevelData highLevelData, BasicData basicDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        long startTime = -1;
        long endTime = -1;
        long promotionSize = 0;
        long promotionTimes = 0;
        long minorGCTimes = 0;
        for (SerialGCData data : basicDatas.getSerialGCDatas()) {
            if (data.getStwDuration() > maxGCPause) {
                maxGCPause = data.getStwDuration();
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
                if (data.getStwDuration() > maxGCPause) {
                    maxGCPause = data.getStwDuration();
                }
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setMaxGCPause(maxGCPause);
        highLevelData.setMinorGCDuration(endTime - startTime);
        highLevelData.setPromotionSize(promotionSize);
        highLevelData.setPromotionTimes(promotionTimes);
        highLevelData.setMinorGCTimes(minorGCTimes);
        return highLevelData;
    }
}
