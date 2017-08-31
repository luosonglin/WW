package com.winwin.android.UI.Entity;

public class MyInfoDto {

    /**
     * totalCredits : 0
     * userName : admin
     * headPic : http://172.18.84.241:88/2017-05-27/avatar491495847225001.png
     * publishParkList : null
     * publishRequirementList : null
     * commendsList : null
     */

    private int totalCredits;
    private String userName;
    private String headPic;
    private Object publishParkList;
    private Object publishRequirementList;
    private Object commendsList;

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Object getPublishParkList() {
        return publishParkList;
    }

    public void setPublishParkList(Object publishParkList) {
        this.publishParkList = publishParkList;
    }

    public Object getPublishRequirementList() {
        return publishRequirementList;
    }

    public void setPublishRequirementList(Object publishRequirementList) {
        this.publishRequirementList = publishRequirementList;
    }

    public Object getCommendsList() {
        return commendsList;
    }

    public void setCommendsList(Object commendsList) {
        this.commendsList = commendsList;
    }
}
