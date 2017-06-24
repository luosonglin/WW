package com.winwin.app.Data.Retrofit;


import com.winwin.app.UI.Entity.HttpResult2;

/**
 * 异常类
 * 按照自己公司的接口需求来  不可直接套用
 * 使用需谨慎
 * 有什么问题可以联系我
 * 邮箱80945540@qq.com
 */
public class ApiException extends RuntimeException {

    public ApiException(HttpResult2 httpResult2) {
        this(getApiExceptionMessage(httpResult2));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(HttpResult2 httpResult2){
        String message = "";
        switch (httpResult2.getCode()) {
            default:
                message = "与服务端链接错误"+ httpResult2.getCode();

        }
        return message;
    }
}

