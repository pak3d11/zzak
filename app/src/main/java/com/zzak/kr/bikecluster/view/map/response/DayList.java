package com.zzak.kr.bikecluster.view.map.response;


public class DayList {
    public String seq;
    public String subject;
    public String summary;
    public String writer_name;
    public String view_cnt;
    public String like_cnt;
    public String reg_date;
    public String img_path;

    @Override
    public String toString() {
        return "DayList{" +
                "seq='" + seq + '\'' +
                ", subject='" + subject + '\'' +
                ", summary='" + summary + '\'' +
                ", writer_name='" + writer_name + '\'' +
                ", view_cnt='" + view_cnt + '\'' +
                ", like_cnt='" + like_cnt + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", img_path='" + img_path + '\'' +
                '}';
    }
}
