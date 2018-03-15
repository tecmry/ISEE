package com.example.mao.beautylife.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mao.beautylife.R;

import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.adapter.SmartViewHolder;
import com.example.mao.beautylife.sql.SearchHistoryDataSQL;
import com.example.mao.beautylife.databinding.ActivitySearchBinding;
import com.example.mao.beautylife.util.HttpUtil;
import com.example.mao.beautylife.util.ModeUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private ActivitySearchBinding binding;

    class SearchViewHolder extends SmartViewHolder{
        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ModeUtil.MIUISetStatusBarLightMode(getWindow(), true);
        ModeUtil.FlymeSetStatusBarLightMode(getWindow(), true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding.activitySearchHistoryRecycler.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
            return new SearchViewHolder(view);
        }, (holder, position) -> {

        }, () -> {
            return 0;
        }));
        binding.activitySearchSearch.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String content = textView.getText().toString();
        if (content.equals("")){
            Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }else {
            SearchHistoryDataSQL dataSQL = new SearchHistoryDataSQL();
            dataSQL.setContent(content);
            dataSQL.save();
            binding.activitySearchSearch.setText("");
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("keyWord", content);
            startActivity(intent);
        }
        return true;
    }

    private void initHotText(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "heat",
                "MAC", "YSL416", "Dior", "纪梵希304",
                "小羊皮", "口红", "口红", "唇釉",
                "酒红", "棕红", "裸色", "平价替代",
                "huda beauty", "nyx", "slow ride",
                "mac chili", "欧莱雅619", "欧莱雅639",
                "sartwoman", "秋冬", "日常", "南瓜色");
        Random random = new Random();
        int temp;
        temp = random.nextInt(list.size());
        binding.activitySearchHotText1.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText2.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText3.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText5.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText6.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText7.setText(list.get(temp));
        list.remove(temp);
    }


    private void clickText(String content){
        if (!content.equals("")){
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("keyWord", content);
            startActivity(intent);
            SearchHistoryDataSQL dataSQL = new SearchHistoryDataSQL();
            dataSQL.setContent(content);
            dataSQL.save();
        }
    }
}
