package com.nexusy.glp.parser;

import com.nexusy.glp.data.Statistic;

import java.io.File;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public interface GCLogParser {

    Statistic parse(File gcLogFile);
}
