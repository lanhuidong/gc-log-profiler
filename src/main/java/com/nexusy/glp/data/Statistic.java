package com.nexusy.glp.data;

import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-12-06
 */
public class Statistic {

    /**
     * 当前使用的垃圾收集器名称
     */
    private String gcName;

    /**
     * 是否显示调用了{@link System#gc()}
     */
    private boolean callSystemGC;

    /**
     * 新生代大小，单位：字节
     */
    private long youngGenSize;

    /**
     * 老年代大小，单位：KB
     */
    private long oldGenSize;

    /**
     * 永久代大小，单位：字节
     */
    private long permGenSize;

    /**
     * Minor GC的统计列表
     */
    private List<MinorGCStatistic> minorGCStats;

    private List<MajorGCStatistic> majorGCStats;

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public boolean isCallSystemGC() {
        return callSystemGC;
    }

    public void setCallSystemGC(boolean callSystemGC) {
        this.callSystemGC = callSystemGC;
    }

    public long getYoungGenSize() {
        return youngGenSize;
    }

    public void setYoungGenSize(long youngGenSize) {
        this.youngGenSize = youngGenSize;
    }

    public long getOldGenSize() {
        return oldGenSize;
    }

    public void setOldGenSize(long oldGenSize) {
        this.oldGenSize = oldGenSize;
    }

    public long getPermGenSize() {
        return permGenSize;
    }

    public void setPermGenSize(long permGenSize) {
        this.permGenSize = permGenSize;
    }

    public List<MinorGCStatistic> getMinorGCStats() {
        return minorGCStats;
    }

    public void setMinorGCStats(List<MinorGCStatistic> minorGCStats) {
        this.minorGCStats = minorGCStats;
    }

    public List<MajorGCStatistic> getMajorGCStats() {
        return majorGCStats;
    }

    public void setMajorGCStats(List<MajorGCStatistic> majorGCStats) {
        this.majorGCStats = majorGCStats;
    }
}
