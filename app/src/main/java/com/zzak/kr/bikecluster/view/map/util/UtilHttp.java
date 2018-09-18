package com.zzak.kr.bikecluster.view.map.util;

import com.compa.gsk.util.AppContext;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class UtilHttp {

    public static Map<String, RequestBody> requestBody(String[] keys, String[] values){
        Map<String, RequestBody> requestBody = new HashMap<>();
        if(keys != null && keys.length > 0){
            for(int i=0; i<keys.length; i++){
                requestBody.put(keys[i], RequestBody.create(MultipartBody.FORM, values[i]));
            }
        }
        return requestBody;
    }

}
