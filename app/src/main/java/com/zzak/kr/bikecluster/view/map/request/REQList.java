package com.zzak.kr.bikecluster.view.map.request;

import com.compa.gsk.http.RequestBase;
import com.zzak.kr.bikecluster.view.map.api.MaditorAPI;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-31 오후 3:03
 */
public class REQList extends RequestBase {

    public Map<String, RequestBody> body;

    @Override
    public Call getCall() {
        return this.retrofit().create(MaditorAPI.class).reqMaditorPage(body);
    }
}
