package com.nexusy.glp.data.basic;

/**
 * @author lanhuidong
 * @since 2016-12-08
 */
public class CMSInitialMarkData extends GCData {

    private CMSPhase phase;

    private long oldGenUsage;

    private long oldGenSize;

    private long heapUsage;

    private long heapSize;

    private double duration;

    public CMSPhase getPhase() {
        return phase;
    }

    public void setPhase(CMSPhase phase) {
        this.phase = phase;
    }

    public long getOldGenUsage() {
        return oldGenUsage;
    }

    public void setOldGenUsage(long oldGenUsage) {
        this.oldGenUsage = oldGenUsage;
    }

    public long getOldGenSize() {
        return oldGenSize;
    }

    public void setOldGenSize(long oldGenSize) {
        this.oldGenSize = oldGenSize;
    }

    public long getHeapUsage() {
        return heapUsage;
    }

    public void setHeapUsage(long heapUsage) {
        this.heapUsage = heapUsage;
    }

    public long getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(long heapSize) {
        this.heapSize = heapSize;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}
