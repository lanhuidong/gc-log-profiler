package com.nexusy.glp.data;

import java.time.LocalDateTime;

/**
 * @author lanhuidong
 * @since 2016-12-08
 */
public class ParallelGCData {
    /**
     * GC开始时间
     */
    private LocalDateTime dateTime;

    /**
     * JVM启动后的毫秒数
     */
    private long uptime;

    /**
     * 区分Minor GC和Full GC
     */
    private String flag;

    /**
     * 触发GC的原因
     */
    private String cause;

    /**
     * 垃圾收集器的名称
     */
    private String gcName;

    /**
     * 垃圾收集前新生代的使用量
     */
    private long youngGenUsageBfGC;

    /**
     * 垃圾收集后新生代的使用量
     */
    private long youngGenUsageAfGC;

    /**
     * 新生代的大小
     */
    private long yongGenSize;

    private long oldGenUsageBfGC;

    private long oldGenUsageAfGC;

    private long oldSize;

    /**
     * 垃圾收集前堆的使用量
     */
    private long heapUsageBfGC;

    /**
     * 垃圾收集后堆的使用量
     */
    private long headUsageAfGC;

    /**
     * 整个堆的大小
     */
    private long heapSize;

    private double totalDuration;

    private long metaspaceUsageBfGC;

    private long metaspaceUsageAfGC;

    private long metaspaceSize;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public long getYoungGenUsageBfGC() {
        return youngGenUsageBfGC;
    }

    public void setYoungGenUsageBfGC(long youngGenUsageBfGC) {
        this.youngGenUsageBfGC = youngGenUsageBfGC;
    }

    public long getYoungGenUsageAfGC() {
        return youngGenUsageAfGC;
    }

    public void setYoungGenUsageAfGC(long youngGenUsageAfGC) {
        this.youngGenUsageAfGC = youngGenUsageAfGC;
    }

    public long getYongGenSize() {
        return yongGenSize;
    }

    public void setYongGenSize(long yongGenSize) {
        this.yongGenSize = yongGenSize;
    }

    public long getOldGenUsageBfGC() {
        return oldGenUsageBfGC;
    }

    public void setOldGenUsageBfGC(long oldGenUsageBfGC) {
        this.oldGenUsageBfGC = oldGenUsageBfGC;
    }

    public long getOldGenUsageAfGC() {
        return oldGenUsageAfGC;
    }

    public void setOldGenUsageAfGC(long oldGenUsageAfGC) {
        this.oldGenUsageAfGC = oldGenUsageAfGC;
    }

    public long getOldSize() {
        return oldSize;
    }

    public void setOldSize(long oldSize) {
        this.oldSize = oldSize;
    }

    public long getHeapUsageBfGC() {
        return heapUsageBfGC;
    }

    public void setHeapUsageBfGC(long heapUsageBfGC) {
        this.heapUsageBfGC = heapUsageBfGC;
    }

    public long getHeadUsageAfGC() {
        return headUsageAfGC;
    }

    public void setHeadUsageAfGC(long headUsageAfGC) {
        this.headUsageAfGC = headUsageAfGC;
    }

    public long getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(long heapSize) {
        this.heapSize = heapSize;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getMetaspaceUsageBfGC() {
        return metaspaceUsageBfGC;
    }

    public void setMetaspaceUsageBfGC(long metaspaceUsageBfGC) {
        this.metaspaceUsageBfGC = metaspaceUsageBfGC;
    }

    public long getMetaspaceUsageAfGC() {
        return metaspaceUsageAfGC;
    }

    public void setMetaspaceUsageAfGC(long metaspaceUsageAfGC) {
        this.metaspaceUsageAfGC = metaspaceUsageAfGC;
    }

    public long getMetaspaceSize() {
        return metaspaceSize;
    }

    public void setMetaspaceSize(long metaspaceSize) {
        this.metaspaceSize = metaspaceSize;
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
