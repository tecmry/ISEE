package com.example.mao.beautylife.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mao.beautylife.R;

import java.util.List;

/**
 * Created by Tecmry on 2017/12/9.
 */

public class VoteActivity extends AppCompatActivity {

    private TextView questionTitle;
    private List<String> nameList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.yeverydaysignin);
        init();
        LoadData();
    }
    private void init() {
        questionTitle = findViewById(R.id.yquestiontitle);

    }
    /***
     * 使用Retrofit进行数据请求
     * */
    private void LoadData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
