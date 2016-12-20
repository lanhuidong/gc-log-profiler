package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.CMSConcurrentData;
import com.nexusy.glp.data.basic.CMSPhase;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSConcurrentParser extends AbstractGCLogLineParser<CMSConcurrentData> {

    private static final String regex = "(?<phase>CMS-concurrent-.*)"
            + ":\\s(?<elapsedTime>\\d+\\.\\d+)/(?<clockTime>\\d+\\.\\d+)";

    private static final Pattern pattern = Pattern.compile(regex);


    @Override
    public CMSConcurrentData parse0(String line) {
        CMSConcurrentData data = new CMSConcurrentData();
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String phase = matcher.group("phase");
            data.setPhase(CMSPhase.string2Enum(phase));

            double elapsedTime = StringUtil.toDouble(matcher.group("elapsedTime"));
            data.setElapsedTime(elapsedTime);
            double clockTime = StringUtil.toDouble(matcher.group("clockTime"));
            data.setClockTime(clockTime);
        }
        return data;
    }
}
