package com.winwin.app.UI.Entity;

import java.util.List;

public class ParkDetailDto {

    /**
     * usingAreaPercent : 0
     * checkInCustomers : 0
     * parkVo : {"createId":37,"createTime":1493111165000,"isDel":0,"updaterId":null,"updateTime":null,"id":9,"shCode":"SH009","name":"松江大学城测试","shName":"松江大学城测试a","parkDesc":null,"parkRemark":null,"proName":"松江区松江大学城123号","payDay":2,"payMon":60,"proStand":1.5,"watStand":1.5,"proArea":280,"rentArea":200,"planImage":null,"noteDay":15,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":"艾yi","homeImage":"http://106.14.47.190:89/2017-04-25/301493111110297.png","type":1,"longitude":121.465361,"latitude":31.252954,"planImgs":null,"coverImgs":null,"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":{"id":310100,"name":"上海市","parent":{"id":310000,"name":"上海","parent":null}},"area":{"id":310117,"name":"松江区","parent":null},"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离8号线中兴路(地铁站)350米","peripheryDesc":null,"collection":false,"belongCurrentUser":false}
     * shareUrl : http://www.baidu.com?parkId=9
     */

    private double usingAreaPercent;
    private int checkInCustomers;
    private ParkVoBean parkVo;

    public double getUsingAreaPercent() {
        return usingAreaPercent;
    }

    public void setUsingAreaPercent(double usingAreaPercent) {
        this.usingAreaPercent = usingAreaPercent;
    }

    public int getCheckInCustomers() {
        return checkInCustomers;
    }

    public void setCheckInCustomers(int checkInCustomers) {
        this.checkInCustomers = checkInCustomers;
    }

    public ParkVoBean getParkVo() {
        return parkVo;
    }

    public void setParkVo(ParkVoBean parkVo) {
        this.parkVo = parkVo;
    }

    public static class ParkVoBean {
        /**
         * createId : 37
         * createTime : 1493111165000
         * isDel : 0
         * updaterId : null
         * updateTime : null
         * id : 9
         * shCode : SH009
         * name : 松江大学城测试
         * shName : 松江大学城测试a
         * parkDesc : null
         * parkRemark : null
         * proName : 松江区松江大学城123号
         * payDay : 2
         * payMon : 60
         * proStand : 1.5
         * watStand : 1.5
         * proArea : 280
         * rentArea : 200
         * planImage : null
         * noteDay : 15
         * state : 2
         * reviewCreateId : null
         * buildNum : 0
         * houseNum : 0
         * reviewInfo : null
         * createName : 艾yi
         * homeImage : http://106.14.47.190:89/2017-04-25/301493111110297.png
         * type : 1
         * longitude : 121.465361
         * latitude : 31.252954
         * planImgs : null
         * coverImgs : null
         * sandImgs : null
         * adImg : null
         * commercialId : null
         * commercialName : null
         * city : {"id":310100,"name":"上海市","parent":{"id":310000,"name":"上海","parent":null}}
         * area : {"id":310117,"name":"松江区","parent":null}
         * officeSuperType : 1
         * officeSecondType : 1
         * userLists : null
         * currentRentNum : null
         * dayRentStartPi : null
         * distanceMetroDesc : 距离8号线中兴路(地铁站)350米
         * peripheryDesc : null
         * collection : false
         * belongCurrentUser : false
         */

        private int createId;
        private long createTime;
        private int isDel;
        private Object updaterId;
        private Object updateTime;
        private int id;
        private String shCode;
        private String name;
        private String shName;
        private String parkDesc;
        private Object parkRemark;
        private String proName;
        private double payDay;
        private double payMon;
        private double proStand;
        private double watStand;
        private double proArea;
        private double rentArea;
        private Object planImage;
        private int noteDay;
        private int state;
        private Object reviewCreateId;
        private int buildNum;
        private int houseNum;
        private Object reviewInfo;
        private String createName;
        private String homeImage;
        private int type;
        private double longitude;
        private double latitude;
        private Object planImgs;
        private List<FileDto> coverImgs;
        private Object sandImgs;
        private Object adImg;
        private Object commercialId;
        private Object commercialName;
        private CityBean city;
        private AreaBean area;
        private int officeSuperType;
        private int officeSecondType;
        private Object userLists;
        private Object currentRentNum;
        private Object dayRentStartPi;
        private String distanceMetroDesc;
        private String peripheryDesc;
        private boolean collection;
        private boolean belongCurrentUser;
        private String shareUrl;

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

        public String getParkDesc() {
            return parkDesc;
        }

        public void setParkDesc(String parkDesc) {
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

        public double getPayMon() {
            return payMon;
        }

        public void setPayMon(double payMon) {
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

        public int getNoteDay() {
            return noteDay;
        }

        public void setNoteDay(int noteDay) {
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

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
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

        public List<FileDto> getCoverImgs() {
            return coverImgs;
        }

        public void setCoverImgs(List<FileDto> coverImgs) {
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

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
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

        public String getPeripheryDesc() {
            return peripheryDesc;
        }

        public void setPeripheryDesc(String peripheryDesc) {
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


        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public static class CityBean {
            /**
             * id : 310100
             * name : 上海市
             * parent : {"id":310000,"name":"上海","parent":null}
             */

            private int id;
            private String name;
            private ParentBean parent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ParentBean getParent() {
                return parent;
            }

            public void setParent(ParentBean parent) {
                this.parent = parent;
            }

            public static class ParentBean {
                /**
                 * id : 310000
                 * name : 上海
                 * parent : null
                 */

                private int id;
                private String name;
                private Object parent;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getParent() {
                    return parent;
                }

                public void setParent(Object parent) {
                    this.parent = parent;
                }
            }
        }

        public static class AreaBean {
            /**
             * id : 310117
             * name : 松江区
             * parent : null
             */

            private int id;
            private String name;
            private Object parent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getParent() {
                return parent;
            }

            public void setParent(Object parent) {
                this.parent = parent;
            }
        }
    }
}
