package com.example.mywechat;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener{

    private Fragment mTab01 = new weixinFragment();
    private Fragment mTab02 = new frdFragment();
    private Fragment mTab03 = new contactFragment();
    private Fragment mTab04 = new settingFragment();

    private FragmentManager fm;

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabContact;
    private LinearLayout mTabSetting;

    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgContact;
    private ImageButton mImgSetting;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initEvent();
       selectFragment(0);
    }

    private void initView(){
        mTabWeixin = (LinearLayout)findViewById(R.id.weixin);
        mTabFrd = (LinearLayout)findViewById(R.id.frd);
        mTabContact = (LinearLayout)findViewById(R.id.contact);
        mTabSetting = (LinearLayout)findViewById(R.id.setting);

        mImgWeixin = (ImageButton)findViewById(R.id.img_winxin);
        mImgFrd = (ImageButton)findViewById(R.id.img_frd);
        mImgContact = (ImageButton)findViewById(R.id.img_contact);
        mImgSetting = (ImageButton)findViewById(R.id.img_setting);
    }
    //加入4个Fragment
    private void initFragment(){
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }
    //隐藏页面
    private void hideFragment(FragmentTransaction tansaction){
        tansaction.hide(mTab01);
        tansaction.hide(mTab02);
        tansaction.hide(mTab03);
        tansaction.hide(mTab04);
    }
    //选择页面
    private void selectFragment(int i){
        FragmentTransaction transaction =fm.beginTransaction();
        hideFragment(transaction);
        switch(i){
            case 0:
                transaction.show(mTab01);
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                transaction.show(mTab03);
                mImgContact.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgSetting.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v){
        resetImg();
        switch (v.getId()){
            case R.id.weixin:
                selectFragment(0);
                break;
            case R.id.frd:
                selectFragment(1);
                break;
            case R.id.contact:
                selectFragment(2);
                break;
            case R.id.setting:
                selectFragment(3);
                break;
            default:break;
        }
    }

    //img变灰
    public void resetImg(){
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgContact.setImageResource(R.drawable.tab_address_normal);
        mImgSetting.setImageResource(R.drawable.tab_settings_normal);

    }

    //监听优化
    private void initEvent(){
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabContact.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

    }
    //本地数据库

}
