package com.nexusy.glp.data;

/**
 * 由于CMS-concurrent-mark, CMS-concurrent-preclean, CMS-concurrent-abortable-preclean, CMS-concurrent-sweep,
 * CMS-concurrent-reset阶段记录的数据结构相同，因此使用同一个类，由{@link #phase}字段区分数据属于哪个阶段
 *
 * @author lanhuidong
 * @since 2016-12-08
 */
public class CMSConcurrentData extends GCData {

    private CMSPhase phase;

    private double elapsedTime;

    private double clockTime;

    public CMSPhase getPhase() {
        return phase;
    }

    public void setPhase(CMSPhase phase) {
        this.phase = phase;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public double getClockTime() {
        return clockTime;
    }

    public void setClockTime(double clockTime) {
        this.clockTime = clockTime;
    }

}
