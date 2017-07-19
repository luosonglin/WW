package com.winwin.app.UI.Entity;

public class BrokerDto {

    /**
     * userId : 17
     * roleName : 咨询经纪人,招商总监
     * realName : 韩毅
     * mobile : 13800000000
     * image : http://106.14.47.190:89/2017-01-19/avatar11484798551480default.jpg
     */

    private int userId;
    private String roleName;
    private String realName;
    private String mobile;
    private String image;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
