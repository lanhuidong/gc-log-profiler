package com.nexusy.glp.analyzer;

import com.nexusy.glp.analyzer.impl.CMSGCAnalyzer;
import com.nexusy.glp.analyzer.impl.ParNewGCAnalyzer;
import com.nexusy.glp.analyzer.impl.ParallelGCAnalyzer;
import com.nexusy.glp.analyzer.impl.SerialGCAnalyzer;
import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class DefaultGCAnalyzer {

    private static List<GCLogAnalyzer> analyzers = new ArrayList<>();

    static {
        analyzers.add(new SerialGCAnalyzer());
        analyzers.add(new ParallelGCAnalyzer());
        analyzers.add(new ParNewGCAnalyzer());
        analyzers.add(new CMSGCAnalyzer());
    }

    public HighLevelData analyze(BasicData basicDatas) {
        HighLevelData data = new HighLevelData();
        for (GCLogAnalyzer analyzer : analyzers) {
            analyzer.analyze(data, basicDatas);
        }
        return data;
    }
}
