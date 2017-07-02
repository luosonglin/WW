package com.winwin.app.UI.Entity;

/**
 * 平台元数据信息
 */
public class MetaDataDto {

    /**
     * createId : null
     * createTime : 1497408839000
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 2
     * metaType : 1
     * dataDisplay : 房地产/建筑
     * sort : 1
     * leftData : 0
     * rightData : 0
     */

    private Object createId;
    private long createTime;
    private int isDel;
    private Object updaterId;
    private Object updateTime;
    private int id;
    private int metaType;
    private String dataDisplay;
    private int sort;
    private int leftData;
    private int rightData;

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

    public int getMetaType() {
        return metaType;
    }

    public void setMetaType(int metaType) {
        this.metaType = metaType;
    }

    public String getDataDisplay() {
        return dataDisplay;
    }

    public void setDataDisplay(String dataDisplay) {
        this.dataDisplay = dataDisplay;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getLeftData() {
        return leftData;
    }

    public void setLeftData(int leftData) {
        this.leftData = leftData;
    }

    public int getRightData() {
        return rightData;
    }

    public void setRightData(int rightData) {
        this.rightData = rightData;
    }
}
