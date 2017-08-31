package com.winwin.android.UI.Entity;

public class CustomerDto {

    /**
     * createId : 1
     * createTime : 1494996027000
     * isDel : 0
     * updaterId : 1
     * updateTime : 1494996027000
     * id : 4
     * commendId : 1
     * commendName : admin
     * commendCustomerName : ceshi admin
     * commendCustomerInitId : 98
     * commendCustomerCurrentId : 99
     * recommendId : 39
     * recommendName : wilsin
     * commendStatus : 0
     * remarks : null
     */

    private int createId;
    private long createTime;
    private int isDel;
    private int updaterId;
    private long updateTime;
    private int id;
    private int commendId;
    private String commendName;
    private String commendCustomerName;
    private int commendCustomerInitId;
    private int commendCustomerCurrentId;
    private int recommendId;
    private String recommendName;
    private int commendStatus;
    private Object remarks;

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(int updaterId) {
        this.updaterId = updaterId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommendId() {
        return commendId;
    }

    public void setCommendId(int commendId) {
        this.commendId = commendId;
    }

    public String getCommendName() {
        return commendName;
    }

    public void setCommendName(String commendName) {
        this.commendName = commendName;
    }

    public String getCommendCustomerName() {
        return commendCustomerName;
    }

    public void setCommendCustomerName(String commendCustomerName) {
        this.commendCustomerName = commendCustomerName;
    }

    public int getCommendCustomerInitId() {
        return commendCustomerInitId;
    }

    public void setCommendCustomerInitId(int commendCustomerInitId) {
        this.commendCustomerInitId = commendCustomerInitId;
    }

    public int getCommendCustomerCurrentId() {
        return commendCustomerCurrentId;
    }

    public void setCommendCustomerCurrentId(int commendCustomerCurrentId) {
        this.commendCustomerCurrentId = commendCustomerCurrentId;
    }

    public int getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(int recommendId) {
        this.recommendId = recommendId;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public int getCommendStatus() {
        return commendStatus;
    }

    public void setCommendStatus(int commendStatus) {
        this.commendStatus = commendStatus;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }
}
