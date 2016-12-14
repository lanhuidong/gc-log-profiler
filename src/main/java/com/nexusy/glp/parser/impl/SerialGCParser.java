package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.SerialGCData;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析Serial GC产生的日志
 *
 * @author lanhuidong
 * @since 2016-12-07
 */
public class SerialGCParser extends AbstractGCLogLineParser<SerialGCData> {

    private static final String regex = "\\[(?<flag>[a-zA-Z|\\s]+[a-zA-Z])"
            + "("
            + "\\s\\((?<cause>[\\w|\\s]+)\\)"
            + ")?"
            + "("
            + ".*DefNew:\\s(?<youngUsageBfGC>\\d+\\w)"
            + "->(?<yongUsageAfGC>\\d+\\w)"
            + "\\((?<youngSize>\\d+\\w)\\)"
            + ",\\s(?<minorGCDuration>\\d+\\.\\d+)\\ssecs\\]"
            + ")?"
            + "("
            + ".*\\[Tenured:\\s(?<oldUsageBfGC>\\d+\\w)"
            + "->(?<oldUsageAfGC>\\d+\\w)"
            + "\\((?<oldSize>\\d+\\w)\\)"
            + ",\\s(?<fullGCDuration>\\d+\\.\\d+)\\ssecs\\]"
            + ")?"
            + "\\s(?<heapUsageBfGC>\\d+\\w)"
            + "->(?<headUsageAfGC>\\d+\\w)"
            + "\\((?<heapSize>\\d+\\w)\\)"
            + "("
            + ",\\s\\[(Metaspace|Perm\\s):\\s(?<metaspaceUsageBfGC>\\d+\\w)"
            + "->(?<metaspaceUsageAfGC>\\d+\\w)"
            + "\\((?<metaspaceSize>\\d+\\w)\\)\\]"
            + ")?"
            + ",\\s(?<totalDuration>\\d+\\.\\d+)\\ssecs\\]\\s";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public SerialGCData parse0(String line) {
        SerialGCData data = new SerialGCData();
        data.setGcName("DefNew");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String flag = matcher.group("flag");
            data.setFlag(flag);

            String cause = matcher.group("cause");
            data.setCause(cause);

            if (line.contains("DefNew")) {
                long youngUsageBfGC = StringUtil.toBytes(matcher.group("youngUsageBfGC"));
                data.setYoungGenUsageBfGC(youngUsageBfGC);
                long yongUsageAfGC = StringUtil.toBytes(matcher.group("yongUsageAfGC"));
                data.setYoungGenUsageAfGC(yongUsageAfGC);
                long youngSize = StringUtil.toBytes(matcher.group("youngSize"));
                data.setYongGenSize(youngSize);
                double minorGCDuration = StringUtil.toDouble(matcher.group("minorGCDuration"));
                data.setMinorGCDuration(minorGCDuration);
            }

            if (line.contains("Tenured")) {
                long oldUsageBfGC = StringUtil.toBytes(matcher.group("oldUsageBfGC"));
                data.setOldGenUsageBfGC(oldUsageBfGC);
                long oldUsageAfGC = StringUtil.toBytes(matcher.group("oldUsageAfGC"));
                data.setOldGenUsageAfGC(oldUsageAfGC);
                long oldSize = StringUtil.toBytes(matcher.group("oldSize"));
                data.setOldSize(oldSize);
                double fullGCDuration = StringUtil.toDouble(matcher.group("fullGCDuration"));
                data.setFullGCDuration(fullGCDuration);
            }

            long heapUsageBfGC = StringUtil.toBytes(matcher.group("heapUsageBfGC"));
            data.setHeapUsageBfGC(heapUsageBfGC);
            long headUsageAfGC = StringUtil.toBytes(matcher.group("headUsageAfGC"));
            data.setHeadUsageAfGC(headUsageAfGC);
            long heapSize = StringUtil.toBytes(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            if (line.contains("Metaspace") || line.contains("Perm")) {
                long metaspaceUsageBfGC = StringUtil.toBytes(matcher.group("metaspaceUsageBfGC"));
                data.setMetaspaceUsageBfGC(metaspaceUsageBfGC);
                long metaspaceUsageAfGC = StringUtil.toBytes(matcher.group("metaspaceUsageAfGC"));
                data.setMetaspaceUsageAfGC(metaspaceUsageAfGC);
                long metaspaceSize = StringUtil.toBytes(matcher.group("metaspaceSize"));
                data.setMetaspaceSize(metaspaceSize);
            }

            double totalDuration = StringUtil.toDouble(matcher.group("totalDuration"));
            data.setTotalDuration(totalDuration);
        }
        return data;
    }

}
