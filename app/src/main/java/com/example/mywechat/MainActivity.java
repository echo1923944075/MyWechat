package com.example.mywechat;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.os.Bundle;
import android.os.Handler;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

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
        //权限申请
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //添加按钮事件
        ImageButton btnAdd = (ImageButton) findViewById(R.id.ib_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout views = (LinearLayout) getLayoutInflater().inflate(R.layout.add, null);
                new AlertDialog.Builder(MainActivity.this).setTitle("添加联系人").setView(views)
                        .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                         @Override
                         //添加按钮事件
                         public void onClick(DialogInterface dialog, int which) {
                             EditText et1 = views.findViewById(R.id.et_addName);
                             EditText et2 = views.findViewById(R.id.et_addPhone);
                             // 获取程序界面中的三个文本框的内容
                             String name = et1.getText().toString();
                             String phone = et2.getText().toString();
//                             String email = ((EditText) findViewById(R.id.email))
//                                     .getText().toString();
                             // 创建一个空的ContentValues
                             ContentValues values = new ContentValues();
                             // 向RawContacts.CONTENT_URI执行一个空值插入
                             // 目的是获取系统返回的rawContactId
                             Uri rawContactUri = getContentResolver().insert(
                                     ContactsContract.RawContacts.CONTENT_URI, values);
                             long rawContactId = ContentUris.parseId(rawContactUri);
                             values.clear();
                             values.put(Data.RAW_CONTACT_ID, rawContactId);
                             // 设置内容类型
                             values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
                             // 设置联系人名字
                             values.put(StructuredName.GIVEN_NAME, name);
                             // 向联系人URI添加联系人名字
                             getContentResolver().insert(ContactsContract
                                     .Data.CONTENT_URI, values);
                             values.clear();
                             values.put(Data.RAW_CONTACT_ID, rawContactId);
                             values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
                             // 设置联系人的电话号码
                             values.put(Phone.NUMBER, phone);
                             // 设置电话类型
                             values.put(Phone.TYPE, Phone.TYPE_MOBILE);
                             // 向联系人电话号码URI添加电话号码
                             getContentResolver().insert(ContactsContract
                                     .Data.CONTENT_URI, values);
                             values.clear();
                             values.put(Data.RAW_CONTACT_ID, rawContactId);
//                             values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
//                             // 设置联系人的E-mail地址
//                             values.put(Email.DATA, email);
//                             // 设置该电子邮件的类型
//                             values.put(Email.TYPE, Email.TYPE_WORK);
//                             // 向联系人E-mail URI添加E-mail数据
//                             getContentResolver().insert(ContactsContract
//                                     .Data.CONTENT_URI, values);
                             Toast.makeText(MainActivity.this, "联系人数据添加成功",
                                     Toast.LENGTH_SHORT).show();
                         }
                }).show();

            }
        });
        //弹窗
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
}

