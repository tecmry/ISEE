package com.example.mao.beautylife.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mao.beautylife.data.SearchData;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.SearchRecyclerAdapter;
import com.example.mao.beautylife.databinding.ActivitySearchResultBinding;
import com.example.mao.beautylife.sql.SearchHistoryDataSQL;
import com.example.mao.beautylife.util.HttpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



public class SearchResultActivity extends AppCompatActivity implements OnLoadmoreListener, SearchRecyclerAdapter.ItemListener, TextView.OnEditorActionListener {

    private static final String TAG = "SearchResultActivity";
    private int current = 0;
    private ActivitySearchResultBinding binding;
    private String keyWord;
    private List<SearchData> list = new ArrayList<>();
    private SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(list, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding.activitySearchResultRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.activitySearchResultRecycler.setAdapter(adapter);
        keyWord = getIntent().getStringExtra("keyWord");
        binding.activitySearchResultSearch.setHint(keyWord);
        binding.activitySearchResultRefresh.setOnLoadmoreListener(this);
        binding.activitySearchResultSearch.setOnEditorActionListener(this);
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    parseJSON(response.body());
                }else {
                    Toast.makeText(SearchResultActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            current = 0;
            binding.activitySearchResultSearch.setText("");
            binding.activitySearchResultRefresh.setEnableRefresh(true);
            binding.activitySearchResultRefresh.autoRefresh();
            list.clear();
            keyWord = content;
            request(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    if (response.isSuccessful()){
                        parseJSON(response.body());
                        binding.activitySearchResultRefresh.finishRefresh();
                        binding.activitySearchResultRefresh.setEnableRefresh(false);
                    }else {
                        Toast.makeText(SearchResultActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return true;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    parseJSON(response.body());
                    binding.activitySearchResultRefresh.finishLoadmore();
                }else {
                    Toast.makeText(SearchResultActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void itemCallback(View itemView) {

    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/product/search")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .params("currentPage", current)
                .params("keyword", keyWord)
                .execute(callback);
    }

    private void parseJSON(String jsonData){
        current++;
        try {
            JSONArray array = new JSONObject(jsonData).getJSONArray("products");
            for (int i = 0; i < array.length(); i += 3){
                JSONObject object1 = array.getJSONObject(i);
                JSONObject object2 = array.getJSONObject(i + 1);
                JSONObject object3 = array.getJSONObject(i + 2);
                SearchData data = new SearchData();
                data.setImageUrl1(object1.getString("imgurl"));
                data.setTitle1(object1.getString("title"));
                data.setImageUrl2(object2.getString("imgurl"));
                data.setTitle2(object2.getString("title"));
                data.setImageUrl3(object3.getString("imgurl"));
                data.setTitle3(object3.getString("title"));
                list.add(data);
                adapter.notifyItemChanged(list.size() - 1);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
