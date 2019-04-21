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
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mData="今日新闻";

    private Context mContext;
    private ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    private ArrayList<DailyNewsBean.TopStoriesBean> mBanners;

    public RlvDailyNewsAdapter(Context context, ArrayList<DailyNewsBean.StoriesBean> newsList, ArrayList<DailyNewsBean.TopStoriesBean> banners) {
        mContext = context;
        mNewsList = newsList;
        mBanners = banners;
    }

    @Override
    public int getItemViewType(int position) {
        if(mBanners.size()>0){
            if(position==0){
                return TYPE_BANNER;
            }else if(position==1){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if(position==0){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(i==TYPE_BANNER){
            return new BannerVH(inflater.inflate(R.layout.item_banner,null));
        }else if(i==TYPE_TIME){
            return new TimeVH(inflater.inflate(R.layout.item_data,null));
        }else {
            return new NewVH(inflater.inflate(R.layout.item_time,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if(viewType==TYPE_BANNER){
            BannerVH bannerVH= (BannerVH) viewHolder;
            bannerVH.banner.setImages(mBanners)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean= (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(mContext).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        }else if(viewType==TYPE_TIME){
            TimeVH timeVH= (TimeVH) viewHolder;
            timeVH.tv.setText(mData);

        }else {
            NewVH newsVH = (NewVH) viewHolder;
            int newPosition=i-1;
            if(mBanners.size()>0){
                newPosition-=1;
            }
            DailyNewsBean.StoriesBean storiesBean=mNewsList.get(newPosition);
            newsVH.name.setText(storiesBean.getTitle());
            Glide.with(mContext).load(storiesBean.getImages().get(0)).into(newsVH.img);
        }
    }

    @Override
    public int getItemCount() {
        if(mBanners.size()>0){
            return mNewsList.size()+1+1;
        }else {
            return mNewsList.size()+1;
        }
    }

    public void setData(DailyNewsBean bean){
        mData = bean.getDate();
        mBanners.clear();
        if(bean.getStories()!=null&&bean.getTop_stories().size()>0){
            mBanners.addAll(bean.getTop_stories());
        }
        mNewsList.clear();
        if(bean.getStories()!=null && bean.getStories().size()>0){
            mNewsList.addAll(bean.getStories());
            notifyDataSetChanged();
        }
    }

    class BannerVH extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner banner;

        public BannerVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tv;


        public TimeVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class NewVH extends RecyclerView.ViewHolder{
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_name)
        TextView name;

        public NewVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
