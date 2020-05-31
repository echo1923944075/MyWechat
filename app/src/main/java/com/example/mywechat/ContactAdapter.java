package com.example.mywechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myviewHolder> {
//    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<String> mList; // 联系人名称字符串数组

    public ContactAdapter(Context context, ArrayList<String> list){
        mContext = context;
        mList = list;
    }

    public class  myviewHolder extends RecyclerView.ViewHolder{
        TextView iv_Contact;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            iv_Contact = itemView.findViewById(R.id.tv_NameAndPhone);

        }
    }

    @NonNull
    @Override
    public ContactAdapter.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(mContext).inflate(R.layout.contend_item,parent,false);
        return new myviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.myviewHolder holder,int posititon) {
        holder.iv_Contact.setText(mList.get(posititon));
    }

    @Override
    public int getItemCount()  {
        return mList == null ? 0 : mList.size();
    }

}
