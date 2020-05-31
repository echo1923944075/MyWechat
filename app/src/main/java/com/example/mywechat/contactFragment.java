package com.example.mywechat;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class contactFragment extends Fragment  {

    private View view;
    private RecyclerView recyclerView;
//    Context mcontext = getActivity();
    //存放数据
    private ArrayList<String> contactsList = new ArrayList<>();
    List<Contact> contacts;
    //定义适配器
    private ContactAdapter contactAdapter;
    final Uri uri=ContactsContract.Contacts.CONTENT_URI;  //Android联系人uri

    public contactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab03,container,false);
        getContacts();
        initRecyclerView();
        return view;
    }

    //配置reclerview
    private void initRecyclerView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_contact);
        //创建adpter
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        contactAdapter = new ContactAdapter(getActivity(),contactsList);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(manager);


    }

//
//    //回调方法，无论哪种结果，最终都会回调该方法，之后在判断用户是否授权，
//    // 用户同意则调用readContacts（）方法，失败则会弹窗提示失败
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    readContacts();
//                } else {
//                    Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//    }

    //获取所有联系人
    private void getContacts() {
//        // 定义两个List来封装系统的联系人信息、指定联系人的电话号码、Email等详情
//        final ArrayList<String> names = new ArrayList<>();
//        final ArrayList<ArrayList<String>> details = new ArrayList<>();
//
//        // 使用ContentResolver查找联系人数据
//        Cursor cursor =  mcontext.getContentResolver().query(
//                ContactsContract.Contacts.CONTENT_URI, null, null,
//                null, null);
//        // 遍历查询结果，获取系统中所有联系人
//        while (cursor.moveToNext())
//        {
//            // 获取联系人ID
//            String contactId = cursor.getString(cursor
//                    .getColumnIndex(ContactsContract.Contacts._ID));
//            // 获取联系人的名字
//            String name = cursor.getString(cursor.getColumnIndex(
//                    ContactsContract.Contacts.DISPLAY_NAME));
//            names.add(name);
//            // 使用ContentResolver查找联系人的电话号码
//            Cursor phones =  mcontext.getContentResolver().query(
//                    Phone.CONTENT_URI,
//                    null, Phone.CONTACT_ID
//                            + " = " + contactId, null, null);
//            ArrayList<String> detail = new ArrayList<>();
//            // 遍历查询结果，获取该联系人的多个电话号码
//            while (phones.moveToNext())
//            {
//                // 获取查询结果中电话号码列中数据
//                String phoneNumber = phones.getString(phones
//                        .getColumnIndex(Phone.NUMBER));
//                detail.add("电话号码：" + phoneNumber);
//            }
//            phones.close();
//            // 使用ContentResolver查找联系人的E-mail地址
//            Cursor emails =  mcontext.getContentResolver().query(
//                    Email.CONTENT_URI,
//                    null, Email
//                            .CONTACT_ID + " = " + contactId, null, null);
//            // 遍历查询结果，获取该联系人的多个E-mail地址
//            while (emails.moveToNext())
//            {
//                // 获取查询结果中E-mail地址列中数据
//                String emailAddress = emails.getString(emails
//                        .getColumnIndex(Email.DATA));
//                detail.add("邮件地址：" + emailAddress);
//            }
//            emails.close();
//            contactsList.add(name);
//            details.add(detail);
//        }
//    }
        Cursor cursor = null;
        try {
            //cursor指针 query询问 contract协议 kinds种类
            cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "   " + number);
                }
                //notify公布
//                adapter.notifyDataSetChanged();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
