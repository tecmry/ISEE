package com.example.mao.beautylife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.data.SearchData;
import com.example.mao.beautylife.listener.OnItemClickListener;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by -- Mao on 2017/12/9.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SearchData> list;

    private ItemListener listener;

    public SearchRecyclerAdapter(List<SearchData> list, ItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface ItemListener{
        void itemCallback(View itemView);
    }



    private class ItemHolder extends RecyclerView.ViewHolder{

        ImageView imageView1, imageView2, imageView3;
        TextView textView1, textView2, textView3;

        private ItemHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.result_item_image_one);
            imageView2 = itemView.findViewById(R.id.result_item_image_two);
            imageView3 = itemView.findViewById(R.id.result_item_image_three);
            textView1 = itemView.findViewById(R.id.result_item_text_one);
            textView2 = itemView.findViewById(R.id.result_item_text_two);
            textView3 = itemView.findViewById(R.id.result_item_text_three);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_resule_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder)holder;
        SearchData data = list.get(position);
        ImageLoaderUtil.ImageLoader(itemHolder.imageView1, data.getImageUrl1());
        ImageLoaderUtil.ImageLoader(itemHolder.imageView2, data.getImageUrl2());
        ImageLoaderUtil.ImageLoader(itemHolder.imageView3, data.getImageUrl3());
        itemHolder.textView1.setText(data.getTitle1());
        itemHolder.textView2.setText(data.getTitle2());
        itemHolder.textView3.setText(data.getTitle3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
