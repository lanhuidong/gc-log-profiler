package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.parser.GCLogParser;
import org.junit.Test;

import java.io.File;

/**
 * @author lanhuidong
 * @since 2016-12-16
 */
public class SimpleLogParserTest {

    private GCLogParser parser = new SimpleLogParser();

    @Test
    public void test() {
        File file = new File("/Users/lanhuidong/Documents/GCLogs/cms.log.2");
        HighLevelData data = parser.parse(file);
        System.out.println(data.getMinorGCDuration() / 1000d / data.getMinorGCTimes());
        System.out.println(data.getMaxGCPause());
        System.out.println(data.getPromotionSize() / 1024d / data.getPromotionTimes());
        System.out.println(data.getPromotionSize() / 1024d / (data.getMinorGCDuration() / 1000d));
    }
}
