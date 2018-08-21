package com.zzak.kr.bikecluster.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

/**
 * 재사용적인 기본적인 알림창 메소드
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 2:25
 */

public class UtilAlert {

    /**
     * @author IT사업부 곽성규
     * @version 1.0.0
     * @date 2018-07-09 오후 2:26
     *
     * @param activity
     * @param title 제목
     * @param message 내용
     * @param negativeTitle 거부 타이틀 지정
     * @param positiveTitle 승인 타이틀 지정
     * @param negativeListener 거부 이벤트 리스너
     * @param positiveListener 승인 이벤트 리스너
     */
    public static void AlertShow(Activity activity, boolean cancelable, String title, String message, String negativeTitle, String positiveTitle,
                                 DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        if(title != null && !title.equals("")){
            dialog.setTitle(title);
        }
        if(message != null && !message.equals("")){
            dialog.setMessage(message);
        }
        dialog.setCancelable(true);
        if(negativeListener != null && (negativeTitle != null && !negativeTitle.equals(""))){
            dialog.setNegativeButton(negativeTitle, negativeListener);
        }
        if(positiveListener != null && (positiveTitle != null && !positiveTitle.equals(""))){
            dialog.setPositiveButton(positiveTitle, positiveListener);
        }
        if(!cancelable){
            dialog.setCancelable(false);
        }
        dialog.create().show();
    }
    public static void AlertShow(Fragment fragment, boolean cancelable, String title, String message, String negativeTitle, String positiveTitle,
                                 DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(fragment.getContext());
        if(title != null && !title.equals("")){
            dialog.setTitle(title);
        }
        if(message != null && !message.equals("")){
            dialog.setMessage(message);
        }
        dialog.setCancelable(true);
        if(negativeListener != null && (negativeTitle != null && !negativeTitle.equals(""))){
            dialog.setNegativeButton(negativeTitle, negativeListener);
        }
        if(positiveListener != null && (positiveTitle != null && !positiveTitle.equals(""))){
            dialog.setPositiveButton(positiveTitle, positiveListener);
        }
        if(!cancelable){
            dialog.setCancelable(false);
        }
        dialog.create().show();
    }
    public static void AlertShow(Context context, boolean cancelable, String title, String message, String negativeTitle, String positiveTitle,
                                 DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if(title != null && !title.equals("")){
            dialog.setTitle(title);
        }
        if(message != null && !message.equals("")){
            dialog.setMessage(message);
        }
        dialog.setCancelable(true);
        if(negativeListener != null && (negativeTitle != null && !negativeTitle.equals(""))){
            dialog.setNegativeButton(negativeTitle, negativeListener);
        }
        if(positiveListener != null && (positiveTitle != null && !positiveTitle.equals(""))){
            dialog.setPositiveButton(positiveTitle, positiveListener);
        }
        if(!cancelable){
            dialog.setCancelable(false);
        }
        dialog.create().show();
    }
}
