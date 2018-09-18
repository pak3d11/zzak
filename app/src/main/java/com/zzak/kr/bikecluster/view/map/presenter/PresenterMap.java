package com.zzak.kr.bikecluster.view.map.presenter;

import android.support.v7.widget.RecyclerView;

import com.compa.gsk.base.BasePresenter;
import com.compa.gsk.http.CompaRESTListener;
import com.compa.gsk.util.UtilStr;
import com.zzak.kr.bikecluster.view.map.contract.ContractMap;
import com.zzak.kr.bikecluster.view.map.adapter.AdapterMapRv;
import com.zzak.kr.bikecluster.view.map.item.ItemMapRv;
import com.zzak.kr.bikecluster.view.map.request.REQList;
import com.zzak.kr.bikecluster.view.map.response.DayList;
import com.zzak.kr.bikecluster.view.map.response.RESList;
import com.zzak.kr.bikecluster.view.map.util.UtilHttp;
import com.zzak.kr.bikecluster.view.map.view.FmtMap;
import java.util.ArrayList;

public class PresenterMap extends BasePresenter<FmtMap> implements ContractMap.Presenter, AdapterMapRv.RvAdapterListener{

    private AdapterMapRv adapterMapRv;

    @Override
    public void setRecyclerView(RecyclerView recyclerView) {
        adapterMapRv = new AdapterMapRv(this);
        recyclerView.setAdapter(adapterMapRv);
    }

    @Override
    public void onViewCreated() {
        req();
    }

    @Override
    public void onRvItemClick(int position) {
        this.mView.showToast(adapterMapRv.getItem(position).tv_title);
    }

    public void req(){
        REQList reqList = new REQList();
        reqList.baseUrl = "http://api.ikn.kr/whp/";
        reqList.body = UtilHttp.requestBody(new String[]{"page"}, new String[]{"1"});
        this.<REQList, RESList>request(reqList, new CompaRESTListener<RESList>() {
            @Override
            public void onError(int i) {

            }

            @Override
            public void onFailure(String s) {

            }

            @Override
            public void onSuccess(RESList resList) {
                if(resList != null){
                    ArrayList<DayList> list = resList.list;
                    ArrayList<ItemMapRv> rvItems = new ArrayList<>();
                    for(int i=0; i<list.size(); i++){
                        DayList dayList = list.get(i);
                        ItemMapRv itemMapRv = new ItemMapRv();
                        itemMapRv.seq = dayList.seq;
                        itemMapRv.tv_title = dayList.subject;
                        itemMapRv.tv_summary = dayList.summary;
                        itemMapRv.tv_writer_name = dayList.writer_name;
                        itemMapRv.tv_date = UtilStr.datePatternReplace(dayList.reg_date, "yyyy-MM-dd", "MMM. dd.yyyy", "en", "US");
                        itemMapRv.img_path = "http://img.healthi.kr"+dayList.img_path;
                        if(i == 0){
                            itemMapRv.viewType = AdapterMapRv.VIEW_HEADER;
                            itemMapRv.tv_header = "헤더 입니다.";

                        }else if(i == list.size() - 1){
                            itemMapRv.viewType = AdapterMapRv.VIEW_FOOTER;
                            itemMapRv.tv_footer = "푸터 입니다.";

                        }else {
                            itemMapRv.viewType = AdapterMapRv.VIEW_LIST;
                        }
                        rvItems.add(itemMapRv);
                    }
                    adapterMapRv.refreshAll(rvItems);
                }
            }
        });
    }
}
