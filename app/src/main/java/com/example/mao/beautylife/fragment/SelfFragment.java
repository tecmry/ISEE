package com.example.mao.beautylife.fragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.activity.FullImageActivity;
import com.example.mao.beautylife.activity.LoginActivity;
import com.example.mao.beautylife.bottommenu.BottomMenuFragment;
import com.example.mao.beautylife.bottommenu.MenuItem;
import com.example.mao.beautylife.bottommenu.MenuItemOnClickListener;
import com.example.mao.beautylife.databinding.FragmentSelfBinding;
import com.example.mao.beautylife.util.ImageLoaderUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by -- Mao on 2017/11/25.
 */

public class SelfFragment extends Fragment {


    private static final String TAG = "SelfFragment";
    private FragmentSelfBinding binding;
    private final int APPLY_WRITE = 1;
    private final int CHOOSE_PHOTO = 2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_self, container, false);
        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        binding.selfUserName.setText(AVUser.getCurrentUser().getUsername());
        ImageLoaderUtil.ImageLoader(this, binding.selfUserImage, pref.getString("imageUrl", "http://ac-ecIHPESH.clouddn.com/b5364792bd7d4a31161f.png"));
        binding.selfUserImage.setOnClickListener(view -> {
            BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
            List<MenuItem> menuItemList = new ArrayList<MenuItem>();
            MenuItem menuItem1 = new MenuItem();
            menuItem1.setText("查看放大图像");
            menuItem1.setStyle(MenuItem.MenuItemStyle.COMMON);
            menuItem1.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem1) {
                @Override
                public void onClickMenuItem(View v, MenuItem menuItem) {
                    Intent intent = new Intent(getActivity(), FullImageActivity.class);
                    intent.putExtra("imageUrl", pref.getString("imageUrl", "http://ac-ecIHPESH.clouddn.com/b5364792bd7d4a31161f.png"));
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            });
            MenuItem menuItem2 = new MenuItem();
            menuItem2.setText("修改头像");
            menuItem2.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem2) {
                @Override
                public void onClickMenuItem(View v, MenuItem menuItem) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, APPLY_WRITE);
                    }else {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, APPLY_WRITE);
                        }else {
                            openAlbum();
                        }
                    }
                }
            });
            menuItemList.add(menuItem1);
            menuItemList.add(menuItem2);
            bottomMenuFragment.setMenuItems(menuItemList);
            bottomMenuFragment.show(getActivity().getFragmentManager(), "BottomMenuFragment");
        });
        return binding.getRoot();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case APPLY_WRITE:
                openAlbum();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                handleImage(data);
            default:
                break;
        }
    }

    private void handleImage(Intent data){
        if (data == null)
            return;
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getActivity(), uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath != null){
            ImageLoaderUtil.ImageLoader(this, binding.selfUserImage, imagePath);
            editor = pref.edit();
            editor.putString("imageUrl", imagePath);
            editor.apply();
        }
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

}
