package com.example.mao.beautylife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;

/**
 * Created by Tecmry on 2017/12/17.
 */

public class YSelfItemAdapter extends RecyclerView.Adapter<YSelfItemAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public YSelfItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_self,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YSelfItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * l:代表左Item r:代表右Item
         * @Param  lImgv 左边大图
         * @Param  rImgv 右边大图
         * **/
        private ImageView lImgv;
        private ImageView rImgv;

        /**
         * 同上
         * @Param tags 表示用户属性
         * **/
        private TextView ltags_one;
        private TextView ltags_two;
        private TextView rtags_one;
        private TextView rtags_two;

        /**
         * @Param  Item的标题
         * **/
        private TextView ltitle;
        private TextView rtitle;

        /**
         * @Param Item的描述
         * **/
        private TextView lwrite;
        private TextView rwrite;
        /**
         * @Param  用户浏览量
         * **/
        private TextView lpeopleview;
        private TextView rpeopleview;
        /**
         * @Param  观看次数
         * **/
        private TextView lviewtimes;
        private TextView rviewtimes;

        public ViewHolder(View itemView) {
            super(itemView);
            lImgv = itemView.findViewById(R.id.item_img);
            rImgv = itemView.findViewById(R.id.yritem_img);

            ltags_one = itemView.findViewById(R.id.tags_one);
            ltags_two = itemView.findViewById(R.id.tags_two);
            rtags_one = itemView.findViewById(R.id.yrtags_one);
            rtags_two = itemView.findViewById(R.id.yrtags_two);

            ltitle = itemView.findViewById(R.id.yitem_name);
            rtitle = itemView.findViewById(R.id.yritem_name);

            lwrite = itemView.findViewById(R.id.yitem_write);
            rwrite = itemView.findViewById(R.id.yritem_write);

            lpeopleview = itemView.findViewById(R.id.ylviewpeople);
            rpeopleview = itemView.findViewById(R.id.yrviewpeople);

            lviewtimes = itemView.findViewById(R.id.ytv_time);
            rviewtimes = itemView.findViewById(R.id.yrtv_time);

        }
    }
}
