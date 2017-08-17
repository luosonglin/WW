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
    /**
     * UserRegisterVo {
     cardType (integer, optional): 用户登录标识类型(1-手机号，2-微信，3-QQ， 0 其他) ,
     headPicUrl (string, optional): 头像 ,
     msgCaptcha (string, optional): 短信验证码(当cardType=1时存在，其他为空) ,
     msgType (integer, optional): 短信验证码类型：0 注册，1 找回密码 ,
     password (string, optional): 密码 ,
     userCard (string, optional): 用户登录标识(手机号或者微信或者QQ) ,
     userName (string, optional): 用户名----可用于注册成功后的登录名
     }
     */

    private int cardType;
    private String headPicUrl;
    private String msgCaptcha;
    private int msgType;
    private String password;
    private String userCard;
    private String userName;

    public UserRegisterVo() {
    }

    //忘记密码用
    public UserRegisterVo(int cardType, int msgType, String userCard) {
        this.cardType = cardType;
        this.msgType = msgType;
        this.userCard = userCard;
    }

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
