package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tecmry on 2017/12/10.
 */

public class YOthersPushitemAdapter extends RecyclerView.Adapter<YOthersPushitemAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG ="YOthersPushitemAdapter" ;

    private YWithMeItemAdapter adpter;

    private Context context;
    private List<String> mList = new ArrayList<>();
    private YOthersPushitemAdapter(Context context,List<String> list)
    {
     this.context = context;
     this.mList = list;
    }
    public interface OnItemClickListner {

        void OnItemClickListner(View view, int position);
    }

    private OnItemClickListner listner;

    public void setItemClickListner(OnItemClickListner listner) {
        this.listner = listner;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yotheruser_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        /*
         *在这里处理时间逻辑
         * **/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if (listner != null) {
            listner.OnItemClickListner(v, (int) v.getTag());
//            Log.d(TAG, "objid" + list.get((int) v.getTag()).getObjectId());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userimg;
        ImageView articleimg;

        TextView username;
        TextView tag_one;
        TextView tags_two;
        TextView forwardnumber;
        TextView getnumber;//收藏
        TextView likenumber;

        TextView articletitle;
        TextView articlelittle;


        public ViewHolder(View itemView) {
            super(itemView);

            userimg = (ImageView)itemView.findViewById(R.id.yothers_username);
            articleimg = (ImageView)itemView.findViewById(R.id.yother_talkimg);

            username = (TextView)itemView.findViewById(R.id.yotherusername);
            tag_one = (TextView)itemView.findViewById(R.id.yothers_tags_one);
            tags_two = (TextView)itemView.findViewById(R.id.yothers_tag_two);

            forwardnumber = (TextView)itemView.findViewById(R.id.yforwardnumber);
            getnumber = (TextView)itemView.findViewById(R.id.ygetnumber);
            likenumber = (TextView)itemView.findViewById(R.id.ylikenumber);

            articlelittle = (TextView)itemView.findViewById(R.id.ytalk_little);
            articletitle = (TextView)itemView.findViewById(R.id.yother_talktitle);
        }
    }
}
