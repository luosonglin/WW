package com.winwin.android.UI.Entity;

public class IndexBannerDto {
    /**
     * createId : null
     * createTime : 1497928061000
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 1
     * bannerType : 1
     * bannerOrder : 0
     * bannerPath : http://106.14.47.190:89/2017-02-28/481488291874917.jpg
     *  "isGravity": 1,
     "bannerActionType": 1,
     "bannerActionVal": "http://www.shlingang.com/"
     */

    private Object createId;
    private long createTime;
    private int isDel;
    private Object updaterId;
    private long updateTime;
    private int id;
    private int bannerType;
    private int bannerOrder;
    private String bannerPath;
    private int isGravity;
    private int bannerActionType;
    private String bannerActionVal;

    public Object getCreateId() {
        return createId;
    }

    public void setCreateId(Object createId) {
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

    public Object getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Object updaterId) {
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

    public int getBannerType() {
        return bannerType;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }

    public int getBannerOrder() {
        return bannerOrder;
    }

    public void setBannerOrder(int bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public int getIsGravity() {
        return isGravity;
    }

    public void setIsGravity(int isGravity) {
        this.isGravity = isGravity;
    }

    public int getBannerActionType() {
        return bannerActionType;
    }

    public void setBannerActionType(int bannerActionType) {
        this.bannerActionType = bannerActionType;
    }

    public String getBannerActionVal() {
        return bannerActionVal;
    }

    public void setBannerActionVal(String bannerActionVal) {
        this.bannerActionVal = bannerActionVal;
    }
}
