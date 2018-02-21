package com.example.mao.beautylife.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivityFullImageBinding;

public class FullImageActivity extends AppCompatActivity {

    private ActivityFullImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_image);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        final String url = getIntent().getStringExtra("imageUrl");
        GlideApp.with(this).load(url)
                .fitCenter()
                .into(binding.activityFullImagePhoto);
        binding.activityFullImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
