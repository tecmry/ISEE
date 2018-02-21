package com.example.mao.beautylife.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.sql.SearchHistoryDataSQL;
import com.example.mao.beautylife.databinding.ActivitySearchBinding;
import com.example.mao.beautylife.util.HttpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding.activitySearchImageGarbage.setOnClickListener(this);
        binding.activitySearchBack.setOnClickListener(view -> onBackPressed());
        initHotText();
        initHistoryText();
        binding.activitySearchSearch.setOnEditorActionListener(this);

    }

    @Override
    public void onClick(View view) {
        String content;
        switch (view.getId()){
            case R.id.activity_search_image_garbage:
                DataSupport.deleteAll(SearchHistoryDataSQL.class);
                binding.activitySearchHistoryText1.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText2.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText3.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText4.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText5.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText6.setVisibility(View.INVISIBLE);
                binding.activitySearchHistoryText7.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "成功清除搜索记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_search_hot_text_1:
                content = binding.activitySearchHotText1.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_2:
                content = binding.activitySearchHotText2.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_3:
                content = binding.activitySearchHotText3.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_4:
                content = binding.activitySearchHotText4.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_5:
                content = binding.activitySearchHotText5.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_6:
                content = binding.activitySearchHotText6.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_hot_text_7:
                content = binding.activitySearchHotText7.getText().toString();
                clickText(content);
                break;
            case R.id.activity_search_history_text_1:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_2:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_3:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_4:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_5:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_6:
                clickText(binding.activitySearchHistoryText1.getText().toString());
                break;
            case R.id.activity_search_history_text_7:
                clickText(binding.activitySearchHistoryText7.getText().toString());
                break;
            default:
                break;
        }
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
        binding.activitySearchHotText1.setOnClickListener(this);
        binding.activitySearchHotText2.setOnClickListener(this);
        binding.activitySearchHotText3.setOnClickListener(this);
        binding.activitySearchHotText4.setOnClickListener(this);
        binding.activitySearchHotText5.setOnClickListener(this);
        binding.activitySearchHotText6.setOnClickListener(this);
        binding.activitySearchHotText7.setOnClickListener(this);
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
        binding.activitySearchHotText4.setText(list.get(temp));
        list.remove(temp);
        temp = random.nextInt(list.size());
        binding.activitySearchHotText4.setText(list.get(temp));
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

    private void initHistoryText(){
        binding.activitySearchHistoryText1.setOnClickListener(this);
        binding.activitySearchHistoryText2.setOnClickListener(this);
        binding.activitySearchHistoryText3.setOnClickListener(this);
        binding.activitySearchHistoryText4.setOnClickListener(this);
        binding.activitySearchHistoryText5.setOnClickListener(this);
        binding.activitySearchHistoryText6.setOnClickListener(this);
        binding.activitySearchHistoryText7.setOnClickListener(this);
        int num = SearchHistoryDataSQL.count("searchhistorydatasql");
        if (num < 7){
            List<SearchHistoryDataSQL> list = DataSupport.findAll(SearchHistoryDataSQL.class);
            switch (num){
                case 6:
                    binding.activitySearchHistoryText1.setText(list.get(5).getContent());
                    binding.activitySearchHistoryText2.setText(list.get(4).getContent());
                    binding.activitySearchHistoryText3.setText(list.get(3).getContent());
                    binding.activitySearchHistoryText4.setText(list.get(2).getContent());
                    binding.activitySearchHistoryText5.setText(list.get(1).getContent());
                    binding.activitySearchHistoryText6.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText3.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText4.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText5.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText6.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    binding.activitySearchHistoryText1.setText(list.get(4).getContent());
                    binding.activitySearchHistoryText2.setText(list.get(3).getContent());
                    binding.activitySearchHistoryText3.setText(list.get(2).getContent());
                    binding.activitySearchHistoryText4.setText(list.get(1).getContent());
                    binding.activitySearchHistoryText5.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText3.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText4.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText5.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    binding.activitySearchHistoryText1.setText(list.get(3).getContent());
                    binding.activitySearchHistoryText2.setText(list.get(2).getContent());
                    binding.activitySearchHistoryText3.setText(list.get(1).getContent());
                    binding.activitySearchHistoryText4.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText3.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText4.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    binding.activitySearchHistoryText1.setText(list.get(2).getContent());
                    binding.activitySearchHistoryText2.setText(list.get(1).getContent());
                    binding.activitySearchHistoryText3.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText3.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    binding.activitySearchHistoryText1.setText(list.get(1).getContent());
                    binding.activitySearchHistoryText2.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    binding.activitySearchHistoryText1.setText(list.get(0).getContent());
                    binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }else {
            List<SearchHistoryDataSQL> list = DataSupport.findAll(SearchHistoryDataSQL.class).subList(num - 7, num);
            binding.activitySearchHistoryText1.setText(list.get(6).getContent());
            binding.activitySearchHistoryText2.setText(list.get(5).getContent());
            binding.activitySearchHistoryText3.setText(list.get(4).getContent());
            binding.activitySearchHistoryText4.setText(list.get(3).getContent());
            binding.activitySearchHistoryText5.setText(list.get(2).getContent());
            binding.activitySearchHistoryText6.setText(list.get(1).getContent());
            binding.activitySearchHistoryText7.setText(list.get(0).getContent());
            binding.activitySearchHistoryText1.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText2.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText3.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText4.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText5.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText6.setVisibility(View.VISIBLE);
            binding.activitySearchHistoryText7.setVisibility(View.VISIBLE);
        }
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
