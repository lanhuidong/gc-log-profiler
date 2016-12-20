package com.nexusy.glp.analyzer.impl;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;
import com.nexusy.glp.data.basic.CMSFinalRemarkData;
import com.nexusy.glp.data.basic.CMSInitialMarkData;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class CMSGCAnalyzer extends AbstractGCAnalyzer {

    @Override
    public HighLevelData analyze(HighLevelData highLevelData, BasicData basicDatas) {
        double maxGCPause = highLevelData.getMaxGCPause();
        for (CMSInitialMarkData data : basicDatas.getCmsInitialMarkDatas()) {
            if (data.getStwDuration() > maxGCPause) {
                maxGCPause = data.getStwDuration();
            }
        }
        for (CMSFinalRemarkData data : basicDatas.getCmsFinalRemarkDatas()) {
            if (data.getStwDuration() > maxGCPause) {
                maxGCPause = data.getStwDuration();
            }
        }
        highLevelData.setMaxGCPause(maxGCPause);
        return highLevelData;
    }
}
