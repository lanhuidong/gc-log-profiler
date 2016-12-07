package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.Statistic;
import org.junit.Test;

import java.io.File;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class SimpleLogParserTest {

    @Test
    public void testParse() {
        File logFile = new File("/Users/lanhuidong/Documents/GCLogs/cms.log.2");
        SimpleLogParser parser = new SimpleLogParser();
        Statistic statistic = parser.parse(logFile);
        System.out.println(statistic.getParNewDatas().size());
    }
}
