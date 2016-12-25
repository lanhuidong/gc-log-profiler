package com.nexusy.glp.data.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexusy.glp.data.basic.CommandLineData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lanhuidong
 * @since 2016-12-15
 */
public class HighLevelData {

    /**
     * 命令行标记
     */
    private CommandLineData cmdData;

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

    /**
     * key为引发GC的原因，value为次数
     */
    private Map<String, Integer> causeMap = new HashMap<>();

    @JsonProperty("cmd_data")
    public CommandLineData getCmdData() {
        return cmdData;
    }

    public void setCmdData(CommandLineData cmdData) {
        this.cmdData = cmdData;
    }

    @JsonProperty("minor_gc_duration")
    public long getMinorGCDuration() {
        return minorGCDuration;
    }

    public void setMinorGCDuration(long minorGCDuration) {
        this.minorGCDuration = minorGCDuration;
    }

    @JsonProperty("minor_gc_times")
    public long getMinorGCTimes() {
        return minorGCTimes;
    }

    public void setMinorGCTimes(long minorGCTimes) {
        this.minorGCTimes = minorGCTimes;
    }

    @JsonProperty("max_gc_pause")
    public double getMaxGCPause() {
        return maxGCPause;
    }

    public void setMaxGCPause(double maxGCPause) {
        this.maxGCPause = maxGCPause;
    }

    @JsonProperty("promotion_times")
    public long getPromotionTimes() {
        return promotionTimes;
    }

    public void setPromotionTimes(long promotionTimes) {
        this.promotionTimes = promotionTimes;
    }

    @JsonProperty("promotion_size")
    public long getPromotionSize() {
        return promotionSize;
    }

    public void setPromotionSize(long promotionSize) {
        this.promotionSize = promotionSize;
    }

    @JsonProperty("cause_map")
    public Map<String, Integer> getCauseMap() {
        return causeMap;
    }

    public void setCauseMap(Map<String, Integer> causeMap) {
        this.causeMap = causeMap;
    }
}
