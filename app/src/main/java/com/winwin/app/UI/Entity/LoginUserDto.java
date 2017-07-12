package com.winwin.app.UI.Entity;

import java.util.List;

public class LoginUserDto {

    /**
     * createId : null
     * createTime : null
     * isDel : 0
     * updaterId : null
     * updateTime : null
     * id : 1
     * username : admin
     * password : e412d37138d368d37e9a9405847bf81384f2c10a
     * name : admin
     * realName : admin
     * mobile : 13632602163
     * birth : 1137024000000
     * state : 0
     * loginTime : 1499477031449
     * image : http://106.14.0.72:8887/2017-07-07/311499397259568.png
     * superior : null
     * superiorName : null
     * imageFile : null
     * userRoleS : [{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":5,"name":"平台管理员","description":"win win平台负责人","state":null,"authorityIds":null,"userCount":0,"authority":null,"category":1}]
     * roleIds : null
     * errLoginNum : 0
     * isLockTime : null
     * userCard : null
     * cardType : null
     * parkRolesVoList : null
     * token : 443500e23c62450797d2104e3f44e9f8App#1
     * parkIds : null
     * "userSig": "eJxtj82OgjAYRd*lW43TFkEwcYF1IkYcw-jXsGkqFKc1I03pGH-iuw*i7lx*59zk3u8KlvGiw7WWOeOWOSYHfYC6EELHRQ4G7caLk5ZGMF5YYe7edV1cR55W5uJgZSEf7gkruauv2WdCJqSlJhuCzHyDt9*xomEZnQIyigvrUZrQiH-tiI78FO3HoQwJ-Eh9D01RZY-zMl5hFdItNrM1v-yo45lXcL0cjtNWppLB4FWW71nzw7vxVv6KhgeBjz2v9*I8y8q-g2X2rB8e4i64-QMt*VAQ"
     */

    private Object createId;
    private Object createTime;
    private int isDel;
    private Object updaterId;
    private Object updateTime;
    private int id;
    private String username;
    private String password;
    private String name;
    private String realName;
    private String mobile;
    private long birth;
    private int state;
    private long loginTime;
    private String image;
    private Object superior;
    private Object superiorName;
    private Object imageFile;
    private Object roleIds;
    private int errLoginNum;
    private Object isLockTime;
    private Object userCard;
    private Object cardType;
    private Object parkRolesVoList;
    private String token;
    private Object parkIds;
    private List<UserRoleSBean> userRoleS;
    private String userSig;

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getSuperior() {
        return superior;
    }

    public void setSuperior(Object superior) {
        this.superior = superior;
    }

    public Object getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(Object superiorName) {
        this.superiorName = superiorName;
    }

    public Object getImageFile() {
        return imageFile;
    }

    public void setImageFile(Object imageFile) {
        this.imageFile = imageFile;
    }

    public Object getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Object roleIds) {
        this.roleIds = roleIds;
    }

    public int getErrLoginNum() {
        return errLoginNum;
    }

    public void setErrLoginNum(int errLoginNum) {
        this.errLoginNum = errLoginNum;
    }

    public Object getIsLockTime() {
        return isLockTime;
    }

    public void setIsLockTime(Object isLockTime) {
        this.isLockTime = isLockTime;
    }

    public Object getUserCard() {
        return userCard;
    }

    public void setUserCard(Object userCard) {
        this.userCard = userCard;
    }

    public Object getCardType() {
        return cardType;
    }

    public void setCardType(Object cardType) {
        this.cardType = cardType;
    }

    public Object getParkRolesVoList() {
        return parkRolesVoList;
    }

    public void setParkRolesVoList(Object parkRolesVoList) {
        this.parkRolesVoList = parkRolesVoList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getParkIds() {
        return parkIds;
    }

    public void setParkIds(Object parkIds) {
        this.parkIds = parkIds;
    }

    public List<UserRoleSBean> getUserRoleS() {
        return userRoleS;
    }

    public void setUserRoleS(List<UserRoleSBean> userRoleS) {
        this.userRoleS = userRoleS;
    }

    public static class UserRoleSBean {
        /**
         * createId : null
         * createTime : null
         * isDel : 0
         * updaterId : null
         * updateTime : null
         * id : 5
         * name : 平台管理员
         * description : win win平台负责人
         * state : null
         * authorityIds : null
         * userCount : 0
         * authority : null
         * category : 1
         */

        private Object createId;
        private Object createTime;
        private int isDel;
        private Object updaterId;
        private Object updateTime;
        private int id;
        private String name;
        private String description;
        private Object state;
        private Object authorityIds;
        private int userCount;
        private Object authority;
        private int category;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getAuthorityIds() {
            return authorityIds;
        }

        public void setAuthorityIds(Object authorityIds) {
            this.authorityIds = authorityIds;
        }

        public int getUserCount() {
            return userCount;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }

        public Object getAuthority() {
            return authority;
        }

        public void setAuthority(Object authority) {
            this.authority = authority;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }
    }
}
