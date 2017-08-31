package com.winwin.android.UI.Entity;

import java.util.List;

public class PageDto<T> {
    /**
     * pageIndex : 1
     * pageSize : 100
     * count : 7
     * list : [{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":2,"shCode":"SH002","name":"飞乐大厦","shName":null,"parkDesc":null,"parkRemark":null,"proName":"上海|上海市|静安区|永和路398号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":11647.35,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":2,"longitude":121.449276,"latitude":31.295047,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1489044654000,"isDel":0,"updaterId":null,"updateTime":null,"id":225,"dataType":3,"dataId":2,"imagePath":"http://106.14.47.190:89/2017-03-01/301488352759239.jpg","compressPath":"http://106.14.47.190:89/2017-03-01/301488352759239SM.jpg","width":601,"height":450,"suffix":"jpg","size":49862,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离1号线汶水路(地铁站)292米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":3,"shCode":"SZ003","name":"极策测试园区","shName":null,"parkDesc":null,"parkRemark":null,"proName":"广东省|深圳市|南山区|大冲商务大厦","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":13200,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":1,"longitude":113.910659,"latitude":22.585631,"planImgs":null,"coverImgs":[{"createId":21,"createTime":1488336740000,"isDel":0,"updaterId":null,"updateTime":null,"id":81,"dataType":3,"dataId":3,"imagePath":"http://106.14.47.190:89/2017-02-28/401488291851151.jpg","compressPath":"http://106.14.47.190:89/2017-02-28/401488291851151SM.jpg","width":2862,"height":1684,"suffix":"jpg","size":726109,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":2,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离5号线/环中线兴东(地铁站)957米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":4,"shCode":"SH004","name":"静安新业坊","shName":null,"parkDesc":null,"parkRemark":null,"proName":"上海|上海市|静安区|汶水路210号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":61558,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":2,"longitude":121.451989,"latitude":31.297029,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1489044673000,"isDel":0,"updaterId":null,"updateTime":null,"id":228,"dataType":3,"dataId":4,"imagePath":"http://106.14.47.190:89/2017-02-21/491487666241348_w1449_h1087.jpg","compressPath":"http://106.14.47.190:89/2017-02-21/491487666241348_w1449_h1087SM.jpg","width":133,"height":100,"suffix":"jpg","size":null,"sortno":null,"type":0,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离1号线汶水路(地铁站)524米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":5,"shCode":"SH005","name":"上海交科松江科创园","shName":null,"parkDesc":null,"parkRemark":null,"proName":"泗泾镇泗砖公路351号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":35000,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":1,"longitude":121.290789,"latitude":31.109626,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1489463516000,"isDel":0,"updaterId":null,"updateTime":null,"id":234,"dataType":3,"dataId":5,"imagePath":"http://106.14.47.190:89/2017-03-14/141489463325225.jpg","compressPath":"http://106.14.47.190:89/2017-03-14/141489463325225SM.jpg","width":3000,"height":2000,"suffix":"jpg","size":1581087,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离9号线泗泾(地铁站)3065米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":6,"shCode":"SH006","name":"98创意园","shName":null,"parkDesc":null,"parkRemark":null,"proName":"延平路98号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":13269,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":1,"longitude":121.448284,"latitude":31.234954,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1490347866000,"isDel":0,"updaterId":null,"updateTime":null,"id":328,"dataType":3,"dataId":6,"imagePath":"http://106.14.47.190:89/2017-03-16/571489650687069.jpg","compressPath":"http://106.14.47.190:89/2017-03-16/571489650687069SM.jpg","width":1729,"height":1200,"suffix":"jpg","size":1656150,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离7号线昌平路(地铁站)555米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":7,"shCode":"SH007","name":"蓝天绿地商务广场","shName":null,"parkDesc":null,"parkRemark":null,"proName":"共和新路1301号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":16414,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":1,"longitude":121.466888,"latitude":31.270005,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1490347607000,"isDel":0,"updaterId":null,"updateTime":null,"id":316,"dataType":3,"dataId":7,"imagePath":"http://106.14.47.190:89/2017-03-16/491489651584561.jpg","compressPath":"http://106.14.47.190:89/2017-03-16/491489651584561SM.jpg","width":959,"height":682,"suffix":"jpg","size":138091,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离8号线西藏北路(地铁站)745米","peripheryDesc":null,"collection":false,"belongCurrentUser":false},{"createId":null,"createTime":null,"isDel":0,"updaterId":null,"updateTime":null,"id":8,"shCode":"SH008","name":"上海机电大厦","shName":null,"parkDesc":null,"parkRemark":null,"proName":"恒丰路600号","payDay":null,"payMon":null,"proStand":null,"watStand":null,"proArea":null,"rentArea":22400,"planImage":null,"noteDay":null,"state":2,"reviewCreateId":null,"buildNum":0,"houseNum":0,"reviewInfo":null,"createName":null,"homeImage":null,"type":1,"longitude":121.458934,"latitude":31.253266,"planImgs":null,"coverImgs":[{"createId":19,"createTime":1492076724000,"isDel":0,"updaterId":null,"updateTime":null,"id":481,"dataType":3,"dataId":8,"imagePath":"http://106.14.47.190:89/2017-04-11/911491900995529.jpg","compressPath":"http://106.14.47.190:89/2017-04-11/911491900995529SM.jpg","width":428,"height":523,"suffix":"jpg","size":326762,"sortno":null,"type":null,"coord":null}],"sandImgs":null,"adImg":null,"commercialId":null,"commercialName":null,"city":null,"area":null,"officeSuperType":1,"officeSecondType":1,"userLists":null,"currentRentNum":null,"dayRentStartPi":null,"distanceMetroDesc":"距离1号线;3号线;4号线上海火车站(地铁站)415米","peripheryDesc":null,"collection":false,"belongCurrentUser":false}]
     */

    private int pageIndex;
    private int pageSize;
    private int count;
    private List<T> list;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
