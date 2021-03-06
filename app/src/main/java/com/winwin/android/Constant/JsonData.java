package com.winwin.android.Constant;


import com.winwin.android.UI.Entity.BookListDto;
import com.winwin.android.UI.Entity.MySection;

import java.util.ArrayList;
import java.util.List;

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
