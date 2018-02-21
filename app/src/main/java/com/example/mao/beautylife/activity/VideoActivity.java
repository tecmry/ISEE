package com.example.mao.beautylife.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.example.mao.beautylife.R;

import com.example.mao.beautylife.databinding.ActivityVideoBinding;
import com.example.mao.beautylife.listener.OnTransitionListener;
import com.example.mao.beautylife.video.SwitchVideoModel;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    private final static String IMG_TRANSITION = "IMG_TRANSITION";
    private final static String TRANSITION = "TRANSITION";
    private ActivityVideoBinding binding;
    private OrientationUtils orientationUtils;
    private boolean isTransition;
    private Transition transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        Intent intent = getIntent();
        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(new SwitchVideoModel("标清", intent.getStringExtra("videoUrl")));
        init(list, intent.getStringExtra("title"));
    }

    private void init(List<SwitchVideoModel> list, String title) {

        binding.videoPlayer.setUp(list, true, title);

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
        binding.videoPlayer.setThumbImageView(imageView);

        //增加title
        binding.videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //videoPlayer.setShowPauseCover(false);

        //videoPlayer.setSpeed(2f);

        //设置返回键
        binding.videoPlayer.getBackButton().setVisibility(View.VISIBLE);

        //设置旋转
        orientationUtils = new OrientationUtils(this, binding.videoPlayer);

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        binding.videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

        //是否可以滑动调整
        binding.videoPlayer.setIsTouchWiget(true);

        //设置返回按键功能
        binding.videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //过渡动画
        initTransition();
    }


    @Override
    protected void onPause() {
        super.onPause();
        binding.videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.videoPlayer.onVideoResume();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            binding.videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        binding.videoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
        if (isTransition) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    private void initTransition() {
        if (isTransition) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(binding.videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            binding.videoPlayer.startPlayLogic();
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
                    binding.videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }
}
