package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;

import java.util.List;

/**
 * Created by Tecmry on 2017/12/16.
 */

public class YWithMeItemAdapter extends RecyclerView.Adapter<YWithMeItemAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private List<String> list;

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

    private YOthersPushitemAdapter.OnItemClickListner listner;

    public void setItemClickListener(YOthersPushitemAdapter.OnItemClickListner listner) {
        this.listner = listner;
    }

    public YWithMeItemAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public YWithMeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ypersninformation_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YWithMeItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (listner != null) {
            listner.OnItemClickListner(view, (int) view.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView headimg;
        private TextView withmessage;
        private ImageView mathimageview;
        public ViewHolder(View itemView) {
            super(itemView);
            headimg = itemView.findViewById(R.id.yinformationitem_icon);
            withmessage = itemView.findViewById(R.id.yinformationitem_message);
            mathimageview = itemView.findViewById(R.id.yinformationitem_math);
        }
    }
}
