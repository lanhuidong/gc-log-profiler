package com.nexusy.glp.parser;

import com.nexusy.glp.data.basic.BasicData;

import java.io.File;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public interface GCLogParser {

    BasicData parse(File gcLogFile);
}
