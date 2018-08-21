package com.zzak.kr.bikecluster.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * view 동적 생성에 id 동적 할당을 위한 클래스
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-16 오후 2:37
 */

public class UtilViewIdGenerator {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public UtilViewIdGenerator() {
    }

    @SuppressLint({"NewApi"})
    public static int generateViewId() {
        if(Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        } else {
            int result;
            int newValue;
            do {
                result = sNextGeneratedId.get();
                newValue = result + 1;
                if(newValue > 16777215) {
                    newValue = 1;
                }
            } while(!sNextGeneratedId.compareAndSet(result, newValue));

            return result;
        }
    }
}
