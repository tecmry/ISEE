package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.NetArticleItemData;

import java.util.List;

/**
 * Created by Tecmry on 2018/3/26.
 */

public class RecomandVideoRecyclerAdapter extends RecyclerView.Adapter<RecomandVideoRecyclerAdapter.ViewHolder> {
    private List<NetArticleItemData.VideosBean> dataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecomandVideoRecyclerAdapter(List<NetArticleItemData.VideosBean> dataList, Context mContext )
    {
        this.dataList = dataList;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public RecomandVideoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecomandVideoRecyclerAdapter.ViewHolder viewHolder = new RecomandVideoRecyclerAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.fragment_video_news_recommendvideoitems,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomandVideoRecyclerAdapter.ViewHolder holder, int position) {
        if ((position+1)%2==0)
        {
            if (dataList.size()!=0)
            {
                holder.Tv_LeftTitle.setText(dataList.get(2).getTitle());
                holder.Tv_RightTitle.setText(dataList.get(3).getTitle());
                Glide.with(mContext).load(dataList.get(2).getCoverUrl()).into(holder.Iv_Left);
                Glide.with(mContext).load(dataList.get(3).getCoverUrl()).into(holder.Iv_Right);
                holder.Tv_LeftAuthor.setText(dataList.get(2).getNickname());
                holder.Tv_RightAuthor.setText(dataList.get(3).getNickname());
            }
        }else {
            holder.Tv_LeftTitle.setText(dataList.get(0).getTitle());
            holder.Tv_RightTitle.setText(dataList.get(1).getTitle());
            Glide.with(mContext).load(dataList.get(0).getCoverUrl()).into(holder.Iv_Left);
            Glide.with(mContext).load(dataList.get(1).getCoverUrl()).into(holder.Iv_Right);
            holder.Tv_LeftAuthor.setText(dataList.get(0).getNickname());
            holder.Tv_RightAuthor.setText(dataList.get(1).getNickname());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Iv_Left;
        private ImageView Iv_Right;
        private TextView  Tv_LeftTitle;
        private TextView  Tv_RightTitle;
        private TextView  Tv_LeftAuthor;
        private TextView  Tv_RightAuthor;
        public ViewHolder(View itemView) {
            super(itemView);
            Iv_Left = itemView.findViewById(R.id.Iv_RecommendvideoItemLeft);
            Iv_Right = itemView.findViewById(R.id.Iv_RecommendvideoItemRight);
            Tv_LeftAuthor = itemView.findViewById(R.id.Tv_RecommendvideoItemAuthorLeft);
            Tv_RightAuthor = itemView.findViewById(R.id.Tv_RecommendvideoItemAuthorRight);
            Tv_LeftTitle = itemView.findViewById(R.id.Tv_RecommendvideoItemTitleLeft);
            Tv_RightTitle = itemView.findViewById(R.id.Tv_RecommendvideoItemTimeRight);
        }
    }
}
