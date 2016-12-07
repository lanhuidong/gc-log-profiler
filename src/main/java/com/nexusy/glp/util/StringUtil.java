package com.nexusy.glp.util;

/**
 * @author lanhuidong
 * @since 2016-12-07
 */
public class StringUtil {

    public static boolean isNotBlank(CharSequence sequence) {
        return sequence != null && sequence.length() != 0;
    }
}
