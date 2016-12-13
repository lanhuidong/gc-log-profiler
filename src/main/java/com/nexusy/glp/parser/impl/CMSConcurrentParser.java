package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSConcurrentData;
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
public class CMSConcurrentParser implements GCLogLineParser<CMSConcurrentData> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String regex = "((?<datetime>\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}\\.\\d{3}[-|+]\\d{4}):\\s)?"
            + "(?<uptime>\\d+\\.\\d+):\\s"
            + "\\[(?<phase>CMS-concurrent-.*):\\s(?<elapsedTime>\\d+\\.\\d+)/(?<clockTime>\\d+\\.\\d+)\\ssecs\\]\\s"
            + "\\[.*user=(?<userTime>\\d+\\.\\d+).*sys=(?<sysTime>\\d+\\.\\d+).*real=(?<realTime>\\d+\\.\\d+).*\\]";

    private static final Pattern pattern = Pattern.compile(regex);


    @Override
    public CMSConcurrentData parse(String line) {
        CMSConcurrentData data = new CMSConcurrentData();
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String dateTimeString = matcher.group("datetime");
            if (StringUtil.isNotBlank(dateTimeString)) {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                data.setDateTime(dateTime);
            }

            long uptime = (long) (Double.valueOf(matcher.group("uptime")) * 1000);
            data.setUptime(uptime);

            String phase = matcher.group("phase");
            data.setPhase(phase);

            double elapsedTime = Double.valueOf(matcher.group("elapsedTime"));
            data.setElapsedTime(elapsedTime);
            double clockTime = Double.valueOf(matcher.group("clockTime"));
            data.setClockTime(clockTime);

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
