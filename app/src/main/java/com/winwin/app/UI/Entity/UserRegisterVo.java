package com.winwin.app.UI.Entity;

public class UserRegisterVo {

    /**
     * cardType : 0
     * headPicUrl : string
     * msgCaptcha : string
     * msgType : 0
     * password : string
     * userCard : string
     * userName : string
     */

    private int cardType;
    private String headPicUrl;
    private String msgCaptcha;
    private int msgType;
    private String password;
    private String userCard;
    private String userName;

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public String getMsgCaptcha() {
        return msgCaptcha;
    }

    public void setMsgCaptcha(String msgCaptcha) {
        this.msgCaptcha = msgCaptcha;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
