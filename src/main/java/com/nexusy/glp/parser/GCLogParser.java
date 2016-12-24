package com.nexusy.glp.parser;

import com.nexusy.glp.data.basic.BasicData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public interface GCLogParser {

    BasicData parse(InputStream in);

    default BasicData parse(File gcLogFile) {
        try {
            return parse(new FileInputStream(gcLogFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new BasicData();
    }

}
