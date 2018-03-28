package com.example.mao.beautylife.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.activity.VideoNewsActivity;
import com.example.mao.beautylife.adapter.PageAdapter;
import com.example.mao.beautylife.base.BaseFragment;
import com.example.mao.beautylife.databinding.FragmentSelfBinding;
import com.example.mao.beautylife.util.ImageLoaderUtil;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import static com.tencent.plus.DensityUtil.dip2px;

/**
 * Created by -- Mao on 2017/11/25.
 */

public class SelfFragment extends BaseFragment {


    private static final String TAG = "SelfFragment";
    private FragmentSelfBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_self, container, false);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        binding.selfUserName.setText(AVUser.getCurrentUser().getUsername());
        ImageLoaderUtil.ImageLoader(this, binding.selfUserImage, pref.getString("imageUrl", "http://ac-ecIHPESH.clouddn.com/b5364792bd7d4a31161f.png"));
        List<Fragment> list = new LinkedList<>();
        list.add(new SelfPubFragment());
        list.add(new SelfHisFragment());
        binding.selfUserViewPager.setAdapter(new PageAdapter(getFragmentManager(), list));
        binding.fragmentSelfTab.setupWithViewPager(binding.selfUserViewPager);
        binding.selfUserViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.fragmentSelfTab));
        binding.fragmentSelfTab.getTabAt(0).setText("我的发布");
        binding.fragmentSelfTab.getTabAt(1).setText("我的足迹");
        reflex(binding.fragmentSelfTab);

        binding.selfUserImage.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), VideoNewsActivity.class));
        });

        return binding.getRoot();
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected boolean isFragmentVisible() {
        return super.isFragmentVisible();
    }

    public static void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
