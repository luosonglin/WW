package com.winwin.android.UI.Entity;

public class ReginVo {


    /**
     * id : 0
     * name : string
     */

    private int id;
    private String name;

    public ReginVo() {
    }

    public ReginVo(int id, String name) {
        this.id = id;
        this.name = name;
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
}
