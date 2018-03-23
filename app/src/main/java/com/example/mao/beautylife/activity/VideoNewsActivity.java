package com.example.mao.beautylife.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.VideoNewsData;
import com.example.mao.beautylife.listener.OnTransitionListener;
import com.example.mao.beautylife.video.SampleVideo;
import com.example.mao.beautylife.video.SwitchVideoModel;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tecmry on 2018/3/23.
 */

public class VideoNewsActivity extends AppCompatActivity implements View.OnClickListener
{

    private final static String IMG_TRANSITION = "IMG_TRANSITION";
    private final static String TRANSITION = "TRANSITION";
    private TextView videoTitle;
    private TextView videoNewsContentTitle;
    private TextView videoDetails;

    private TextView userName;
    private TextView userTagsOne;
    private TextView userTagsTwo;

    private Button focusUser;

    private ImageView userHead;

    private VideoNewsData newsData;

    private SampleVideo sampleVideo;
    private List<SwitchVideoModel> list = new ArrayList<>();

    private Transition transition;
    private boolean isTransition;
    private OrientationUtils orientationUtils;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_video_news);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        init();

    }
    private void init()
    {
        videoTitle = findViewById(R.id.TV_videotitle);
        videoNewsContentTitle = findViewById(R.id.Tv_newscontent);
        videoDetails = findViewById(R.id.Tv_VideoDetails);

        userName = findViewById(R.id.videonews_username);
        userTagsOne = findViewById(R.id.videonews_usertagsone);
        userTagsTwo = findViewById(R.id.videonews_usertagstwo);

        focusUser = findViewById(R.id.Bt_focusUser);

        userHead = findViewById(R.id.videonews_userImg);
        sampleVideo = findViewById(R.id.video_player);

        newsData = (VideoNewsData)getIntent().getSerializableExtra("newsData");

        if (newsData!=null)
        {
            videoTitle.setText(newsData.getTitle());
            videoNewsContentTitle.setText(newsData.getContent());
            videoDetails.setText(newsData.getVideoDetails());
            userName.setText(newsData.getVideoUserName());
            Glide.with(this).load(newsData.getVideoUserHeadUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userHead);
            if (newsData.getVideoUserTags().size()!=0)
            {
                if (newsData.getVideoUserTags().size()==1)
                {
                    userTagsOne.setText(newsData.getVideoUserTags().get(0));
                    userTagsTwo.setVisibility(View.GONE);
                }
                if (newsData.getVideoUserTags().size()==2)
                {
                    userTagsOne.setText(newsData.getVideoUserTags().get(0));
                    userTagsTwo.setText(newsData.getVideoUserTags().get(1));
                }
            }
            list.add(new SwitchVideoModel("标清",newsData.getVideoUrl()));

        }else {
            Toast.makeText(this,"数据传输错误()",Toast.LENGTH_SHORT).show();
        }
        focusUser.setOnClickListener(this);

        sampleVideo.setUp(list, true, newsData.getTitle());
        sampleVideo.getTitleTextView().setVisibility(View.VISIBLE);
        orientationUtils = new OrientationUtils(this, sampleVideo);
        sampleVideo.setIsTouchWiget(true);

       sampleVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

       sampleVideo.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initTransition();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Bt_focusUser:
                /**
                 * 在这里处理关注按钮的逻辑
                 * **/
                break;
        }
    }

    private void initTransition() {
        if (isTransition) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(sampleVideo, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            sampleVideo.startPlayLogic();
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    sampleVideo.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }
    @Override
    protected void onPause() {
        super.onPause();
        sampleVideo.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sampleVideo.onVideoResume();
    }
}
