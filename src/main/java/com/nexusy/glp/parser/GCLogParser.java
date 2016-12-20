package com.nexusy.glp.parser;

import com.nexusy.glp.data.advanced.HighLevelData;

import java.io.File;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public interface GCLogParser {

    HighLevelData parse(File gcLogFile);
}
