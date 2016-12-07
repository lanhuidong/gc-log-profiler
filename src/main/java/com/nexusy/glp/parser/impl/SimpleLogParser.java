package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.ParNewData;
import com.nexusy.glp.data.Statistic;
import com.nexusy.glp.parser.GCLogParser;

import java.io.*;
import java.util.ArrayList;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class SimpleLogParser implements GCLogParser {

    private ParNewParser parNewParser = new ParNewParser();

    @Override
    public Statistic parse(File gcLogFile) {
        Statistic statistic = new Statistic();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(gcLogFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("[ParNew:")) {
                    ParNewData data = parNewParser.parse(line);
                    if (statistic.getParNewDatas() == null) {
                        statistic.setParNewDatas(new ArrayList<>());
                    }
                    statistic.getParNewDatas().add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statistic;
    }
}
