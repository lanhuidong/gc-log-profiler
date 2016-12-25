package com.nexusy.glp.data.basic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lanhuidong
 * @since 2016-12-24
 */
public class CommandLineData {

    private GCName youngGCName;
    private GCName oldGCName;

    @JsonProperty("young_gc_name")
    public GCName getYoungGCName() {
        return youngGCName;
    }

    public void setYoungGCName(GCName youngGCName) {
        this.youngGCName = youngGCName;
    }

    @JsonProperty("old_gc_name")
    public GCName getOldGCName() {
        return oldGCName;
    }

    public void setOldGCName(GCName oldGCName) {
        this.oldGCName = oldGCName;
    }
}
