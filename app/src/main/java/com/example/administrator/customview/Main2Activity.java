package com.example.administrator.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.customview.PullToRefereshRecyclerView.PTRAInstance;
import com.example.administrator.customview.PullToRefereshRecyclerView.PTRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    PTRAInstance instance;
    PTRecyclerView recyclerView;
    List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    void initView(){
        recyclerView = (PTRecyclerView) findViewById(R.id.ptrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        strings = new ArrayList<>();
        for(int i = 0;i<10;i++)
            strings.add("第"+i+"项");

        instance = new PTRAInstance(this,strings);
        instance.addFooterView(R.layout.view_loadmore);
        recyclerView.setAdapter(instance);
    }
}
