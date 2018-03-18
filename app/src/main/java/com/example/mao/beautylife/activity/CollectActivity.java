package com.example.mao.beautylife.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivityCollectBinding;
import com.example.mao.beautylife.util.ModeUtil;

public class CollectActivity extends AppCompatActivity {

    private ActivityCollectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collect);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ModeUtil.MIUISetStatusBarLightMode(getWindow(), true);
        ModeUtil.FlymeSetStatusBarLightMode(getWindow(), true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding.activityCollectBack.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

    }
}
