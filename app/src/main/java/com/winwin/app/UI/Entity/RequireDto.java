package com.winwin.app.UI.Entity;

import java.util.List;

public class RequireDto {

    /**
     * createId : null
     * createTime : 1498603721000
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 1
     * pubRequireId : 1
     * pubRequireName : 发布需求人姓名不可为空
     * pubRequireHeaderPic : null
     * pubRequireCompany : 发布需求人公司名称不可为空
     * belongIndustryId : 2
     * belongIndustryDisPlay : null
     * requireAreaRangId : 12
     * requireAreaRangDisplay : null
     * requireAreaIds : ;310104;;310105;
     * requireAreaIdArray : null
     * requireAreaName : 徐汇区;长宁区
     * otherInfo : string
     * browseNum : 45
     * pubReqManDesc : 22
     * pubReqManDescInfo : null
     * followNum : 1
     * requireTitle : 测试发布需求标题
     * effectImgs : [{"createId":1,"createTime":1498632497000,"isDel":0,"updaterId":null,"updateTime":null,"id":513,"dataType":8,"dataId":1,"imagePath":"http://106.14.0.72:8887/2017-06-27/431498557470227.png","compressPath":"http://106.14.0.72:8887/2017-06-27/431498557470227SM.png","width":950,"height":480,"suffix":"png","size":41957,"sortno":null,"type":null,"coord":null},{"createId":1,"createTime":1498632497000,"isDel":0,"updaterId":null,"updateTime":null,"id":514,"dataType":8,"dataId":1,"imagePath":"http://106.14.0.72:8887/2017-06-27/431498557470227.png","compressPath":"http://106.14.0.72:8887/2017-06-27/431498557470227SM.png","width":950,"height":480,"suffix":"png","size":41957,"sortno":null,"type":null,"coord":null}]
     * requireBanner : null
     * collect : null
     */

    private Object createId;
    private long createTime;
    private int isDel;
    private Object updaterId;
    private Object updateTime;
    private int id;
    private int pubRequireId;
    private String pubRequireName;
    private Object pubRequireHeaderPic;
    private String pubRequireCompany;
    private int belongIndustryId;
    private String belongIndustryDisPlay;
    private int requireAreaRangId;
    private String requireAreaRangDisplay;
    private String requireAreaIds;
    private Object requireAreaIdArray;
    private String requireAreaName;
    private String otherInfo;
    private int browseNum;
    private int pubReqManDesc;
    private String pubReqManDescInfo;
    private int followNum;
    private String requireTitle;
    private Object requireBanner;
    private Boolean collect;
    private List<FileDto> effectImgs;

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

    public int getPubRequireId() {
        return pubRequireId;
    }

    public void setPubRequireId(int pubRequireId) {
        this.pubRequireId = pubRequireId;
    }

    public String getPubRequireName() {
        return pubRequireName;
    }

    public void setPubRequireName(String pubRequireName) {
        this.pubRequireName = pubRequireName;
    }

    public Object getPubRequireHeaderPic() {
        return pubRequireHeaderPic;
    }

    public void setPubRequireHeaderPic(Object pubRequireHeaderPic) {
        this.pubRequireHeaderPic = pubRequireHeaderPic;
    }

    public String getPubRequireCompany() {
        return pubRequireCompany;
    }

    public void setPubRequireCompany(String pubRequireCompany) {
        this.pubRequireCompany = pubRequireCompany;
    }

    public int getBelongIndustryId() {
        return belongIndustryId;
    }

    public void setBelongIndustryId(int belongIndustryId) {
        this.belongIndustryId = belongIndustryId;
    }

    public String getBelongIndustryDisPlay() {
        return belongIndustryDisPlay;
    }

    public void setBelongIndustryDisPlay(String belongIndustryDisPlay) {
        this.belongIndustryDisPlay = belongIndustryDisPlay;
    }

    public int getRequireAreaRangId() {
        return requireAreaRangId;
    }

    public void setRequireAreaRangId(int requireAreaRangId) {
        this.requireAreaRangId = requireAreaRangId;
    }

    public String getRequireAreaRangDisplay() {
        return requireAreaRangDisplay;
    }

    public void setRequireAreaRangDisplay(String requireAreaRangDisplay) {
        this.requireAreaRangDisplay = requireAreaRangDisplay;
    }

    public String getRequireAreaIds() {
        return requireAreaIds;
    }

    public void setRequireAreaIds(String requireAreaIds) {
        this.requireAreaIds = requireAreaIds;
    }

    public Object getRequireAreaIdArray() {
        return requireAreaIdArray;
    }

    public void setRequireAreaIdArray(Object requireAreaIdArray) {
        this.requireAreaIdArray = requireAreaIdArray;
    }

    public String getRequireAreaName() {
        return requireAreaName;
    }

    public void setRequireAreaName(String requireAreaName) {
        this.requireAreaName = requireAreaName;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public int getPubReqManDesc() {
        return pubReqManDesc;
    }

    public void setPubReqManDesc(int pubReqManDesc) {
        this.pubReqManDesc = pubReqManDesc;
    }

    public String getPubReqManDescInfo() {
        return pubReqManDescInfo;
    }

    public void setPubReqManDescInfo(String pubReqManDescInfo) {
        this.pubReqManDescInfo = pubReqManDescInfo;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getRequireTitle() {
        return requireTitle;
    }

    public void setRequireTitle(String requireTitle) {
        this.requireTitle = requireTitle;
    }

    public Object getRequireBanner() {
        return requireBanner;
    }

    public void setRequireBanner(Object requireBanner) {
        this.requireBanner = requireBanner;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public List<FileDto> getEffectImgs() {
        return effectImgs;
    }

    public void setEffectImgs(List<FileDto> effectImgs) {
        this.effectImgs = effectImgs;
    }

}
