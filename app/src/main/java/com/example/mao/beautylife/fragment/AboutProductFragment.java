package com.example.mao.beautylife.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.base.BaseFragment;
import com.example.mao.beautylife.data.NetVideoItemData;
import com.example.mao.beautylife.service.RecommendProductInterface;
import com.example.mao.beautylife.util.HttpUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tecmry on 2018/3/24.
 */

public class AboutProductFragment extends BaseFragment {

    /**
     *
     *
     * **/

    private TextView TV_One;
    private TextView TV_Two;
    private TextView TV_Three;
    private TextView TV_Four;
    private TextView TV_Five;
    private TextView TV_Six;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_news_productabout, container, false);
        TV_One = view.findViewById(R.id.TV_RLOne_Brand);
        TV_Two = view.findViewById(R.id.TV_RLOne_Product);
        TV_Three = view.findViewById(R.id.TV_RLTwo_Brand);
        TV_Four = view.findViewById(R.id.TV_RLTwo_Product);
        TV_Five = view.findViewById(R.id.TV_RLThree_Brand);
        TV_Six = view.findViewById(R.id.TV_RLThree_Product);

        GetProduct();

        return view;
    }
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected boolean isFragmentVisible() {
        return super.isFragmentVisible();
    }
    private void GetProduct()
    {
        new Thread(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUtil.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RecommendProductInterface videoService = retrofit.create(RecommendProductInterface.class);
            Call<NetVideoItemData> call = videoService.listRespnse(1);
            call.enqueue(new Callback<NetVideoItemData>() {
                @Override
                public void onResponse(Call<NetVideoItemData> call, Response<NetVideoItemData> response) {
                    Log.d("GetRecommendVideo", "Get is success");
                    NetVideoItemData netVideoItemData = response.body();
                    List<NetVideoItemData.ProductsBean> beanList = netVideoItemData.getProducts();

                    getActivity().runOnUiThread(()->
                    {
                        for (int i =0;i<beanList.size();i++)
                        {
                           if (i==1)
                           {
                               TV_One.setText(beanList.get(i).getBrand());
                               TV_Two.setText(beanList.get(i).getTitle());
                           }
                           if (i==2)
                           {
                               TV_Three.setText(beanList.get(i).getBrand());
                               TV_Four.setText(beanList.get(i).getTitle());
                           }
                           if (i==3)
                           {
                               TV_Five.setText(beanList.get(i).getBrand());
                               TV_Six.setText(beanList.get(i).getTitle());
                           }
                        }
                    });
                }

                @Override
                public void onFailure(Call<NetVideoItemData> call, Throwable t) {
                    Log.d("GetRecommendVideo","Get is out");
                }
            });
        }).start();
    }

}
