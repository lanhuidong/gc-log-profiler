package com.nexusy.glp.data;

import java.time.LocalDateTime;

/**
 * @author lanhuidong
 * @since 2016-12-09
 */
public class CMSFinalRemarkData {

    private LocalDateTime dateTime;

    private long uptime;

    private String phase;

    private long youngGenUsage;

    private long youngGenSize;

    private double rescanDuration;

    private double weakRefsProcessing;

    private double classUnloading;

    private double scrubSymbolTable;

    private double scrubStringTable;

    private long oldGenUsage;

    private long oldGenSize;

    private long heapUsage;

    private long heapSize;

    private double duration;

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

    public long getYoungGenUsage() {
        return youngGenUsage;
    }

    public void setYoungGenUsage(long youngGenUsage) {
        this.youngGenUsage = youngGenUsage;
    }

    public long getYoungGenSize() {
        return youngGenSize;
    }

    public void setYoungGenSize(long youngGenSize) {
        this.youngGenSize = youngGenSize;
    }

    public double getRescanDuration() {
        return rescanDuration;
    }

    public void setRescanDuration(double rescanDuration) {
        this.rescanDuration = rescanDuration;
    }

    public double getWeakRefsProcessing() {
        return weakRefsProcessing;
    }

    public void setWeakRefsProcessing(double weakRefsProcessing) {
        this.weakRefsProcessing = weakRefsProcessing;
    }

    public double getClassUnloading() {
        return classUnloading;
    }

    public void setClassUnloading(double classUnloading) {
        this.classUnloading = classUnloading;
    }

    public double getScrubSymbolTable() {
        return scrubSymbolTable;
    }

    public void setScrubSymbolTable(double scrubSymbolTable) {
        this.scrubSymbolTable = scrubSymbolTable;
    }

    public double getScrubStringTable() {
        return scrubStringTable;
    }

    public void setScrubStringTable(double scrubStringTable) {
        this.scrubStringTable = scrubStringTable;
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
