package com.example.mao.beautylife.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivityProductBinding;
import com.example.mao.beautylife.fragment.ProductEvaluateFragment;
import com.example.mao.beautylife.fragment.ProductOfficialFragment;
import com.example.mao.beautylife.fragment.ProductSimilarityFragment;
import com.example.mao.beautylife.fragment.ProductTestFragment;
import com.example.mao.beautylife.service.MapService;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;

public class ProductActivity extends AppCompatActivity implements OnBMClickListener, View.OnClickListener {

    private ActivityProductBinding binding;
    private FragmentManager manager;

    private ProductOfficialFragment officialFragment = new ProductOfficialFragment();
    private ProductEvaluateFragment evaluateFragment = new ProductEvaluateFragment();
    private ProductTestFragment testFragment = new ProductTestFragment();
    private ProductSimilarityFragment similarityFragment = new ProductSimilarityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        initBmb();
        binding.activityProductTextOne.setOnClickListener(this);
        binding.activityProductTextTwo.setOnClickListener(this);
        binding.activityProductTextThree.setOnClickListener(this);
        binding.activityProductTextFour.setOnClickListener(this);
        manager = getSupportFragmentManager();
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("brand", intent.getStringExtra("brand"));
        officialFragment.setArguments(bundle);

        manager.beginTransaction().replace(R.id.activity_product_fragment, officialFragment).commit();
        binding.activityProductTextOne.setOnClickListener(view -> {
            TextView textView = (TextView) view;
            textView.setTextColor(Color.BLACK);
            binding.activityProductTextTwo.setTextColor(Color.WHITE);
            binding.activityProductTextThree.setTextColor(Color.WHITE);
            binding.activityProductTextFour.setTextColor(Color.WHITE);
            manager.beginTransaction().replace(R.id.activity_product_fragment, officialFragment).commit();
        });
        binding.activityProductTextTwo.setOnClickListener(view -> {
            TextView textView = (TextView) view;
            textView.setTextColor(Color.BLACK);
            binding.activityProductTextOne.setTextColor(Color.WHITE);
            binding.activityProductTextThree.setTextColor(Color.WHITE);
            binding.activityProductTextFour.setTextColor(Color.WHITE);
            manager.beginTransaction().replace(R.id.activity_product_fragment, evaluateFragment).commit();
        });
        binding.activityProductTextThree.setOnClickListener(view -> {
            TextView textView = (TextView) view;
            textView.setTextColor(Color.BLACK);
            binding.activityProductTextTwo.setTextColor(Color.WHITE);
            binding.activityProductTextOne.setTextColor(Color.WHITE);
            binding.activityProductTextFour.setTextColor(Color.WHITE);
            manager.beginTransaction().replace(R.id.activity_product_fragment, testFragment).commit();
        });
        binding.activityProductTextFour.setOnClickListener(view -> {
            TextView textView = (TextView) view;
            textView.setTextColor(Color.BLACK);
            binding.activityProductTextTwo.setTextColor(Color.WHITE);
            binding.activityProductTextThree.setTextColor(Color.WHITE);
            binding.activityProductTextOne.setTextColor(Color.WHITE);
            manager.beginTransaction().replace(R.id.activity_product_fragment, similarityFragment).commit();
        });
    }

    @Override
    public void onBoomButtonClick(int index) {
        switch (index){
            case 0:
                //拔草

                break;
            case 1:
                //点赞

                break;
            case 2:
                //分享

                break;
            case 3:
                //收藏

                break;
            case 4:
                //种草

                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }

    private void initBmb(){
        binding.activityProductBmb.setNormalColor(Color.rgb(252, 199, 195));
        binding.activityProductBmb.setHighlightedColor(Color.RED);
        TextInsideCircleButton.Builder builder_1, builder_2, builder_3, builder_4, builder_5;
        builder_1 = new TextInsideCircleButton.Builder();
        builder_2 = new TextInsideCircleButton.Builder();
        builder_3 = new TextInsideCircleButton.Builder();
        builder_4 = new TextInsideCircleButton.Builder();
        builder_5 = new TextInsideCircleButton.Builder();
        initBmb(builder_1);
        initBmb(builder_2);
        initBmb(builder_3);
        initBmb(builder_4);
        initBmb(builder_5);
        builder_1.normalText("拔草").normalColor(Color.rgb(219, 187, 115)).normalImageDrawable(getDrawable(R.drawable.bmb_bacao_image));
        builder_2.normalText("点赞").normalColor(Color.rgb(255, 239, 152)).normalImageDrawable(getDrawable(R.drawable.bmb_like_image));
        builder_3.normalText("分享").normalColor(Color.rgb(25, 113, 235)).normalImageDrawable(getDrawable(R.drawable.bmb_share_image));
        builder_4.normalText("收藏").normalColor(Color.rgb(253, 195, 196)).normalImageDrawable(getDrawable(R.drawable.bmb_collect_image));
        builder_5.normalText("种草").normalColor(Color.rgb(143, 233, 160)).normalImageDrawable(getDrawable(R.drawable.bmb_zhongcao_image));
        binding.activityProductBmb.addBuilder(builder_1);
        binding.activityProductBmb.addBuilder(builder_2);
        binding.activityProductBmb.addBuilder(builder_3);
        binding.activityProductBmb.addBuilder(builder_4);
        binding.activityProductBmb.addBuilder(builder_5);

    }

    private void initBmb(TextInsideCircleButton.Builder builder){
        builder.listener(this).imagePadding(new Rect(35, 35, 35, 40)).rotateText(true).rotateText(true);
    }

}
