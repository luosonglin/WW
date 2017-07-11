package com.winwin.app.UI.Entity;

import java.util.List;

public class ParkDto {

    /**
     * createId : null
     * createTime : null
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 2
     * shCode : SH002
     * name : 飞乐大厦
     * shName : null
     * parkDesc : null
     * parkRemark : null
     * proName : 上海|上海市|静安区|永和路398号
     * payDay : null
     * payMon : null
     * proStand : null
     * watStand : null
     * proArea : null
     * rentArea : 11647.35
     * planImage : null
     * noteDay : null
     * state : 2
     * reviewCreateId : null
     * buildNum : 0
     * houseNum : 0
     * reviewInfo : null
     * createName : null
     * homeImage : null
     * type : 2
     * longitude : 121.449276
     * latitude : 31.295047
     * planImgs : null
     * coverImgs : [{"createId":19,"createTime":1489044654000,"isDel":0,"updaterId":null,"updateTime":null,"id":225,"dataType":3,"dataId":2,"imagePath":"http://106.14.47.190:89/2017-03-01/301488352759239.jpg","compressPath":"http://106.14.47.190:89/2017-03-01/301488352759239SM.jpg","width":601,"height":450,"suffix":"jpg","size":49862,"sortno":null,"type":null,"coord":null}]
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
    private Object createTime;
    private int isDel;
    private Object updaterId;
    private Object updateTime;
    private int id;
    private String shCode;
    private String name;
    private Object shName;
    private Object parkDesc;
    private Object parkRemark;
    private String proName;
    private Object payDay;
    private Object payMon;
    private Object proStand;
    private Object watStand;
    private Object proArea;
    private double rentArea;
    private Object planImage;
    private Object noteDay;
    private int state;
    private Object reviewCreateId;
    private int buildNum;
    private int houseNum;
    private Object reviewInfo;
    private Object createName;
    private String homeImage;
    private int type;
    private double longitude;
    private double latitude;
    private Object planImgs;
    private Object sandImgs;
    private Object adImg;
    private Object commercialId;
    private Object commercialName;
    private ReginVo city;
    private ReginVo area;
    private int officeSuperType;
    private int officeSecondType;
    private Object userLists;
    private Object currentRentNum;
    private Object dayRentStartPi;
    private String distanceMetroDesc;
    private Object peripheryDesc;
    private boolean collection;
    private boolean belongCurrentUser;
    private List<CoverImgsBean> coverImgs;

    public Object getCreateId() {
        return createId;
    }

    public void setCreateId(Object createId) {
        this.createId = createId;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
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

    public Object getShName() {
        return shName;
    }

    public void setShName(Object shName) {
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

    public Object getPayDay() {
        return payDay;
    }

    public void setPayDay(Object payDay) {
        this.payDay = payDay;
    }

    public Object getPayMon() {
        return payMon;
    }

    public void setPayMon(Object payMon) {
        this.payMon = payMon;
    }

    public Object getProStand() {
        return proStand;
    }

    public void setProStand(Object proStand) {
        this.proStand = proStand;
    }

    public Object getWatStand() {
        return watStand;
    }

    public void setWatStand(Object watStand) {
        this.watStand = watStand;
    }

    public Object getProArea() {
        return proArea;
    }

    public void setProArea(Object proArea) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public String getHomeImage() {
        return homeImage;
    }

    public void setHomeImage(String homeImage) {
        this.homeImage = homeImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Object getPlanImgs() {
        return planImgs;
    }

    public void setPlanImgs(Object planImgs) {
        this.planImgs = planImgs;
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

    public ReginVo getCity() {
        return city;
    }

    public void setCity(ReginVo city) {
        this.city = city;
    }

    public ReginVo getArea() {
        return area;
    }

    public void setArea(ReginVo area) {
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

    public List<CoverImgsBean> getCoverImgs() {
        return coverImgs;
    }

    public void setCoverImgs(List<CoverImgsBean> coverImgs) {
        this.coverImgs = coverImgs;
    }

    public static class CoverImgsBean {
        /**
         * createId : 19
         * createTime : 1489044654000
         * isDel : 0
         * updaterId : null
         * updateTime : null
         * id : 225
         * dataType : 3
         * dataId : 2
         * imagePath : http://106.14.47.190:89/2017-03-01/301488352759239.jpg
         * compressPath : http://106.14.47.190:89/2017-03-01/301488352759239SM.jpg
         * width : 601
         * height : 450
         * suffix : jpg
         * size : 49862
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
