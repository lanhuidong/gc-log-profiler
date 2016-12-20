package com.nexusy.glp.data.basic;

/**
 * @author lanhuidong
 * @since 2016-12-09
 */
public class CMSFinalRemarkData extends GCData {

    private CMSPhase phase;

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

    /**
     * Stop the World的持续时间
     */
    private double stwDuration;

    public CMSPhase getPhase() {
        return phase;
    }

    public void setPhase(CMSPhase phase) {
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

    public double getStwDuration() {
        return stwDuration;
    }

    public void setStwDuration(double stwDuration) {
        this.stwDuration = stwDuration;
    }

}
