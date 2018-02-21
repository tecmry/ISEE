package com.example.mao.beautylife.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivityMapBinding;
import com.example.mao.beautylife.event.MapEvent;
import com.example.mao.beautylife.service.MapService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MapActivity extends AppCompatActivity implements Inputtips.InputtipsListener, ServiceConnection {

    private ActivityMapBinding binding;
    private MapService.MapBinder mapBinder;
    private AMap aMap;
    private LatLng latLng;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.activityMapMap.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        //第一个参数搜索内容  第二个参数城市
        InputtipsQuery inputQuery = new InputtipsQuery(intent.getStringExtra(""), intent.getStringExtra(""));
        Inputtips inputTips = new Inputtips(getApplicationContext(), inputQuery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap = binding.activityMapMap.getMap();
        }
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMapEvent(MapEvent event){
        AMapLocation amapLocation = event.getLocation();
        String local = amapLocation.getProvince() + amapLocation.getCity();
        binding.activityMapCurrentLocal.setText(local);
        //将地图移动到定位点
        latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
        //添加图钉
        aMap.addMarker(getMarkerOptions(amapLocation));
    }

    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
//        options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fire));
        //位置
        options.position(latLng);
        StringBuffer buffer = new StringBuffer();
        buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() +  "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        //标题
        options.title(buffer.toString());
        //子标题
        //设置多少帧刷新一次图片资源
        options.period(60);
        return options;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MapService.class);
        intent.putExtra("isFirstLoc", true);
        startService(intent);
        binding.activityMapMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, MapService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        binding.activityMapMap.onDestroy();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {

    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mapBinder = (MapService.MapBinder) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}
