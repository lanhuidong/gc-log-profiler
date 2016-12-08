package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.DefNewData;
import com.nexusy.glp.parser.GCLogLineParser;
import com.nexusy.glp.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析Serial GC产生的日志
 *
 * @author lanhuidong
 * @since 2016-12-07
 */
public class DefNewParser implements GCLogLineParser<DefNewData> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String regex = "((?<datetime>\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}\\.\\d{3}[-|+]\\d{4}):\\s)?"
            + "(?<uptime>\\d+\\.\\d+):\\s"
            + "\\[(?<flag>[a-zA-Z|\\s]+[a-zA-Z])"
            + "("
            + "\\s\\((?<cause>[\\w|\\s]+)\\)"
            + ")?"
            + "("
            + ".*DefNew:\\s(?<youngUsageBfGC>\\d+)\\w"
            + "->(?<yongUsageAfGC>\\d+)\\w"
            + "\\((?<youngSize>\\d+)\\w\\)"
            + ",\\s(?<minorGCDuration>\\d+\\.\\d+)\\ssecs\\]"
            + ")?"
            + "("
            + ".*\\[Tenured:\\s(?<oldUsageBfGC>\\d+)\\w"
            + "->(?<oldUsageAfGC>\\d+)\\w"
            + "\\((?<oldSize>\\d+)\\w\\)"
            + ",\\s(?<fullGCDuration>\\d+\\.\\d+)\\ssecs\\]"
            + ")?"
            + "\\s(?<heapUsageBfGC>\\d+)\\w"
            + "->(?<headUsageAfGC>\\d+)\\w"
            + "\\((?<heapSize>\\d+)\\w\\)"
            + "("
            + ",\\s\\[(Metaspace|Perm\\s):\\s(?<metaspaceUsageBfGC>\\d+)\\w"
            + "->(?<metaspaceUsageAfGC>\\d+)\\w"
            + "\\((?<metaspaceSize>\\d+)\\w\\)\\]"
            + ")?"
            + ",\\s(?<totalDuration>\\d+\\.\\d+)\\ssecs\\]\\s"
            + "\\[.*user=(?<userTime>\\d+\\.\\d+).*sys=(?<sysTime>\\d+\\.\\d+).*real=(?<realTime>\\d+\\.\\d+).*\\]";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public DefNewData parse(String line) {
        DefNewData data = new DefNewData();
        data.setGcName("DefNew");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {

            String dateTimeString = matcher.group("datetime");
            if (StringUtil.isNotBlank(dateTimeString)) {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                data.setDateTime(dateTime);
            }

            long uptime = (long) (Double.valueOf(matcher.group("uptime")) * 1000);
            data.setUptime(uptime);

            String flag = matcher.group("flag");
            data.setFlag(flag);

            String cause = matcher.group("cause");
            data.setCause(cause);

            if (line.contains("DefNew")) {
                long youngUsageBfGC = Long.valueOf(matcher.group("youngUsageBfGC"));
                data.setYoungGenUsageBfGC(youngUsageBfGC);
                long yongUsageAfGC = Long.valueOf(matcher.group("yongUsageAfGC"));
                data.setYoungGenUsageAfGC(yongUsageAfGC);
                long youngSize = Long.valueOf(matcher.group("youngSize"));
                data.setYongGenSize(youngSize);
                double minorGCDuration = Double.valueOf(matcher.group("minorGCDuration"));
                data.setMinorGCDuration(minorGCDuration);
            }

            if (line.contains("Tenured")) {
                long oldUsageBfGC = Long.valueOf(matcher.group("oldUsageBfGC"));
                data.setOldGenUsageBfGC(oldUsageBfGC);
                long oldUsageAfGC = Long.valueOf(matcher.group("oldUsageAfGC"));
                data.setOldGenUsageAfGC(oldUsageAfGC);
                long oldSize = Long.valueOf(matcher.group("oldSize"));
                data.setOldSize(oldSize);
                double fullGCDuration = Double.valueOf(matcher.group("fullGCDuration"));
                data.setFullGCDuration(fullGCDuration);
            }

            long heapUsageBfGC = Long.valueOf(matcher.group("heapUsageBfGC"));
            data.setHeapUsageBfGC(heapUsageBfGC);
            long headUsageAfGC = Long.valueOf(matcher.group("headUsageAfGC"));
            data.setHeadUsageAfGC(headUsageAfGC);
            long heapSize = Long.valueOf(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            if (line.contains("Metaspace") || line.contains("Perm")) {
                long metaspaceUsageBfGC = Long.valueOf(matcher.group("metaspaceUsageBfGC"));
                data.setMetaspaceUsageBfGC(metaspaceUsageBfGC);
                long metaspaceUsageAfGC = Long.valueOf(matcher.group("metaspaceUsageAfGC"));
                data.setMetaspaceUsageAfGC(metaspaceUsageAfGC);
                long metaspaceSize = Long.valueOf(matcher.group("metaspaceSize"));
                data.setMetaspaceSize(metaspaceSize);
            }

            double totalDuration = Double.valueOf(matcher.group("totalDuration"));
            data.setTotalDuration(totalDuration);

            double userTime = Double.valueOf(matcher.group("userTime"));
            data.setUserTime(userTime);
            double sysTime = Double.valueOf(matcher.group("sysTime"));
            data.setSysTime(sysTime);
            double realTime = Double.valueOf(matcher.group("realTime"));
            data.setRealTime(realTime);
        }
        return data;
    }

}
