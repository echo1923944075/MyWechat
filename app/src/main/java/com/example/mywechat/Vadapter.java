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

public class Vadapter extends RecyclerView.Adapter<Vadapter.myviewHolder> {
    private Context context;
    private List<String> mList = new ArrayList<>();
//    private  View infalter;

    public Vadapter(Context context,ArrayList<String>list){
        this.context = context;
        this.mList = list;
    }

//    public void setapapterDataList(Context context,List<String> list){
//        mList = list;
//        Context = context;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public Vadapter.myviewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myviewHolder(itemview);
//        infalter = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
//        myviewHolder myviewholder = new myviewHolder(infalter);
//        return myviewholder;

    }

    @Override
    public void onBindViewHolder(Vadapter.myviewHolder holder, int posititon) {
        holder.tvContent.setText(mList.get(posititon));
//        holder.tvContent.setText();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class  myviewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);

        }
    }

}
