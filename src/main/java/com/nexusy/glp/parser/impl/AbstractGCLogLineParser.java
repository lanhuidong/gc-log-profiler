package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.GCData;
import com.nexusy.glp.parser.GCLogLineParser;
import com.nexusy.glp.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-14
 */
public abstract class AbstractGCLogLineParser<T extends GCData> implements GCLogLineParser<T> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String timestampRegex = "^("
            + "(?<datetime>\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}\\.\\d{3}[-|+]\\d{4})"
            + ":\\s"
            + ")?"
            + "(?<uptime>\\d+\\.\\d+)";

    private static final Pattern timestampPattern = Pattern.compile(timestampRegex);

    private static final String cpuTimeRegex = ".*user=(?<userTime>\\d+\\.\\d+)"
            + ".*sys=(?<sysTime>\\d+\\.\\d+)"
            + ".*real=(?<realTime>\\d+\\.\\d+)";

    private static final Pattern cpuTimePattern = Pattern.compile(cpuTimeRegex);

    @Override
    public T parse(String line) {
        T data = parse0(line);
        parseTimestamp(line, data);
        parseCpuTime(line, data);
        return data;
    }

    protected abstract T parse0(String line);

    private void parseTimestamp(String line, T data) {
        Matcher matcher = timestampPattern.matcher(line);
        if (matcher.find()) {
            String dateTimeString = matcher.group("datetime");
            if (StringUtil.isNotBlank(dateTimeString)) {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                data.setDateTime(dateTime);
            }

            long uptime = (long) (StringUtil.toDouble(matcher.group("uptime")) * 1000);
            data.setUptime(uptime);
        }
    }

    private void parseCpuTime(String line, T data) {
        Matcher matcher = cpuTimePattern.matcher(line);
        if (matcher.find()) {
            double userTime = StringUtil.toDouble(matcher.group("userTime"));
            data.setUserTime(userTime);
            double sysTime = StringUtil.toDouble(matcher.group("sysTime"));
            data.setSysTime(sysTime);
            double realTime = StringUtil.toDouble(matcher.group("realTime"));
            data.setRealTime(realTime);
        }
    }

}
