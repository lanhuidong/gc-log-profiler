package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSFinalRemarkData;
import com.nexusy.glp.data.CMSPhase;
import com.nexusy.glp.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSFinalRemarkParser extends AbstractGCLogLineParser<CMSFinalRemarkData> {

    private static final String regex = ".*YG occupancy:\\s"
            + "(?<youngGenUsage>\\d+)\\s\\w\\s\\((?<youngGenSize>\\d+)\\s\\w\\)"
            + ".*Rescan\\s\\(parallel\\)\\s,\\s(?<rescan>\\d+\\.\\d+)"
            + ".*weak refs processing,\\s(?<weakRefs>\\d+\\.\\d+)"
            + ".*class unloading,\\s(?<classUnloading>\\d+\\.\\d+)"
            + ".*scrub symbol table,\\s(?<symbolTable>\\d+\\.\\d+)"
            + ".*scrub string table,\\s(?<stringTable>\\d+\\.\\d+)"
            + ".*CMS-remark:\\s(?<oldGenUsage>\\d+)\\w\\((?<oldGenSize>\\d+)\\w\\)\\]"
            + "\\s(?<heapUsage>\\d+)\\w\\((?<heapSize>\\d+)\\w\\),\\s(?<duration>\\d+\\.\\d+)";

    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public CMSFinalRemarkData parse0(String line) {
        CMSFinalRemarkData data = new CMSFinalRemarkData();
        data.setPhase(CMSPhase.FinalRemark);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            long youngGenUsage = StringUtil.toLong(matcher.group("youngGenUsage"));
            data.setYoungGenUsage(youngGenUsage);
            long youngGenSize = StringUtil.toLong(matcher.group("youngGenSize"));
            data.setYoungGenSize(youngGenSize);

            double rescanDuration = StringUtil.toDouble(matcher.group("rescan"));
            data.setRescanDuration(rescanDuration);

            double weakRefsProcessing = StringUtil.toDouble(matcher.group("weakRefs"));
            data.setWeakRefsProcessing(weakRefsProcessing);

            double classUnloading = StringUtil.toDouble(matcher.group("classUnloading"));
            data.setClassUnloading(classUnloading);

            double symbolTable = StringUtil.toDouble(matcher.group("symbolTable"));
            data.setScrubSymbolTable(symbolTable);

            double stringTable = StringUtil.toDouble(matcher.group("stringTable"));
            data.setScrubStringTable(stringTable);

            long oldGenUsage = StringUtil.toLong(matcher.group("oldGenUsage"));
            data.setOldGenUsage(oldGenUsage);
            long oldGenSize = StringUtil.toLong(matcher.group("oldGenSize"));
            data.setOldGenSize(oldGenSize);

            long heapUsage = StringUtil.toLong(matcher.group("heapUsage"));
            data.setHeapUsage(heapUsage);
            long heapSize = StringUtil.toLong(matcher.group("heapSize"));
            data.setHeapSize(heapSize);

            double duration = StringUtil.toDouble(matcher.group("duration"));
            data.setDuration(duration);
        }
        return data;
    }
}
