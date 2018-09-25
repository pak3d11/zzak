package com.zzak.kr.bikecluster.view.map.response;

import com.compa.gsk.http.ResponseBase;
import java.util.ArrayList;


public class RESList extends ResponseBase {

    public String current_page;
    public String total_count;
    public String total_page;
    public String next_page;
    public ArrayList<DayList> list;

    @Override
    public String toString() {
        return "RESRecommend{" +
                "state='" + state + '\'' +
                ", msg='" + msg + '\'' +
                ", current_page='" + current_page + '\'' +
                ", total_count='" + total_count + '\'' +
                ", total_page='" + total_page + '\'' +
                ", next_page='" + next_page + '\'' +
                ", list=" + list +
                '}';
    }
}
