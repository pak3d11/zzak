package com.zzak.kr.bikecluster.view.map.api;

import com.zzak.kr.bikecluster.view.map.response.RESList;

import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;


public interface MaditorAPI {

    @Multipart
    @POST("board/maditor_list.asp")
    Call<RESList> reqMaditorPage(@PartMap Map<String, RequestBody> body);

}
