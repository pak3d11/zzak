package com.zzak.kr.bikecluster.view.home.Data;

public class HeaderData {

    private String date, runTime, distance;

    public HeaderData(String date, String runTime, String distance) {

        this.date = date;
        this.runTime = runTime;
        this.distance = distance;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setRunTime(String runTime){
        this.runTime = runTime;
    }

    public String getRunTime(){
        return runTime;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }

    public String getDistance(){
        return distance;
    }
}
