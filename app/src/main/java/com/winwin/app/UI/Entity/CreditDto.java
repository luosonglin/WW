package com.winwin.app.UI.Entity;

import java.util.List;

public class CreditDto {

    /**
     * totalCredits : 0
     * userName : null
     * headPic : null
     * publishParkList : [{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":2,"shCode":"SH002","name":"飞乐大厦","shName":"飞乐大厦二期","parkDesc":null,"parkRemark":null,"proName":"上海|上海市|静安区|永和路398号","payDay":0.32,"payMon":10,"proStand":1.25,"watStand":5.5,"proArea":14638.6,"rentArea":11647.35,"planImage":null,"noteDay":null,"state":null,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":null,"longitude":null,"latitude":null,"planImgs":null,"coverImgs":null,"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离1号线汶水路(地铁站)292米","peripheryDesc":null,"collection":false,"belongCurrentUser":false}]
     * publishRequirementList : []
     * commendsList : [{"createId":1,"createTime":1494996405000,"isDel":0,"updaterId":1,"updateTime":1494996405000,"id":5,"commendId":1,"commendCustomerInitId":98,"commendCustomerCurrentId":100,"recommendId":14,"commendStatus":2,"remarks":null},{"createId":1,"createTime":1494997823000,"isDel":0,"updaterId":1,"updateTime":1494997823000,"id":6,"commendId":1,"commendCustomerInitId":98,"commendCustomerCurrentId":101,"recommendId":71,"commendStatus":2,"remarks":null}]
     */

    private int totalCredits;
    private Object userName;
    private Object headPic;
    private List<PublishParkListBean> publishParkList;
    private List<PublishRequirementList> publishRequirementList;
    private List<CommendsListBean> commendsList;

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getHeadPic() {
        return headPic;
    }

    public void setHeadPic(Object headPic) {
        this.headPic = headPic;
    }

    public List<PublishParkListBean> getPublishParkList() {
        return publishParkList;
    }

    public void setPublishParkList(List<PublishParkListBean> publishParkList) {
        this.publishParkList = publishParkList;
    }

    public List<PublishRequirementList> getPublishRequirementList() {
        return publishRequirementList;
    }

    public void setPublishRequirementList(List<PublishRequirementList> publishRequirementList) {
        this.publishRequirementList = publishRequirementList;
    }

    public List<CommendsListBean> getCommendsList() {
        return commendsList;
    }

    public void setCommendsList(List<CommendsListBean> commendsList) {
        this.commendsList = commendsList;
    }

    public static class PublishParkListBean {
        /**
         * createId : null
         * createTime : null
         * isDel : 0
         * updaterId : null
         * updateTime : null
         * id : 2
         * shCode : SH002
         * name : 飞乐大厦
         * shName : 飞乐大厦二期
         * parkDesc : null
         * parkRemark : null
         * proName : 上海|上海市|静安区|永和路398号
         * payDay : 0.32
         * payMon : 10
         * proStand : 1.25
         * watStand : 5.5
         * proArea : 14638.6
         * rentArea : 11647.35
         * planImage : null
         * noteDay : null
         * state : null
         * reviewCreateId : null
         * buildNum : 0
         * houseNum : 0
         * reviewInfo : null
         * createName : null
         * homeImage : null
         * type : null
         * longitude : null
         * latitude : null
         * planImgs : null
         * coverImgs : null
         * sandImgs : null
         * adImg : null
         * commercialId : null
         * commercialName : null
         * city : null
         * area : null
         * officeSuperType : 1
         * officeSecondType : 1
         * userLists : null
         * currentRentNum : null
         * dayRentStartPi : null
         * distanceMetroDesc : 距离1号线汶水路(地铁站)292米
         * peripheryDesc : null
         * collection : false
         * belongCurrentUser : false
         */

        private Object createId;
        private long createTime;
        private int isDel;
        private Object updaterId;
        private Object updateTime;
        private int id;
        private String shCode;
        private String name;
        private String shName;
        private Object parkDesc;
        private Object parkRemark;
        private String proName;
        private double payDay;
        private int payMon;
        private double proStand;
        private double watStand;
        private double proArea;
        private double rentArea;
        private Object planImage;
        private Object noteDay;
        private Object state;
        private Object reviewCreateId;
        private int buildNum;
        private int houseNum;
        private Object reviewInfo;
        private Object createName;
        private Object homeImage;
        private Object type;
        private Object longitude;
        private Object latitude;
        private Object planImgs;
        private Object coverImgs;
        private Object sandImgs;
        private Object adImg;
        private Object commercialId;
        private Object commercialName;
        private Object city;
        private Object area;
        private int officeSuperType;
        private int officeSecondType;
        private Object userLists;
        private Object currentRentNum;
        private Object dayRentStartPi;
        private String distanceMetroDesc;
        private Object peripheryDesc;
        private boolean collection;
        private boolean belongCurrentUser;

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

        public String getShCode() {
            return shCode;
        }

