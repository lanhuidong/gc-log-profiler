package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSInitialMarkData;
import com.nexusy.glp.data.CMSPhase;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSInitMarkParser extends AbstractGCLogLineParser<CMSInitialMarkData> {

    private static final String regex = ".*CMS-initial-mark:\\s"
            + "(?<oldGenUsage>\\d+\\w)\\((?<oldGenSize>\\d+\\w)\\)\\]\\s"
            + "(?<heapUsage>\\d+\\w)\\((?<heapSize>\\d+\\w)\\),\\s"
            + "(?<duration>\\d+\\.\\d+)";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public CMSInitialMarkData parse0(String line) {
        CMSInitialMarkData data = new CMSInitialMarkData();
        data.setPhase(CMSPhase.InitialMark);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            long oldGenUsage = StringUtil.toBytes(matcher.group("oldGenUsage"));
            data.setOldGenUsage(oldGenUsage);
            long oldGenSize = StringUtil.toBytes(matcher.group("oldGenSize"));
            data.setOldGenSize(oldGenSize);

            long heapUsage = StringUtil.toBytes(matcher.group("heapUsage"));
            data.setHeapUsage(heapUsage);
            long heapSize = StringUtil.toBytes(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            double duration = StringUtil.toDouble(matcher.group("duration"));
            data.setDuration(duration);
        }

        return data;
    }

}
