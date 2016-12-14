package com.nexusy.glp.util;

/**
 * @author lanhuidong
 * @since 2016-12-07
 */
public class StringUtil {

    public static boolean isNotBlank(CharSequence sequence) {
        return sequence != null && sequence.length() != 0;
    }

    public static double toDouble(String s) {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException ignored) {
        }
        return 0.0;
    }

    public static long toLong(String s) {
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException ignored) {
        }
        return 0L;
    }

}
