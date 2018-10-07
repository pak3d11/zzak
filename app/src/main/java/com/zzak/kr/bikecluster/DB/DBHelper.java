package com.zzak.kr.bikecluster.DB;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABAS_NAME = "BikeCluster.db";

    public DBHelper(Context context) {
        super(context, DATABAS_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /**
         * date -  측정날짜
         * measurement_time - 측정시간
         * average_speed - 평균속도
         *
         * !!! 밑의 변수는 지도 부분 구현시 추가 필요 !!!
         * distance - 이동거리
         * start_location - 시작위치
         * end_location - 종료위치
         * start_address - 시작주소
         * end_address - 종료주소
         */
        db.execSQL("CREATE TABLE BIKECLUSTER (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, measurement_time TEXT, average_speed TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 속도 측정 후 측정날짜, 측정시간, 평균속도 DB에 저장(insert)
     * @param date
     * @param measurement_time
     * @param average_speed
     */
    public void insertSppedCheck(String date, String measurement_time, String average_speed){

        date = DatabaseUtils.sqlEscapeString(date);
        measurement_time = DatabaseUtils.sqlEscapeString(measurement_time);
        average_speed = DatabaseUtils.sqlEscapeString(average_speed);

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO BIKECLUSTER VALUES(null, " + date + ", " + measurement_time + ", " + average_speed + ");");
        db.close();
    }

    /**
     * DB내용, idx번호 초기화
     */
    public void initDataBase(){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM BIKECLUSTER");
        db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'BIKECLUSTER';");
        db.close();
    }
}
