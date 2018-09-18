package com.zzak.kr.bikecluster.view.map.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.compa.gsk.base.BaseRecyclerViewAdapter;
import com.compa.gsk.util.GlideLoader;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.map.item.ItemMapRv;


public class AdapterMapRv extends BaseRecyclerViewAdapter<ItemMapRv> {

    public interface RvAdapterListener {
        void onRvItemClick(int position);
    }

    private RvAdapterListener listener;
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_LIST = 1;
    public static final int VIEW_FOOTER = 2;

    public AdapterMapRv(RvAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    protected int initLayout(int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return R.layout.item_rv_map_header;

            case VIEW_LIST:
                return R.layout.item_rv_maditor_day;

            case VIEW_FOOTER:
                return R.layout.item_rv_map_footer;

            default:
                return 0;
        }
    }

    @Override
    protected RecyclerView.ViewHolder initViewHolder(View view, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return new ItemHeader(view);

            case VIEW_LIST:
                return new ItemList(view);

            case VIEW_FOOTER:
                return new ItemFooter(view);

            default:
                return null;
        }
    }

    @Override
    protected void BindViewHolder(RecyclerView.ViewHolder viewHolder, ItemMapRv itemMapRv, int i) {
        if (viewHolder instanceof ItemHeader) {
            ItemHeader holder = (ItemHeader) viewHolder;
            holder.tv_header.setText(itemMapRv.tv_header);

        } else if (viewHolder instanceof ItemList) {
            ItemList holder = (ItemList) viewHolder;

            holder.tv_title.setText(itemMapRv.tv_title);
            holder.tv_summary.setText(itemMapRv.tv_summary);
            holder.tv_writer_name.setText(itemMapRv.tv_writer_name);
            holder.tv_date.setText(itemMapRv.tv_date);

            GlideLoader.loadView(itemMapRv.img_path, holder.img_cover);

        } else if (viewHolder instanceof ItemFooter) {
            ItemFooter holder = (ItemFooter) viewHolder;
            holder.tv_footer.setText(itemMapRv.tv_footer);
        }
    }

    private class ItemHeader extends RecyclerView.ViewHolder {

        public TextView tv_header;

        public ItemHeader(View itemView) {
            super(itemView);
            tv_header = itemView.findViewById(R.id.tv_header);
        }
    }

    private class ItemList extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_title;
        public TextView tv_summary;
        public TextView tv_writer_name;
        public TextView tv_date;

        public ImageView img_cover;

        public ItemList(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_summary = itemView.findViewById(R.id.tv_summary);
            tv_writer_name = itemView.findViewById(R.id.tv_writer_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_cover = itemView.findViewById(R.id.img_cover);
        }

        @Override
        public void onClick(View v) {
            listener.onRvItemClick(getAdapterPosition());
        }
    }

    private class ItemFooter extends RecyclerView.ViewHolder {

        public TextView tv_footer;

        public ItemFooter(View itemView) {
            super(itemView);
            tv_footer = itemView.findViewById(R.id.tv_footer);
        }
    }
}
