package com.nexusy.glp.analyzer.impl;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;
import com.nexusy.glp.data.basic.ParNewData;

import java.util.List;
import java.util.Map;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class ParNewGCAnalyzer extends AbstractGCAnalyzer {

    @Override
    public HighLevelData analyze(HighLevelData highLevelData, BasicData basicDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        List<ParNewData> parNewDatas = basicDatas.getParNewDatas();
        if (parNewDatas != null) {
            int size = parNewDatas.size();
            highLevelData.setMinorGCTimes(size);
            long startTime = parNewDatas.get(0).getUptime();
            long endTime = parNewDatas.get(size - 1).getUptime();
            highLevelData.setMinorGCDuration(endTime - startTime);
            List<Long> promotionPerSec = highLevelData.getPromotionPerSec();
            int minorGCDuration = (int) ((endTime - startTime) / 1000);
            for (int i = 0; i < minorGCDuration; i++) {
                promotionPerSec.add(0L);
            }
            long promotionSize = 0;
            long promotionTimes = 0;
            Map<String, Integer> causeMap = highLevelData.getCauseMap();
            for (ParNewData data : parNewDatas) {
                long yongCleanSize = data.getYoungGenUsageBfGC() - data.getYoungGenUsageAfGC();
                long heapCleanSize = data.getHeapUsageBfGC() - data.getHeadUsageAfGC();
                if (yongCleanSize - heapCleanSize > 0) {
                    promotionSize += yongCleanSize - heapCleanSize;
                    promotionTimes++;
                    int sec = (int) ((data.getUptime() - startTime) / 1000);
                    promotionPerSec.set(sec, promotionPerSec.get(sec) + (yongCleanSize - heapCleanSize));
                }
                if (data.getStwDuration() > maxGCPause) {
                    maxGCPause = data.getStwDuration();
                }
                if (causeMap.containsKey(data.getCause())) {
                    causeMap.put(data.getCause(), causeMap.get(data.getCause()) + 1);
                } else {
                    causeMap.put(data.getCause(), 1);
                }
            }
            highLevelData.setMaxGCPause(maxGCPause);
            highLevelData.setPromotionSize(promotionSize);
            highLevelData.setPromotionTimes(promotionTimes);
        }
        return highLevelData;
    }

}
