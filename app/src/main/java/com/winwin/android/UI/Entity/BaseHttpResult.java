package com.winwin.android.UI.Entity;

import java.util.List;

public class BaseHttpResult<T> {
    private StatusBean status;
    private int totalRows;
    private List<T> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
