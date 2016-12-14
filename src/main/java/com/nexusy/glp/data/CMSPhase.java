package com.nexusy.glp.data;

/**
 * @author lanhuidong
 * @since 2016-12-14
 */
public enum CMSPhase {

    InitialMark("CMS Initial Mark"),
    ConcurrentMark("CMS-concurrent-mark"),
    Preclean("CMS-concurrent-preclean"),
    AbortablePreclean("CMS-concurrent-abortable-preclean"),
    FinalRemark("CMS Final Remark"),
    Sweep("CMS-concurrent-sweep"),
    Reset("CMS-concurrent-reset");

    private String phase;

    CMSPhase(String phase) {
        this.phase = phase;
    }

    public static CMSPhase string2Enum(String phase) {
        CMSPhase result = null;
        switch (phase) {
            case "CMS Initial Mark":
                result = InitialMark;
                break;
            case "CMS-concurrent-mark":
                result = ConcurrentMark;
                break;
            case "CMS-concurrent-preclean":
                result = Preclean;
                break;
            case "CMS-concurrent-abortable-preclean":
                result = AbortablePreclean;
                break;
            case "CMS Final Remark":
                result = FinalRemark;
                break;
            case "CMS-concurrent-sweep":
                result = Sweep;
                break;
            case "CMS-concurrent-reset":
                result = Reset;
                break;
            default:
        }
        return result;
    }

    @Override
    public String toString() {
        return phase;
    }
}
