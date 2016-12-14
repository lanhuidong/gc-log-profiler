package com.nexusy.glp.data;

import java.time.LocalDateTime;

/**
 * @author lanhuidong
 * @since 2016-12-14
 */
public class GCData {

    /**
     * GC开始时间
     */
    private LocalDateTime dateTime;

    /**
     * JVM启动后的毫秒数
     */
    private long uptime;

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
