package com.zzak.kr.bikecluster.view.home.Data;

import com.compa.gsk.base.BaseRecyclerViewItem;

public class Data extends BaseRecyclerViewItem {

    private HeaderData header;
    private ListData list;
    private FooterData footer;

    public Data(int type, HeaderData header){
        this.viewType = type;
        this.header = header;
    }

    public Data(int type, ListData list){
        this.viewType = type;
        this.list = list;
    }

    public Data(int type, FooterData footer){
        this.viewType = type;
        this.footer = footer;
    }

    public void setType(int viewType){
        this.viewType = viewType;
    }

    public int getType(){
        return viewType;
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
