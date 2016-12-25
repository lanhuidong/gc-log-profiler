package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.*;
import com.nexusy.glp.parser.GCLogParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    private CommandLineParser commandLineParser = new CommandLineParser();

    @Override
    public BasicData parse(InputStream in) {
        BasicData basicData = new BasicData();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (basicData.getCommandLineData() != null) {
                    selectParser(line, basicData);
                } else if (line.contains("CommandLine flags")) {
                    CommandLineData data = commandLineParser.parse(line);
                    basicData.setCommandLineData(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basicData;
    }

    private void selectParser(String line, BasicData basicData) {
        CommandLineData cmdData = basicData.getCommandLineData();
        if (cmdData.getOldGCName() == GCName.ParallelOld && cmdData.getYoungGCName() == GCName.ParallelScavenge) {
            // TODO: 2016/12/25  
        } else if (cmdData.getOldGCName() == GCName.CMS && cmdData.getYoungGCName() == GCName.ParNew) {
            if (line.contains("ParNew")) {
                ParNewData data = parNewParser.parse(line);
                if (basicData.getParNewDatas() == null) {
                    basicData.setParNewDatas(new ArrayList<>());
                }
                basicData.getParNewDatas().add(data);
            } else if (line.contains("CMS-concurrent-")) {
                CMSConcurrentData data = cmsConcParser.parse(line);
                if (basicData.getCmsConcurrentDatas() == null) {
                    basicData.setCmsConcurrentDatas(new ArrayList<>());
                }
                basicData.getCmsConcurrentDatas().add(data);
            } else if (line.contains("CMS-remark")) {
                CMSFinalRemarkData data = cmsFinalRemarkParser.parse(line);
                if (basicData.getCmsFinalRemarkDatas() == null) {
                    basicData.setCmsFinalRemarkDatas(new ArrayList<>());
                }
                basicData.getCmsFinalRemarkDatas().add(data);
            } else if (line.contains("CMS-initial-mark")) {
                CMSInitialMarkData data = cmsInitMarkParser.parse(line);
                if (basicData.getCmsInitialMarkDatas() == null) {
                    basicData.setCmsInitialMarkDatas(new ArrayList<>());
                }
                basicData.getCmsInitialMarkDatas().add(data);
            }
        } else if (cmdData.getOldGCName() == GCName.G1 && cmdData.getYoungGCName() == GCName.G1) {
            // TODO: 2016/12/25
        } else if (cmdData.getOldGCName() == GCName.SerialOld && cmdData.getYoungGCName() == GCName.ParallelScavenge) {
            // TODO: 2016/12/25  
        } else if (cmdData.getOldGCName() == GCName.SerialOld && cmdData.getYoungGCName() == GCName.DefNew) {
            SerialGCData data = serialGCParser.parse(line);
            if (basicData.getSerialGCDatas() == null) {
                basicData.setSerialGCDatas(new ArrayList<>());
            }
            basicData.getSerialGCDatas().add(data);
        }
    }

}
