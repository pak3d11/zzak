package com.zzak.kr.bikecluster.view.map.item;

import com.compa.gsk.base.BaseRecyclerViewItem;


public class ItemMapRv extends BaseRecyclerViewItem {

    public String seq;
    public String tv_title;
    public String tv_summary;
    public String tv_writer_name;
    public String tv_date;
    public String img_path;
    public String tv_header;
    public String tv_footer;

    @Override
    public String toString() {
        return "ItemMaditorDayList{" +
                "seq='" + seq + '\'' +
                ", tv_title='" + tv_title + '\'' +
                ", tv_summary='" + tv_summary + '\'' +
                ", tv_writer_name='" + tv_writer_name + '\'' +
                ", tv_date='" + tv_date + '\'' +
                ", img_path='" + img_path + '\'' +
                '}';
    }
}
