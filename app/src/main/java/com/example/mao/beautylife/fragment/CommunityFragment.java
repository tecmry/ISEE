package com.example.mao.beautylife.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.RecycleViewDivider;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.adapter.SmartViewHolder;
import com.example.mao.beautylife.base.BaseFragment;
import com.example.mao.beautylife.data.ColorData;
import com.example.mao.beautylife.data.InfoData;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.databinding.FragmentCommunityBinding;
import com.example.mao.beautylife.util.HttpUtil;
import com.example.mao.beautylife.util.ImageLoaderUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by -- Mao on 2017/11/27.
 */

public class CommunityFragment extends BaseFragment implements ImageLoaderInterface {

    private static final String TAG = "CommunityFragment";
    private FragmentCommunityBinding binding;
    private CommonRecyclerAdapter colorAdapter, videoAdapter, infoAdapter;
    private List<ColorData> colorDataList;
    private List<VideoData> videoDataList;
    private List<InfoData> infoDataList;
    private SimpleDateFormat formatter= new SimpleDateFormat("mm:ss", Locale.CHINA);

    class VideoItem extends SmartViewHolder {

        ImageView imageView;
        TextView playNum, playTime, videoTitle;

        public VideoItem(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.video_item_image);
            playNum = itemView.findViewById(R.id.video_item_num);
            playTime = itemView.findViewById(R.id.video_item_time);
            videoTitle = itemView.findViewById(R.id.video_item_title);
        }

    }

    class ColorItem extends SmartViewHolder {

        CircleImageView imageView;

        public ColorItem(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.color_item_image);
        }

    }

    class InfoItem extends SmartViewHolder {

        public InfoItem(View itemView) {
            super(itemView);

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        initBanner();
        initAdapter();
        videoDataList = new ArrayList<>();
        colorDataList = new ArrayList<>();
        infoDataList = new ArrayList<>();
        binding.fragmentCommunityRecyclerVideo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.fragmentCommunityRecyclerColor.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.fragmentCommunityRecyclerInfo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.fragmentCommunityRecyclerVideo.addItemDecoration(new RecycleViewDivider(getContext(), 20, 50));
        binding.fragmentCommunityRecyclerColor.addItemDecoration(new RecycleViewDivider(getContext(), 20, 50));
        binding.fragmentCommunityRecyclerVideo.setAdapter(videoAdapter);
        binding.fragmentCommunityRecyclerColor.setAdapter(colorAdapter);
        binding.fragmentCommunityRecyclerInfo.setAdapter(infoAdapter);
        final Intent intent;
        binding.fragmentCommunityOne.setOnClickListener(v -> {

        });
        binding.fragmentCommunityTwo.setOnClickListener(v -> {

        });
        binding.fragmentCommunityThree.setOnClickListener(v -> {

        });
        return binding.getRoot();
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            Log.d(TAG, "可见");
        }else {
            Log.d(TAG, "不可见");
        }
    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */

    @Override
    protected void onFragmentFirstVisible() {
        Log.d(TAG, "初次可见");
        binding.refreshLayout.autoRefresh();
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    parseJSON(response.body());
                }else {
                    Toast.makeText(getActivity(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void parseJSON(String jsonData){
        try {
            JSONObject object = new JSONObject(jsonData);
            JSONObject jsonObject = null;
            JSONArray video = object.getJSONArray("videos");
            JSONArray color = object.getJSONArray("tColors");
            for (int i = 0; i < video.length(); i++) {
                jsonObject = video.getJSONObject(i);
                videoDataList.add(new VideoData(jsonObject.getString("nickname"),
                                                    jsonObject.getString("title"),
                                                    jsonObject.getString("coverUrl"),
                                                    jsonObject.getInt("likeTimes"),
                                                    jsonObject.getString("videourl"),
                                                    jsonObject.getString("duration")));
                videoAdapter.notifyItemChanged(videoDataList.size() - 1);
            }
            for (int i = 0; i < color.length(); i++) {
                jsonObject = color.getJSONObject(i);
                ColorData data = new ColorData();
                data.setImageUrl(jsonObject.getString("imgUrl"));
                colorAdapter.notifyItemChanged(colorDataList.size() - 1);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void initBanner(){
        binding.fragmentCommunityPager.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        binding.fragmentCommunityPager.setImageLoader(this);
        List<Integer> list = new LinkedList<>();
        list.add(R.drawable.commnuity_banner_one);
        list.add(R.drawable.commnuity_banner_two);
        list.add(R.drawable.community_banner_three);
        list.add(R.drawable.community_banner_four);
        binding.fragmentCommunityPager.setImages(list);
        binding.fragmentCommunityPager.setBannerAnimation(Transformer.DepthPage);
        binding.fragmentCommunityPager.isAutoPlay(true);
        binding.fragmentCommunityPager.setDelayTime(1500);
        binding.fragmentCommunityPager.start();
    }

    private void initAdapter(){
        colorAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item, parent, false);
            return new ColorItem(view);
        }, (holder, position) -> {
            ColorData data = colorDataList.get(position);
            ColorItem colorItem = (ColorItem) holder;
            ImageLoaderUtil.ImageLoader(colorItem.imageView, data.imageUrl);
        }, () -> {
           return colorDataList.size();
        });

        videoAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
            return new VideoItem(view);
        }, (holder, position) -> {
            VideoData data = videoDataList.get(position);
            VideoItem videoItem = (VideoItem)holder;
            ImageLoaderUtil.ImageLoader(videoItem.imageView, data.getImageUrl());
            videoItem.playNum.setText("播放量:" + data.getNum());
            String title = data.getTitle();
            if (title.length() > 20)
                videoItem.videoTitle.setText(title.substring(0, 18) + "...");
            else
                videoItem.videoTitle.setText(title);
            videoItem.playTime.setText(formatter.format(1000 * Integer.valueOf(data.getVideoTime())));
        }, () -> {
            return videoDataList.size();
        });

        infoAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
            return new InfoItem(view);
        }, (holder, position) -> {

        }, () -> {
            return infoDataList.size();
        });
    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .execute(callback);
    }

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        GlideApp.with(this)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        return null;
    }

}
