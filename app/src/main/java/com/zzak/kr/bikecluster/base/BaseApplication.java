package com.zzak.kr.bikecluster.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-24 오전 11:15
 */
public abstract class BaseApplication extends Application{

    /**
     * 현재 앱의 메니페스에 기록된 권한목록의 정보를 얻어온다.
     *
     * @author IT사업부 곽성규
     * @version 1.0.0
     * @date 2018-08-07 오전 10:40
     *
     * @param context
     * @return String[] 권한 목록
     */
    public static String[] getPermissions(Context context){
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException ("This should have never happened.", e);
        }
    }

    /**
     * 현재 앱의 이름을 획득 하는 메소드
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static String getAppName(Context context){
        String app_name = "";
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(getPackageName(context), PackageManager.GET_UNINSTALLED_PACKAGES);
            app_name = (String) context.getPackageManager().getApplicationLabel(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return app_name;
    }

    /**
     * 현재 앱의 버전 이름 획득 메소드
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static double getAppVersionName(Context context){
        String version_name = "";
        try {
            version_name = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return Double.parseDouble(version_name);
    }

    /**
     * 현재 앱의 버전 코드 획득 메소드
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static double getAppVersionCode(Context context) {
        double version_code = 0;
        try {
            version_code = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return version_code;
    }

    /**
     * 현재 앱의 패키지명 획득 메소드
     *
     * build.gradle(BikeClusterApplication)에 설정된 패키지명
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 현재 앱에 런처로 설정된 클래스(액티비티) 획득 메소드
     *
     * AndroidManifest.xml
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static String getLaunchClassName(Context context){
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
    }

    /**
     * 현재 앱의 마켓 URL 획득 메소드
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static String getMarketUrl(Context context){
        return "https://play.google.com/store/apps/details?id=" + getPackageName(context);
    }
}
