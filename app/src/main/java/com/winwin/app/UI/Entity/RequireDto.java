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
    private Object belongIndustryDisPlay;
    private int requireAreaRangId;
    private Object requireAreaRangDisplay;
    private String requireAreaIds;
    private Object requireAreaIdArray;
    private String requireAreaName;
    private String otherInfo;
    private int browseNum;
    private int pubReqManDesc;
    private Object pubReqManDescInfo;
    private int followNum;
    private String requireTitle;
    private Object requireBanner;
    private Object collect;
    private List<EffectImgsBean> effectImgs;

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

    public Object getBelongIndustryDisPlay() {
        return belongIndustryDisPlay;
    }

    public void setBelongIndustryDisPlay(Object belongIndustryDisPlay) {
        this.belongIndustryDisPlay = belongIndustryDisPlay;
    }

    public int getRequireAreaRangId() {
        return requireAreaRangId;
    }

    public void setRequireAreaRangId(int requireAreaRangId) {
        this.requireAreaRangId = requireAreaRangId;
    }

    public Object getRequireAreaRangDisplay() {
        return requireAreaRangDisplay;
    }

    public void setRequireAreaRangDisplay(Object requireAreaRangDisplay) {
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

    public Object getPubReqManDescInfo() {
        return pubReqManDescInfo;
    }

    public void setPubReqManDescInfo(Object pubReqManDescInfo) {
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

    public Object getCollect() {
        return collect;
    }

    public void setCollect(Object collect) {
        this.collect = collect;
    }

    public List<EffectImgsBean> getEffectImgs() {
        return effectImgs;
    }

    public void setEffectImgs(List<EffectImgsBean> effectImgs) {
        this.effectImgs = effectImgs;
    }

    public static class EffectImgsBean {
        /**
         * createId : 1
         * createTime : 1498632497000
         * isDel : 0
         * updaterId : null
         * updateTime : null
         * id : 513
         * dataType : 8
         * dataId : 1
         * imagePath : http://106.14.0.72:8887/2017-06-27/431498557470227.png
         * compressPath : http://106.14.0.72:8887/2017-06-27/431498557470227SM.png
         * width : 950
         * height : 480
         * suffix : png
         * size : 41957
         * sortno : null
         * type : null
         * coord : null
         */

        private int createId;
        private long createTime;
        private int isDel;
        private Object updaterId;
        private Object updateTime;
        private int id;
        private int dataType;
        private int dataId;
        private String imagePath;
        private String compressPath;
        private int width;
        private int height;
        private String suffix;
        private int size;
        private Object sortno;
        private Object type;
        private Object coord;

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

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getCompressPath() {
            return compressPath;
        }

        public void setCompressPath(String compressPath) {
            this.compressPath = compressPath;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Object getSortno() {
            return sortno;
        }

        public void setSortno(Object sortno) {
            this.sortno = sortno;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getCoord() {
            return coord;
        }

        public void setCoord(Object coord) {
            this.coord = coord;
        }
    }
}
