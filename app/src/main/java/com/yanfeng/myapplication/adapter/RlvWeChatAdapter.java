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
import com.yanfeng.myapplication.bean.WeChatBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvWeChatAdapter extends RecyclerView.Adapter<RlvWeChatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeChatBean.NewslistBean> list;

    public RlvWeChatAdapter(Context context, ArrayList<WeChatBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<WeChatBean.NewslistBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_wechat, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WeChatBean.NewslistBean newslistBean = list.get(i);
        viewHolder.tv_name.setText(newslistBean.getTitle());
        viewHolder.tv_title.setText(newslistBean.getCtime());
        viewHolder.tv.setText(newslistBean.getDescription());
        Glide.with(context).load(newslistBean.getPicUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(WeChatBean bean) {
        if(bean.getNewslist()!=null && bean.getNewslist().size()>0){
            list.addAll(bean.getNewslist());
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv)
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
