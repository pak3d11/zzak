package com.zzak.kr.bikecluster.view.home.Data;

import com.compa.gsk.base.BaseRecyclerViewItem;

public class Data extends BaseRecyclerViewItem {

    private int type;
    private HeaderData header;
    private ListData list;
    private FooterData footer;

    public Data(int type, HeaderData header){
        this.type = type;
        this.header = header;
    }

    public Data(int type, ListData list){
        this.type = type;
        this.list = list;
    }

    public Data(int type, FooterData footer){
        this.type = type;
        this.footer = footer;
    }

    public int getType(){
        return type;
    }

    public HeaderData getHeader(){
        return header;
    }

    public ListData getList(){
        return list;
    }

    public FooterData getFooter(){
        return footer;
    }
}
