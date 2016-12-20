package com.nexusy.glp.data.basic;

import java.util.Collections;
import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
public class BasicData {

    private List<SerialGCData> serialGCDatas;

    private List<ParallelGCData> parallelGCDatas;

    private List<ParNewData> parNewDatas;

    private List<CMSInitialMarkData> cmsInitialMarkDatas;

    private List<CMSConcurrentData> cmsConcurrentDatas;

    private List<CMSFinalRemarkData> cmsFinalRemarkDatas;

    public List<SerialGCData> getSerialGCDatas() {
        if (serialGCDatas == null) {
            return Collections.emptyList();
        }
        return serialGCDatas;
    }

    public void setSerialGCDatas(List<SerialGCData> serialGCDatas) {
        this.serialGCDatas = serialGCDatas;
    }

    public List<ParallelGCData> getParallelGCDatas() {
        if (parallelGCDatas == null) {
            return Collections.emptyList();
        }
        return parallelGCDatas;
    }

    public void setParallelGCDatas(List<ParallelGCData> parallelGCDatas) {
        this.parallelGCDatas = parallelGCDatas;
    }

    public List<ParNewData> getParNewDatas() {
        if (parNewDatas == null) {
            return Collections.emptyList();
        }
        return parNewDatas;
    }

    public void setParNewDatas(List<ParNewData> parNewDatas) {
        this.parNewDatas = parNewDatas;
    }

    public List<CMSInitialMarkData> getCmsInitialMarkDatas() {
        if (cmsInitialMarkDatas == null) {
            return Collections.emptyList();
        }
        return cmsInitialMarkDatas;
    }

    public void setCmsInitialMarkDatas(List<CMSInitialMarkData> cmsInitialMarkDatas) {
        this.cmsInitialMarkDatas = cmsInitialMarkDatas;
    }

    public List<CMSConcurrentData> getCmsConcurrentDatas() {
        if (cmsConcurrentDatas == null) {
            return Collections.emptyList();
        }
        return cmsConcurrentDatas;
    }

    public void setCmsConcurrentDatas(List<CMSConcurrentData> cmsConcurrentDatas) {
        this.cmsConcurrentDatas = cmsConcurrentDatas;
    }

    public List<CMSFinalRemarkData> getCmsFinalRemarkDatas() {
        if (cmsFinalRemarkDatas == null) {
            return Collections.emptyList();
        }
        return cmsFinalRemarkDatas;
    }

    public void setCmsFinalRemarkDatas(List<CMSFinalRemarkData> cmsFinalRemarkDatas) {
        this.cmsFinalRemarkDatas = cmsFinalRemarkDatas;
    }
}
