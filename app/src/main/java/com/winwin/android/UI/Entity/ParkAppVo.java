package com.winwin.android.UI.Entity;

import java.util.List;

/**
 * 发布项目
 */
public class ParkAppVo {

    /**
     * appCoverImgs : [{"compressPath":"string","coord":"string","createId":0,"createTime":"2017-07-08T01:21:07.423Z","dataId":0,"dataType":"string","height":0,"id":0,"imagePath":"string","isDel":0,"size":0,"sortno":0,"suffix":"string","type":"string","updateTime":"2017-07-08T01:21:07.424Z","updaterId":0,"width":0}]
     * area : {"id":0,"name":"string"}
     * city : {"id":0,"name":"string"}
     * createId : 0
     * createTime : 2017-07-08T01:21:07.424Z
     * id : 0
     * isDel : 0
     * officeSecondType : 0
     * officeSuperType : 0
     * parkDesc : string
     * payDay : 0
     * payMon : 0
     * proName : string
     * proStand : 0
     * updateTime : 2017-07-08T01:21:07.424Z
     * updaterId : 0
     * watStand : 0
     */

    private ReginVo area;
    private ReginVo city;
    private int createId;
    private String createTime;
    private int id;
    private int isDel;
    private int officeSecondType;
    private int officeSuperType;
    private String parkDesc;
    private int payDay;
    private int payMon;
    private String proName;
    private int proStand;
    private String updateTime;
    private int updaterId;
    private int watStand;
    private List<FileDto> appCoverImgs;

    public ReginVo getArea() {
        return area;
    }

    public void setArea(ReginVo area) {
        this.area = area;
    }

    public ReginVo getCity() {
        return city;
    }

    public void setCity(ReginVo city) {
        this.city = city;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getOfficeSecondType() {
        return officeSecondType;
    }

    public void setOfficeSecondType(int officeSecondType) {
        this.officeSecondType = officeSecondType;
    }

    public int getOfficeSuperType() {
        return officeSuperType;
    }

    public void setOfficeSuperType(int officeSuperType) {
        this.officeSuperType = officeSuperType;
    }

    public String getParkDesc() {
        return parkDesc;
    }

    public void setParkDesc(String parkDesc) {
        this.parkDesc = parkDesc;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public int getPayMon() {
        return payMon;
    }

    public void setPayMon(int payMon) {
        this.payMon = payMon;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProStand() {
        return proStand;
    }

    public void setProStand(int proStand) {
        this.proStand = proStand;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(int updaterId) {
        this.updaterId = updaterId;
    }

    public int getWatStand() {
        return watStand;
    }

    public void setWatStand(int watStand) {
        this.watStand = watStand;
    }

    public List<FileDto> getAppCoverImgs() {
        return appCoverImgs;
    }

    public void setAppCoverImgs(List<FileDto> appCoverImgs) {
        this.appCoverImgs = appCoverImgs;
    }

    public static class AppCoverImgsBean {
        /**
         * compressPath : string
         * coord : string
         * createId : 0
         * createTime : 2017-07-08T01:21:07.423Z
         * dataId : 0
         * dataType : string
         * height : 0
         * id : 0
         * imagePath : string
         * isDel : 0
         * size : 0
         * sortno : 0
         * suffix : string
         * type : string
         * updateTime : 2017-07-08T01:21:07.424Z
         * updaterId : 0
         * width : 0
         */

        private String compressPath;
        private String coord;
        private int createId;
        private String createTime;
        private int dataId;
        private String dataType;
        private int height;
        private int id;
        private String imagePath;
        private int isDel;
        private int size;
        private int sortno;
        private String suffix;
        private String type;
        private String updateTime;
        private int updaterId;
        private int width;

        public String getCompressPath() {
            return compressPath;
        }

        public void setCompressPath(String compressPath) {
            this.compressPath = compressPath;
        }

        public String getCoord() {
            return coord;
        }

        public void setCoord(String coord) {
            this.coord = coord;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSortno() {
            return sortno;
        }

        public void setSortno(int sortno) {
            this.sortno = sortno;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdaterId() {
            return updaterId;
        }

        public void setUpdaterId(int updaterId) {
            this.updaterId = updaterId;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
