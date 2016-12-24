package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.CommandLineData;
import com.nexusy.glp.data.basic.GCName;
import com.nexusy.glp.parser.GCLogLineParser;

/**
 * @author lanhuidong
 * @since 2016-12-24
 */
public class CommandLineParser implements GCLogLineParser<CommandLineData> {

    @Override
    public CommandLineData parse(String line) {
        CommandLineData data = new CommandLineData();
        if (line.contains("-XX:+UseConcMarkSweepGC") && line.contains("-XX:+UseParNewGC")) {
            data.setYoungGCName(GCName.ParNew);
            data.setOldGCName(GCName.CMS);
        } else if (line.contains("-XX:+UseSerialGC")) {
            data.setYoungGCName(GCName.Serial);
            data.setOldGCName(GCName.SerialOld);
        }
        return data;
    }

}
