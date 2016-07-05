package com.example.administrator.customview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.customview.PullToRefereshRecyclerView.PTRAInstance;
import com.example.administrator.customview.PullToRefereshRecyclerView.PTRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    PTRAInstance instance;
    PTRecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    List<String> strings;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    void initView(){
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        recyclerView = (PTRecyclerView) findViewById(R.id.ptrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        strings = new ArrayList<>();
        for(int i = 0;i<30;i++)
            strings.add("第"+i+"项");

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0;i<10;i++)
                            strings.add("第"+strings.size()+"项");
                        instance.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        instance = new PTRAInstance(this,strings);
        instance.addFooterView(R.layout.view_loadmore);
        recyclerView.setAdapter(instance);
        recyclerView.setLoadMore(new PTRecyclerView.loadMore() {
            @Override
            public void OnRefresh(RecyclerView recyclerView) {
                refreshLayout.setRefreshing(true);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0;i<20;i++)
                            strings.add("第"+strings.size()+"项");
                        instance.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
