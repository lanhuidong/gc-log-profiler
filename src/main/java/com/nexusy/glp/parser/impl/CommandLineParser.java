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
        if (line.contains("-XX:+UseG1GC")) {
            data.setYoungGCName(GCName.G1);
            data.setOldGCName(GCName.G1);
        } else if (line.contains("-XX:+UseConcMarkSweepGC") && line.contains("-XX:+UseParNewGC")) {
            data.setYoungGCName(GCName.ParNew);
            data.setOldGCName(GCName.CMS);
        } else if (line.contains("-XX:+UseParallelOldGC")) {
            data.setYoungGCName(GCName.ParallelScavenge);
            data.setOldGCName(GCName.ParallelOld);
        } else if (line.contains("-XX:+UseParallelGC")) {
            data.setYoungGCName(GCName.ParallelScavenge);
            data.setOldGCName(GCName.SerialOld);
        } else if (line.contains("-XX:+UseSerialGC")) {
            data.setYoungGCName(GCName.DefNew);
            data.setOldGCName(GCName.SerialOld);
        }
        return data;
    }

}
