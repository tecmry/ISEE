package com.example.mao.beautylife;


import com.avos.avoscloud.AVOSCloud;
import com.lzy.okgo.OkGo;

import org.litepal.LitePalApplication;

/**
 * Created by -- Mao on 2017/11/25.
 */

public class MyLeanCloudApp extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"ecIHPESHNrbdCx4IakbXzBK6-gzGzoHsz","nQiL0u9CvujDB7DlUymnqVfN");         //设置全局公共参数

    }
}
