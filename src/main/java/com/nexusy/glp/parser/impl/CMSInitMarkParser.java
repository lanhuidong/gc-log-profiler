package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSInitialMarkData;
import com.nexusy.glp.parser.GCLogLineParser;
import com.nexusy.glp.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSInitMarkParser implements GCLogLineParser<CMSInitialMarkData> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String regex = "((?<datetime>\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}\\.\\d{3}[-|+]\\d{4}):\\s)?"
            + "(?<uptime>\\d+\\.\\d+):\\s"
            + ".*CMS-initial-mark:\\s(?<oldGenUsage>\\d+)\\w\\((?<oldGenSize>\\d+)\\w\\)\\]\\s"
            + "(?<heapUsage>\\d+)\\w\\((?<heapSize>\\d+)\\w\\),\\s"
            + "(?<duration>\\d+\\.\\d+)\\ssecs\\]\\s"
            + "\\[.*user=(?<userTime>\\d+\\.\\d+).*sys=(?<sysTime>\\d+\\.\\d+).*real=(?<realTime>\\d+\\.\\d+).*\\]";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public CMSInitialMarkData parse(String line) {
        CMSInitialMarkData data = new CMSInitialMarkData();
        data.setPhase("CMS Initial Mark");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {

            String dateTimeString = matcher.group("datetime");
            if (StringUtil.isNotBlank(dateTimeString)) {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                data.setDateTime(dateTime);
            }

            long uptime = (long) (Double.valueOf(matcher.group("uptime")) * 1000);
            data.setUptime(uptime);

            long oldGenUsage = Long.valueOf(matcher.group("oldGenUsage"));
            data.setOldGenUsage(oldGenUsage);
            long oldGenSize = Long.valueOf(matcher.group("oldGenSize"));
            data.setOldGenSize(oldGenSize);

            long heapUsage = Long.valueOf(matcher.group("heapUsage"));
            data.setHeapUsage(heapUsage);
            long heapSize = Long.valueOf(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            double duration = Double.valueOf(matcher.group("duration"));
            data.setDuration(duration);

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
