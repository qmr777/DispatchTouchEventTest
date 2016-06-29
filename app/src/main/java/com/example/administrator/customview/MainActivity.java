package com.example.administrator.customview;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.customview.view.MyView2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<String> list;
    boolean flag = true;
    float x = 0,y = 0;

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(MainActivity.TAG,"MainAty接收event "+event.getAction());
        return true;
    }

    void initView(){
        list = new ArrayList<>();
        for(int i = 0;i<20;i++){
            list.add("String"+i);
        }
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RvAdapter(list));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Rv","OnTouch");
                        x = event.getX();
                        y = event.getY();
                        flag = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("Rv","OnMove");
                        float f = Math.abs(event.getY()-y)/Math.abs(event.getX()-x);
                        flag = f<0.4;
                        break;

                }
                Log.i("Rv",flag+" flag");
                return flag;
            }
        });
    }




    @Override
    public void onClick(View v) {

    }

    class RvAdapter extends RecyclerView.Adapter<RvAdapter.Myholder> {
        List<String> list;

        RvAdapter(List<String> s){
            this.list = s;

        }

        @Override
        public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Myholder(LayoutInflater.from(MainActivity.this).inflate(R.layout.cell,null));
        }

        @Override
        public void onBindViewHolder(Myholder holder, int position) {
            holder.myView2.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Myholder extends RecyclerView.ViewHolder{
            MyView2 myView2;

            public Myholder(View itemView) {
                super(itemView);
                myView2 = (MyView2) itemView.findViewById(R.id.mv2);

            }
        }
    }

}
