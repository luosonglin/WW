package com.winwin.app.Constant;


import com.winwin.app.UI.entity.BookListDto;
import com.winwin.app.UI.entity.MySection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class JsonData {
    public static List<MySection> getSampleData(List<BookListDto> expertLists, int PageIndex) {
        List<MySection> list = new ArrayList<>();
        if(PageIndex%2==0){
            list.add(new MySection(true, "分组"+PageIndex, false));
        }else{
            list.add(new MySection(true, "分组"+PageIndex, true));
        }
        for (BookListDto UniversityListDto:expertLists) {
            list.add(new MySection(UniversityListDto));
        }
        return list;
    }
}
