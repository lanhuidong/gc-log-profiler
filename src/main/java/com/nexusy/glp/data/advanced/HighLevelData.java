package com.nexusy.glp.data.advanced;

/**
 * @author lanhuidong
 * @since 2016-12-15
 */
public class HighLevelData {

    /**
     * Minor GC日志的时间跨度，单位：毫秒
     */
    private long minorGCDuration;

    /**
     * 发生Minor GC的次数
     */
    private long minorGCTimes;

    /**
     * GC(Stop the World)最大暂停时间
     */
    private double maxGCPause;

    /**
     * 发生Minor GC时提升对象到老年代的次数
     */
    private long promotionTimes;

    /**
     * Minor GC中年轻代到老年代提升的总内存大小，单位：字节
     */
    private long promotionSize;

    public long getMinorGCDuration() {
        return minorGCDuration;
    }

    public void setMinorGCDuration(long minorGCDuration) {
        this.minorGCDuration = minorGCDuration;
    }

    public long getMinorGCTimes() {
        return minorGCTimes;
    }

    public void setMinorGCTimes(long minorGCTimes) {
        this.minorGCTimes = minorGCTimes;
    }

    public double getMaxGCPause() {
        return maxGCPause;
    }

    public void setMaxGCPause(double maxGCPause) {
        this.maxGCPause = maxGCPause;
    }

    public long getPromotionTimes() {
        return promotionTimes;
    }

    public void setPromotionTimes(long promotionTimes) {
        this.promotionTimes = promotionTimes;
    }

    public long getPromotionSize() {
        return promotionSize;
    }

    public void setPromotionSize(long promotionSize) {
        this.promotionSize = promotionSize;
    }
}
