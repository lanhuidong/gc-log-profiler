package com.nexusy.glp.analyzer;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public interface GCLogAnalyzer {

    HighLevelData analyze(HighLevelData data, BasicData basicDatas);

}
