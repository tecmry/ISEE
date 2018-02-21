package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.SpaceItemDecoration;
import com.example.mao.beautylife.adapter.ThreeRecyclerAdapter;
import com.example.mao.beautylife.data.BrandData;
import com.example.mao.beautylife.databinding.FragmentCommunityThreeBinding;
import com.example.mao.beautylife.listener.FootItemListener;
import com.example.mao.beautylife.util.HttpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by -- Mao on 2017/11/30.
 */

public class CommunityThreeFragment extends Fragment implements AdapterView.OnItemSelectedListener, OnRefreshListener, FootItemListener {

    private int current = 0;
    private List<BrandData> list = new ArrayList<>();
    private static final String TAG = "CommunityThreeFragment";
    private FragmentCommunityThreeBinding binding;
    private ThreeRecyclerAdapter adapter = new ThreeRecyclerAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_three, container, false);
        binding.fragmentCommunityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.fragmentCommunityRecycler.setAdapter(adapter);
        binding.fragmentCommunityRecycler.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.space)));
        List<String> strList = new ArrayList<>();
        strList.add("全部");
        strList.add("口红");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, strList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fragmentThreeSpinner.setAdapter(arrayAdapter);
        binding.fragmentThreeSpinner.setOnItemSelectedListener(this);

        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    current++;
                    parseJSON(response.body());
                }else {
                    Toast.makeText(getActivity(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void itemClick(View footItem) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home/brand")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .params("requestTime", current)
                .execute(callback);
    }

    private void parseJSON(String jsonData){
        try {
            JSONArray array = new JSONObject(jsonData).getJSONArray("brands");
            for (int i = 0; i < array.length(); i += 3){
                JSONObject object1 = array.getJSONObject(i);
                JSONObject object2 = array.getJSONObject(i + 1);
                JSONObject object3 = array.getJSONObject(i + 2);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
