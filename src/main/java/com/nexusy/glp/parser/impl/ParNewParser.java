package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.ParNewData;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-07
 */
public class ParNewParser extends AbstractGCLogLineParser<ParNewData> {

    private static final String regex = "\\[(?<flag>[a-zA-Z|\\s]+[a-zA-Z])"
            + "("
            + "\\s\\((?<cause>[\\w|\\s]+)\\)"
            + ")?"
            + ".*ParNew:\\s(?<youngUsageBfGC>\\d+\\w)"
            + "->(?<yongUsageAfGC>\\d+\\w)"
            + "\\((?<youngSize>\\d+\\w)\\)"
            + ",\\s(?<minorGCDuration>\\d+\\.\\d+)"
            + "\\ssecs\\]\\s(?<heapUsageBfGC>\\d+\\w)"
            + "->(?<headUsageAfGC>\\d+\\w)"
            + "\\((?<heapSize>\\d+\\w)\\)"
            + ",\\s(?<totalDuration>\\d+\\.\\d+)\\ssecs\\]\\s";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public ParNewData parse0(String line) {
        ParNewData data = new ParNewData();
        data.setGcName("ParNew");
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
            double minorGCDuration = StringUtil.toDouble(matcher.group("minorGCDuration"));
            data.setMinorGCDuration(minorGCDuration);

            long heapUsageBfGC = StringUtil.toBytes(matcher.group("heapUsageBfGC"));
            data.setHeapUsageBfGC(heapUsageBfGC);
            long headUsageAfGC = StringUtil.toBytes(matcher.group("headUsageAfGC"));
            data.setHeadUsageAfGC(headUsageAfGC);
            long heapSize = StringUtil.toBytes(matcher.group("heapSize"));
            data.setHeapSize(heapSize);
            double totalDuration = StringUtil.toDouble(matcher.group("totalDuration"));
            data.setStwDuration(totalDuration);
        }
        return data;
    }
}
