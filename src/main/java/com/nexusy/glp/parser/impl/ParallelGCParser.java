package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.ParallelGCData;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-08
 */
public class ParallelGCParser extends AbstractGCLogLineParser<ParallelGCData> {

    private static final String regex = "\\[(?<flag>[a-zA-Z|\\s]+[a-zA-Z])"
            + "("
            + "\\s\\((?<cause>[\\w|\\s]+)\\)"
            + ")?"
            + ".*PSYoungGen:\\s(?<youngUsageBfGC>\\d+\\w)"
            + "->(?<yongUsageAfGC>\\d+\\w)"
            + "\\((?<youngSize>\\d+\\w)\\)\\]"
            + "("
            + ".*\\[ParOldGen:\\s(?<oldUsageBfGC>\\d+\\w)"
            + "->(?<oldUsageAfGC>\\d+\\w)"
            + "\\((?<oldSize>\\d+\\w)\\)\\]"
            + ")?"
            + "\\s(?<heapUsageBfGC>\\d+\\w)"
            + "->(?<headUsageAfGC>\\d+\\w)"
            + "\\((?<heapSize>\\d+\\w)\\)"
            + "("
            + ",?\\s\\[(Metaspace|PSPermGen):\\s(?<metaspaceUsageBfGC>\\d+\\w)"
            + "->(?<metaspaceUsageAfGC>\\d+\\w)"
            + "\\((?<metaspaceSize>\\d+\\w)\\)\\]"
            + ")?"
            + ",\\s(?<totalDuration>\\d+\\.\\d+)\\ssecs\\]\\s";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public ParallelGCData parse0(String line) {
        ParallelGCData data = new ParallelGCData();
        data.setGcName("ParallelGC");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String flag = matcher.group("flag");
            data.setFlag(flag);

            String cause = matcher.group("cause");
            data.setCause(cause);

            long youngUsageBfGC = StringUtil.toBytes(matcher.group("youngUsageBfGC"));
            data.setYoungGenUsageBfGC(youngUsageBfGC);
            long yongUsageAfGC = StringUtil.toBytes(matcher.group("yongUsageAfGC"));
            data.setYoungGenUsageAfGC(yongUsageAfGC);
            long youngSize = StringUtil.toBytes(matcher.group("youngSize"));
            data.setYongGenSize(youngSize);

            if (line.contains("ParOldGen")) {
                long oldUsageBfGC = StringUtil.toBytes(matcher.group("oldUsageBfGC"));
                data.setOldGenUsageBfGC(oldUsageBfGC);
                long oldUsageAfGC = StringUtil.toBytes(matcher.group("oldUsageAfGC"));
                data.setOldGenUsageAfGC(oldUsageAfGC);
                long oldSize = StringUtil.toBytes(matcher.group("oldSize"));
                data.setOldSize(oldSize);
            }

            long heapUsageBfGC = StringUtil.toBytes(matcher.group("heapUsageBfGC"));
            data.setHeapUsageBfGC(heapUsageBfGC);
            long headUsageAfGC = StringUtil.toBytes(matcher.group("headUsageAfGC"));
            data.setHeadUsageAfGC(headUsageAfGC);
            long heapSize = StringUtil.toBytes(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            if (line.contains("Metaspace") || line.contains("PSPermGen")) {
                long metaspaceUsageBfGC = StringUtil.toBytes(matcher.group("metaspaceUsageBfGC"));
                data.setMetaspaceUsageBfGC(metaspaceUsageBfGC);
                long metaspaceUsageAfGC = StringUtil.toBytes(matcher.group("metaspaceUsageAfGC"));
                data.setMetaspaceUsageAfGC(metaspaceUsageAfGC);
                long metaspaceSize = StringUtil.toBytes(matcher.group("metaspaceSize"));
                data.setMetaspaceSize(metaspaceSize);
            }

            double totalDuration = StringUtil.toDouble(matcher.group("totalDuration"));
            data.setStwDuration(totalDuration);
        }
        return data;
    }

}
