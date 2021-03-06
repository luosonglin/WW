package com.winwin.android.UI.Entity;

import com.xiaochao.lcrapiddeveloplibrary.entity.MultiItemEntity;

public class ChatDto extends MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMGS = 3;

    private String textContext;

    private String imageUrl;


    public ChatDto(String textContext, String imageUrl) {
        this.textContext = textContext;
        this.imageUrl = imageUrl;
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
