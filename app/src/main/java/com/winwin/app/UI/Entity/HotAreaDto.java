package com.winwin.app.UI.Entity;

public class HotAreaDto {

    /**
     * id : 310113
     * name : 宝山区
     * parent : {"id":310100,"name":"上海市","parent":null}
     */

    private int id;
    private String name;
    private ParentBean parent;

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

    public ParentBean getParent() {
        return parent;
    }

    public void setParent(ParentBean parent) {
        this.parent = parent;
    }

    public static class ParentBean {
        /**
         * id : 310100
         * name : 上海市
         * parent : null
         */

        private int id;
        private String name;
        private Object parent;

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

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }
    }
}
