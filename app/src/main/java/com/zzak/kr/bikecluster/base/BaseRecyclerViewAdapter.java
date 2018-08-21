package com.zzak.kr.bikecluster.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/**
 * 빠른 리사이클러뷰 어댑터 구성을 위한
 *
 * BaseRecyclerViewAdapter 클래스
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 1:00
 */

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ArrayList baseItems = new ArrayList();
    protected abstract int initLayout(int viewType);
    protected abstract RecyclerView.ViewHolder initViewHolder(View view, int viewType);
    protected abstract void BindViewHolder(RecyclerView.ViewHolder mHolder, Object mItem);

    @Override
    public int getItemCount() {
        return this.baseItems.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.initViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(this.initLayout(viewType), parent, false),
                viewType
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.BindViewHolder(holder, this.baseItems.get(position));
    }

    public Object getItem(int position){
        return this.baseItems.get(position);
    }

    public void addItem(int position, Object object){
        this.baseItems.add(position, object);
        this.notifyItemInserted(position);
        this.notifyItemRangeChanged(position, this.baseItems.size());
    }

    public void setItem(int position, Object obj){
        this.baseItems.set(position, obj);
        this.notifyItemChanged(position);
    }

    public void removeItem(int position){
        this.baseItems.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, this.baseItems.size());
    }

    public void refreshAll(ArrayList items){
        this.baseItems = items;
        this.notifyDataSetChanged();
    }
}
