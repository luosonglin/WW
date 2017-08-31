package com.winwin.android.MVP.IM.model;

/**
 * 会话页获取好友详细资料的实体
 */
public class ConversationUser {
    public String identify;
    public String nickName;
    public String avatar;

    public ConversationUser(String identify) {
        this.identify = identify;
    }

    public ConversationUser(String identify, String nickName, String avatar) {
        this.identify = identify;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
