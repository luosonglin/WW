package com.winwin.app.UI.Entity;

/**
 * SelectRequirementVo {
 areaId (integer, optional): 目标区域id ,
 belongNetId (integer, optional): 行业类型Id ,
 followDesc (boolean, optional): 关注度降序 ,
 publicDateDesc (boolean, optional): 发布时间降序 ,
 requireAreaRangId (integer, optional): 面积区间Id
 }
 */
public class SelectRequirementVo {

    /**
     * areaId : 0
     * belongNetId : 0
     * followDesc : true
     * publicDateDesc : true
     * requireAreaRangId : 0
     */

    private int areaId;
    private int belongNetId;
    private boolean followDesc;
    private boolean publicDateDesc;
    private int requireAreaRangId;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getBelongNetId() {
        return belongNetId;
    }

    public void setBelongNetId(int belongNetId) {
        this.belongNetId = belongNetId;
    }

    public boolean isFollowDesc() {
        return followDesc;
    }

    public void setFollowDesc(boolean followDesc) {
        this.followDesc = followDesc;
    }

    public boolean isPublicDateDesc() {
        return publicDateDesc;
    }

    public void setPublicDateDesc(boolean publicDateDesc) {
        this.publicDateDesc = publicDateDesc;
    }

    public int getRequireAreaRangId() {
        return requireAreaRangId;
    }

    public void setRequireAreaRangId(int requireAreaRangId) {
        this.requireAreaRangId = requireAreaRangId;
    }
}
