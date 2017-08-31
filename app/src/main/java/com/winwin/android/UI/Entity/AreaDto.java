package com.winwin.android.UI.Entity;

public class AreaDto {

    /**
     * createId : null
     * createTime : 1497648949000
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 1
     * areaId : 310106
     * sort : 0
     * areaName : 静安
     * longitude : 121.449276
     * latitude : 31.295047
     */

    private Object createId;
    private long createTime;
    private int isDel;
    private Object updaterId;
    private Object updateTime;
    private int id;
    private int areaId;
    private int sort;
    private String areaName;
    private double longitude;
    private double latitude;

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

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
