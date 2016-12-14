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

    /**
     * 将内存大小转换成字节，比如1K=1024
     */
    public static long toBytes(String s) {
        long size = 0;
        if (s.endsWith("K") || s.endsWith("k")) {
            size = Long.valueOf(s.substring(0, s.length() - 1).trim()) * 1024;
        } else if (s.endsWith("M") || s.endsWith("m")) {
            size = Long.valueOf(s.substring(0, s.length() - 1).trim()) * 1024 * 1024;
        } else if (s.endsWith("G") || s.endsWith("g")) {
            size = Long.valueOf(s.substring(0, s.length() - 1).trim()) * 1024 * 1024;
        }
        return size;
    }

}
