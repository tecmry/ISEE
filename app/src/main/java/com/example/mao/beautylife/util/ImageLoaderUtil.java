package com.example.mao.beautylife.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;


/**
 * Created by lenovo on 2017/8/20.
 */

public class ImageLoaderUtil {

    public static void ImageLoader(Context context, ImageView imageView, Object url){
        GlideApp.with(context).load(url).placeholder(R.drawable.place_holder).error(R.drawable.error).centerCrop().into(imageView);
    }

    public static void ImageLoader(Activity activity, ImageView imageView, Object url){
        GlideApp.with(activity).load(url).placeholder(R.drawable.place_holder).error(R.drawable.error).fitCenter().into(imageView);
    }

    public static void ImageLoader(Fragment fragment, ImageView imageView, Object url){
        GlideApp.with(fragment).load(url).placeholder(R.drawable.place_holder).error(R.drawable.error).centerCrop().into(imageView);
    }

    public static void ImageLoader(ImageView imageView, Object url){
        GlideApp.with(imageView).load(url).placeholder(R.drawable.place_holder).error(R.drawable.error).centerCrop().into(imageView);
    }

}
