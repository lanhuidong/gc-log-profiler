package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.ParNewData;
import com.nexusy.glp.parser.GCLogLineParser;
import com.nexusy.glp.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-07
 */
public class ParNewParser implements GCLogLineParser<ParNewData> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String regex = "((?<datetime>\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}\\.\\d{3}[-|+]\\d{4}):\\s)?"
            + "(?<uptime>\\d+\\.\\d+):\\s"
            + "\\[(?<flag>\\w+)"
            + "\\s\\((?<cause>[\\w|\\s]+)\\)"
            + ".*ParNew:\\s(?<youngUsageBfGC>\\d+)"
            + "\\w->(?<yongUsageAfGC>\\d+)\\w"
            + "\\((?<youngSize>\\d+)\\w\\)"
            + ",\\s(?<minorGCDuration>\\d+\\.\\d+)"
            + "\\ssecs\\]\\s(?<heapUsageBfGC>\\d+)"
            + "\\w->(?<headUsageAfGC>\\d+)"
            + "\\w\\((?<heapSize>\\d+)\\w\\)"
            + ",\\s(?<totalDuration>\\d+\\.\\d+)\\ssecs\\]\\s"
            + "\\[.*user=(?<userTime>\\d+\\.\\d+).*sys=(?<sysTime>\\d+\\.\\d+).*real=(?<realTime>\\d+\\.\\d+).*\\]";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public ParNewData parse(String line) {
        ParNewData data = new ParNewData();
        data.setGcName("ParNew");
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

            long youngUsageBfGC = Long.valueOf(matcher.group("youngUsageBfGC"));
            data.setYoungGenUsageBfGC(youngUsageBfGC);
            long yongUsageAfGC = Long.valueOf(matcher.group("yongUsageAfGC"));
            data.setYoungGenUsageAfGC(yongUsageAfGC);
            long youngSize = Long.valueOf(matcher.group("youngSize"));
            data.setYongGenSize(youngSize);
            double minorGCDuration = Double.valueOf(matcher.group("minorGCDuration"));
            data.setMinorGCDuration(minorGCDuration);

            long heapUsageBfGC = Long.valueOf(matcher.group("heapUsageBfGC"));
            data.setHeapUsageBfGC(heapUsageBfGC);
            long headUsageAfGC = Long.valueOf(matcher.group("headUsageAfGC"));
            data.setHeadUsageAfGC(headUsageAfGC);
            long heapSize = Long.valueOf(matcher.group("heapSize"));
            data.setHeapSize(heapSize);
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
