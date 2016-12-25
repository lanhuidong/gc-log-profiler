package com.nexusy.glp.data.basic;

import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class BasicData {

    private CommandLineData commandLineData;

    private List<SerialGCData> serialGCDatas;

    private List<ParallelGCData> parallelGCDatas;

    private List<ParNewData> parNewDatas;

    private List<CMSInitialMarkData> cmsInitialMarkDatas;

    private List<CMSConcurrentData> cmsConcurrentDatas;

    private List<CMSFinalRemarkData> cmsFinalRemarkDatas;

    public CommandLineData getCommandLineData() {
        return commandLineData;
    }

    public void setCommandLineData(CommandLineData commandLineData) {
        this.commandLineData = commandLineData;
    }

    public List<SerialGCData> getSerialGCDatas() {
        return serialGCDatas;
    }

    public void setSerialGCDatas(List<SerialGCData> serialGCDatas) {
        this.serialGCDatas = serialGCDatas;
    }

    public List<ParallelGCData> getParallelGCDatas() {
        return parallelGCDatas;
    }

    public void setParallelGCDatas(List<ParallelGCData> parallelGCDatas) {
        this.parallelGCDatas = parallelGCDatas;
    }

    public List<ParNewData> getParNewDatas() {
        return parNewDatas;
    }

    public void setParNewDatas(List<ParNewData> parNewDatas) {
        this.parNewDatas = parNewDatas;
    }

    public List<CMSInitialMarkData> getCmsInitialMarkDatas() {
        return cmsInitialMarkDatas;
    }

    public void setCmsInitialMarkDatas(List<CMSInitialMarkData> cmsInitialMarkDatas) {
        this.cmsInitialMarkDatas = cmsInitialMarkDatas;
    }

    public List<CMSConcurrentData> getCmsConcurrentDatas() {
        return cmsConcurrentDatas;
    }

    public void setCmsConcurrentDatas(List<CMSConcurrentData> cmsConcurrentDatas) {
        this.cmsConcurrentDatas = cmsConcurrentDatas;
    }

    public List<CMSFinalRemarkData> getCmsFinalRemarkDatas() {
        return cmsFinalRemarkDatas;
    }

    public void setCmsFinalRemarkDatas(List<CMSFinalRemarkData> cmsFinalRemarkDatas) {
        this.cmsFinalRemarkDatas = cmsFinalRemarkDatas;
    }
}
