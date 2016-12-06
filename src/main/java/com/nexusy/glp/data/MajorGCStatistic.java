package com.nexusy.glp.data;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class MajorGCStatistic {

    /**
     * JVM启动到发生本次Major GC的时间，单位：毫秒
     */
    private long timestamp;

    /**
     * 本次Major GC的持续时间，单位：毫秒
     */
    private double majorGCDuration;

    /**
     * 本次Major GC释放的内存大小，单位：KB
     */
    private long majorGCFreeSpace;

    /**
     * 最大暂停时间，单位：毫秒
     */
    private long maxPauseTime;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getMajorGCDuration() {
        return majorGCDuration;
    }

    public void setMajorGCDuration(double majorGCDuration) {
        this.majorGCDuration = majorGCDuration;
    }

    public long getMajorGCFreeSpace() {
        return majorGCFreeSpace;
    }

    public void setMajorGCFreeSpace(long majorGCFreeSpace) {
        this.majorGCFreeSpace = majorGCFreeSpace;
    }
}
