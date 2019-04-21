package com.yanfeng.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.SectionBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvSectionAdapter extends RecyclerView.Adapter<RlvSectionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SectionBean.DataBean> list;

    public RlvSectionAdapter(Context context, ArrayList<SectionBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_section, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SectionBean.DataBean dataBean = list.get(i);
        viewHolder.name.setText(dataBean.getDescription());
        viewHolder.title.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getThumbnail()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(SectionBean bean) {
        if(bean.getData()!=null && bean.getData().size()>0){
            list.addAll(bean.getData());
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_title)
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
