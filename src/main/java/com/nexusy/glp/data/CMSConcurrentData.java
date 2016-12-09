package com.nexusy.glp.data;

import java.time.LocalDateTime;

/**
 * 由于CMS-concurrent-mark, CMS-concurrent-preclean, CMS-concurrent-abortable-preclean, CMS-concurrent-sweep,
 * CMS-concurrent-reset阶段记录的数据结构相同，因此使用同一个类，由{@link #phase}字段区分数据属于哪个阶段
 *
 * @author lanhuidong
 * @since 2016-12-08
 */
public class CMSConcurrentData {

    private LocalDateTime dateTime;

    private long uptime;

    private String phase;

    private double elapsedTime;

    private double clockTime;

    private double userTime;

    private double sysTime;

    private double realTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
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

    public double getUserTime() {
        return userTime;
    }

    public void setUserTime(double userTime) {
        this.userTime = userTime;
    }

    public double getSysTime() {
        return sysTime;
    }

    public void setSysTime(double sysTime) {
        this.sysTime = sysTime;
    }

    public double getRealTime() {
        return realTime;
    }

    public void setRealTime(double realTime) {
        this.realTime = realTime;
    }
}
