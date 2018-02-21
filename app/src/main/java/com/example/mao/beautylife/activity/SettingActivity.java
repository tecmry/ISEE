package com.example.mao.beautylife.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.avos.avoscloud.AVUser;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivitySettingBinding;
import com.example.mao.beautylife.util.ImageLoaderUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initMaterialEditText();
        binding.activitySettingExit.setOnClickListener(view -> {
            AVUser.logOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        binding.activitySettingEditNameBtn.setOnClickListener(view -> {
            editMaterialEditText(binding.activitySettingEditName);
            ImageLoaderUtil.ImageLoader(this, binding.activitySettingEditNameBtn, R.drawable.write_selected);
        });
        binding.activitySettingBack.setOnClickListener(view -> {
            onBackPressed();
            saveEditText();
        });
    }

    private void initMaterialEditText(){
        initMaterialEditText(binding.activitySettingEditName, "name");
        initMaterialEditText(binding.activitySettingEditSex, "sex");
        initMaterialEditText(binding.activitySettingEditInfo, "info");
        initMaterialEditText(binding.activitySettingEditBirthday, "birthday");
        initMaterialEditText(binding.activitySettingEditLocal, "local");
        initMaterialEditText(binding.activitySettingEditEmail, "email");
        initMaterialEditText(binding.activitySettingEditQq, "qq");
    }

    private void editMaterialEditText(MaterialEditText editText){
        editText.setHideUnderline(false);
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
    }

    private void initMaterialEditText(MaterialEditText editText, String key){
        editText.setHideUnderline(true);
        editText.setFocusableInTouchMode(false);
        editText.setFocusable(false);
        editText.setText(pref.getString(key, "无"));
        editText.setTextColor(Color.BLACK);
    }

    private void saveEditText(){
        editor = pref.edit();
        editor.putString("name", binding.activitySettingEditName.getText().toString());
        editor.putString("sex", binding.activitySettingEditSex.getText().toString());
        editor.putString("info", binding.activitySettingEditInfo.getText().toString());
        editor.putString("birthday", binding.activitySettingEditBirthday.getText().toString());
        editor.putString("local", binding.activitySettingEditBirthday.getText().toString());
        editor.putString("email", binding.activitySettingEditEmail.getText().toString());
        editor.putString("qq", binding.activitySettingEditQq.getText().toString());
        editor.apply();
    }
}
