package com.example.mywechat;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class weixinFragment extends Fragment {
    private View view;//定义用来设置fragment的layout
    public RecyclerView recyclerView;
    private ArrayList<String> mList = new ArrayList<>();
    //定义适配器
    private Vadapter vadapter;
    //配置reclerview
    private void initRecyclerView(){
        //获取recyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.re_view);
        //创建adpter
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        vadapter = new Vadapter(getActivity(),mList);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vadapter);
        recyclerView.setLayoutManager(manager);
//        vadapter.setapapterDataList(mList);
    }


    public weixinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab01,container,false);
        initData();
        initRecyclerView();
        return view;
    }

    //数据
    private void initData(){
        mList.add("C罗");
        mList.add("梅西");
        mList.add("克洛泽");
        mList.add("拉姆");
        mList.add("莱万多夫斯基");
        mList.add("姆巴佩");
        mList.add("贝克汉姆");
    }

}
