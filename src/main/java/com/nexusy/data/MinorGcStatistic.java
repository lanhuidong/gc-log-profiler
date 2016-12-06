package com.nexusy.data;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class MinorGCStatistic {

    /**
     * JVM启动到发生本次Minor GC的时间，单位：毫秒
     */
    private long timestamp;

    /**
     * 本次Minor GC的持续时间，单位：毫秒
     */
    private double minorGCDuration;

    /**
     * 本次Minor GC是否的新生代空间，单位：KB
     */
    private long minorGCFreeSpace;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getMinorGCDuration() {
        return minorGCDuration;
    }

    public void setMinorGCDuration(double minorGCDuration) {
        this.minorGCDuration = minorGCDuration;
    }

    public long getMinorGCFreeSpace() {
        return minorGCFreeSpace;
    }

    public void setMinorGCFreeSpace(long minorGCFreeSpace) {
        this.minorGCFreeSpace = minorGCFreeSpace;
    }
}
