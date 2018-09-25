package com.zzak.kr.bikecluster.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.compa.gsk.base.BaseRecyclerViewAdapter;
import com.compa.gsk.base.BaseRecyclerViewItem;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.home.Data.Data;

public class AdapterHomeRv extends BaseRecyclerViewAdapter<Data> {

    public interface RvAdapterListener {
        void onRvItemClick(int position);
    }

    private RvAdapterListener listener;
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_LIST = 1;
    public static final int VIEW_FOOTER = 2;

    public AdapterHomeRv(RvAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    protected int initLayout(int viewType) {

        switch (viewType) {

            case VIEW_HEADER:
                return R.layout.item_rv_home_header;

            case VIEW_LIST:
                return R.layout.item_rv_home_list;

            case VIEW_FOOTER:
                return R.layout.item_rv_home_footer;

            default:
                return 0;
        }
    }

    @Override
    protected RecyclerView.ViewHolder initViewHolder(View view, int viewType) {

        switch (viewType) {

            case VIEW_HEADER:
                return new HeaderHolder(view);

            case VIEW_LIST:
                return new ListHolder(view);

            case VIEW_FOOTER:
                return new FooterHolder(view);

            default:
                return null;
        }
    }

    @Override
    protected void BindViewHolder(RecyclerView.ViewHolder viewHolder, Data data, int position) {

        if (viewHolder instanceof HeaderHolder) {

            HeaderHolder holder = (HeaderHolder) viewHolder;
            holder.home_header_date.setText(data.getHeader().getDate());
            holder.home_header_runtime.setText(data.getHeader().getRunTime());
            holder.home_header_distance.setText(data.getHeader().getDistance());

        } else if (viewHolder instanceof ListHolder) {

            ListHolder holder = (ListHolder) viewHolder;
            holder.home_list_date.setText(data.getList().getDate());
            holder.home_list_runtime.setText(data.getList().getRunTime());
            holder.home_list_distance.setText(data.getList().getDistance());

        } else if (viewHolder instanceof FooterHolder) {

            FooterHolder holder = (FooterHolder) viewHolder;
            holder.home_footer_btn.setText(data.getFooter().getInitBtn());
        }
    }

    /**
     * Item Data Class들 생성
     */

    //헤더 데이터 + 홀더 클래스
    private class HeaderHolder extends RecyclerView.ViewHolder {

        private TextView home_header_date;
        private TextView home_header_runtime;
        private TextView home_header_distance;

        public HeaderHolder(View itemView) {
            super(itemView);

            home_header_date = itemView.findViewById(R.id.home_header_date);
            home_header_runtime = itemView.findViewById(R.id.home_header_runtime);
            home_header_distance = itemView.findViewById(R.id.home_header_distance);
        }
    }

    //리스트데이터 + 홀더 클래스
    private class ListHolder extends RecyclerView.ViewHolder {

        private TextView home_list_date;
        private TextView home_list_runtime;
        private TextView home_list_distance;

        public ListHolder(View itemView) {
            super(itemView);

            home_list_date = itemView.findViewById(R.id.home_list_date);
            home_list_runtime = itemView.findViewById(R.id.home_list_runtime);
            home_list_distance = itemView.findViewById(R.id.home_list_distance);
        }
    }

    //푸터데이터 + 홀더 클래스
    private class FooterHolder extends RecyclerView.ViewHolder {

        private Button home_footer_btn;

        public FooterHolder(View itemView) {
            super(itemView);

            home_footer_btn = itemView.findViewById(R.id.home_footer_btn);
        }
    }
}
