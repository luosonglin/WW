package com.winwin.android.UI.Entity;

/**
 * 我的推荐，接受或拒绝
 */
public class CommendCustomerVo {

    /**
     * commendStatus : 1
     * dataId : 7
     * remarks : string
     */

    private int commendStatus;
    private int dataId;
    private String remarks;

    public CommendCustomerVo(int commendStatus, int dataId, String remarks) {
        this.commendStatus = commendStatus;
        this.dataId = dataId;
        this.remarks = remarks;
    }

    public int getCommendStatus() {
        return commendStatus;
    }

    public void setCommendStatus(int commendStatus) {
        this.commendStatus = commendStatus;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
