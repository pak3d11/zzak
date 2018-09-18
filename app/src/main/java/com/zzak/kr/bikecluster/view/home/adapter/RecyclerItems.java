package com.zzak.kr.bikecluster.view.home.adapter;

public class RecyclerItems {

    private int type;
    private HeaderItem headerItem;
    private HomeItem homeItem;
    private FooterItem footerItem;


    public RecyclerItems(int type, HeaderItem headerItem){
        this.type = type;
        this.headerItem = headerItem;
    }

    public RecyclerItems(int type, HomeItem homeItem) {
        this.type = type;
        this.homeItem = homeItem;
    }

    public RecyclerItems(int type, FooterItem footerItem) {
        this.type = type;
        this.footerItem = footerItem;
    }

    public int getType() {
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public HeaderItem getHeaderItem(){
        return headerItem;
    }

    public void setHeaderItem(HeaderItem headerItem){
        this.headerItem = headerItem;
    }

    public HomeItem getHomeItem (){
        return homeItem;
    }

    public void setHomeItem(HomeItem homeItem){
        this.homeItem = homeItem;
    }

    public FooterItem getFooterItem(){
        return footerItem;
    }

    public void setFooterItem(FooterItem footerItem){
        this.footerItem = footerItem;
    }


}
