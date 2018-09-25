package com.zzak.kr.bikecluster.view.home.Data;

public class ListData {

    private String date, runTime, distance;

    public ListData(String date, String runTime, String distance){

        this.date = date;
        this.runTime = runTime;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getRunTime(){
        return runTime;
    }

    public void setRunTime(String runTime){
        this.runTime = runTime;
    }

    public String getDistance(){
        return distance;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }
}