        public void setShCode(String shCode) {
            this.shCode = shCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShName() {
            return shName;
        }

        public void setShName(String shName) {
            this.shName = shName;
        }

        public Object getParkDesc() {
            return parkDesc;
        }

        public void setParkDesc(Object parkDesc) {
            this.parkDesc = parkDesc;
        }

        public Object getParkRemark() {
            return parkRemark;
        }

        public void setParkRemark(Object parkRemark) {
            this.parkRemark = parkRemark;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public double getPayDay() {
            return payDay;
        }

        public void setPayDay(double payDay) {
            this.payDay = payDay;
        }

        public int getPayMon() {
            return payMon;
        }

        public void setPayMon(int payMon) {
            this.payMon = payMon;
        }

        public double getProStand() {
            return proStand;
        }

        public void setProStand(double proStand) {
            this.proStand = proStand;
        }

        public double getWatStand() {
            return watStand;
        }

        public void setWatStand(double watStand) {
            this.watStand = watStand;
        }

        public double getProArea() {
            return proArea;
        }

        public void setProArea(double proArea) {
            this.proArea = proArea;
        }

        public double getRentArea() {
            return rentArea;
        }

        public void setRentArea(double rentArea) {
            this.rentArea = rentArea;
        }

        public Object getPlanImage() {
            return planImage;
        }

        public void setPlanImage(Object planImage) {
            this.planImage = planImage;
        }

        public Object getNoteDay() {
            return noteDay;
        }

        public void setNoteDay(Object noteDay) {
            this.noteDay = noteDay;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getReviewCreateId() {
            return reviewCreateId;
        }

        public void setReviewCreateId(Object reviewCreateId) {
            this.reviewCreateId = reviewCreateId;
        }

        public int getBuildNum() {
            return buildNum;
        }

        public void setBuildNum(int buildNum) {
            this.buildNum = buildNum;
        }

        public int getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(int houseNum) {
            this.houseNum = houseNum;
        }

        public Object getReviewInfo() {
            return reviewInfo;
        }

        public void setReviewInfo(Object reviewInfo) {
            this.reviewInfo = reviewInfo;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }

        public Object getHomeImage() {
            return homeImage;
        }

        public void setHomeImage(Object homeImage) {
            this.homeImage = homeImage;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getPlanImgs() {
            return planImgs;
        }

        public void setPlanImgs(Object planImgs) {
            this.planImgs = planImgs;
        }

        public Object getCoverImgs() {
            return coverImgs;
        }

        public void setCoverImgs(Object coverImgs) {
            this.coverImgs = coverImgs;
        }

        public Object getSandImgs() {
            return sandImgs;
        }

        public void setSandImgs(Object sandImgs) {
            this.sandImgs = sandImgs;
        }

        public Object getAdImg() {
            return adImg;
        }

        public void setAdImg(Object adImg) {
            this.adImg = adImg;
        }

        public Object getCommercialId() {
            return commercialId;
        }

        public void setCommercialId(Object commercialId) {
            this.commercialId = commercialId;
        }

        public Object getCommercialName() {
            return commercialName;
        }

        public void setCommercialName(Object commercialName) {
            this.commercialName = commercialName;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public int getOfficeSuperType() {
            return officeSuperType;
        }

        public void setOfficeSuperType(int officeSuperType) {
            this.officeSuperType = officeSuperType;
        }

        public int getOfficeSecondType() {
            return officeSecondType;
        }

        public void setOfficeSecondType(int officeSecondType) {
            this.officeSecondType = officeSecondType;
        }

        public Object getUserLists() {
            return userLists;
        }

        public void setUserLists(Object userLists) {
            this.userLists = userLists;
        }

        public Object getCurrentRentNum() {
            return currentRentNum;
        }

        public void setCurrentRentNum(Object currentRentNum) {
            this.currentRentNum = currentRentNum;
        }

        public Object getDayRentStartPi() {
            return dayRentStartPi;
        }

        public void setDayRentStartPi(Object dayRentStartPi) {
            this.dayRentStartPi = dayRentStartPi;
        }

        public String getDistanceMetroDesc() {
            return distanceMetroDesc;
        }

        public void setDistanceMetroDesc(String distanceMetroDesc) {
            this.distanceMetroDesc = distanceMetroDesc;
        }

        public Object getPeripheryDesc() {
            return peripheryDesc;
        }

        public void setPeripheryDesc(Object peripheryDesc) {
            this.peripheryDesc = peripheryDesc;
        }

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public boolean isBelongCurrentUser() {
            return belongCurrentUser;
        }

        public void setBelongCurrentUser(boolean belongCurrentUser) {
            this.belongCurrentUser = belongCurrentUser;
        }
    }

    public static class PublishRequirementList {

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
         * effectImgs : null
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
        private Object effectImgs;
        private Object requireBanner;
        private Object collect;

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

        public Object getEffectImgs() {
            return effectImgs;
        }

        public void setEffectImgs(Object effectImgs) {
            this.effectImgs = effectImgs;
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
    }
    public static class CommendsListBean {
        /**
         * createId : 1
         * createTime : 1494996405000
         * isDel : 0
         * updaterId : 1
         * updateTime : 1494996405000
         * id : 5
         * commendId : 1
         * commendCustomerInitId : 98
         * commendCustomerCurrentId : 100
         * recommendId : 14
         * commendStatus : 2
         * remarks : null
         */

        private int createId;
        private long createTime;
        private int isDel;
        private int updaterId;
        private long updateTime;
        private int id;
        private int commendId;
        private int commendCustomerInitId;
        private int commendCustomerCurrentId;
        private int recommendId;
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
}
