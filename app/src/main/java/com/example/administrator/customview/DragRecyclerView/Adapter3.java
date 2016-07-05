package com.example.administrator.customview.DragRecyclerView;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class Adapter3 extends RecyclerView.Adapter<Adapter3.MyViewHolder>
        implements RecyclerView.OnItemTouchListener{

    Context context;
    List<String> strings;
    float downX,downY,changeX,changeY;
    boolean flag = false;
    boolean needDragIn = false;
    boolean canClick = false;
    View view;//待移动的view
    long time;//按下时的毫秒数


    public Adapter3(Context context, List<String> strings){
        this.context = context;
        this.strings = strings;
    }

    private float dp2px(float dp){
        return context.getResources().getDisplayMetrics().density * dp + 0.5f;
    }

    private int dp2px(int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cell2,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {
        holder.textView.setText(strings.get(position));
        holder.itemView.scrollTo(0,0);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    //滑动事件拦截 点击事件 上下滑动允许 在这里
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.i("TAG",e.getAction() + " onInterceptTouchEvent");
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                time = System.currentTimeMillis();
                downX = e.getX();
                downY = e.getY();
                canClick = true;
                /*if(needDragIn) {//需要滑入
                    view.scrollTo(0, 0);
                    needDragIn = false;
                    view = null;
                    canClick = false;
                }*/
                flag = false;

                break;
            case MotionEvent.ACTION_MOVE:
                changeX = downX - e.getX();
                changeY = downY - e.getY();
                Log.d("TAG",changeX+"");
                flag = changeX>30&&Math.abs(changeY)<100;//x移动超过30像素,y移动不过100 才传给onTouchEvent
                canClick = Math.abs(changeY)<5;
                break;
            case MotionEvent.ACTION_UP:
        }
        Log.e("TAG","onInterceptTouchEvent "+flag);
        return flag;
    }

    //左右滑动在这里
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.i("TAG",e.getAction()+" onTouchEvent ");
        canClick = false;
        switch (e.getAction()){
            case MotionEvent.ACTION_UP:
                if(view == null) {
                    break;
                }
                if(view.getScrollX()<dp2px(100)) {
                    view.scrollTo(0, 0);
                    needDragIn = false;
                    view = null;
                } else {
                    view.scrollTo(dp2px(200),0);
                    needDragIn = true;
                }
                Log.i("TAG","view为空 "+(view == null)+" " + rv.getChildAdapterPosition(view));
                view = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if(view == null)
                    view = rv.findChildViewUnder(e.getX(),e.getY());//获取准备拖拽的View

                if(downX - e.getX()<=dp2px(200)&&downX - e.getX()>0)//在一定范围之内滑动时
                    view.scrollTo((int) (downX - e.getX()),0);
                break;
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView iv1,iv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            iv2 = (ImageView) itemView.findViewById(R.id.iv2);

            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG","121212 "+getAdapterPosition());
                }
            });

            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int p = getAdapterPosition();
                    strings.remove(p);
                    Adapter3.this.notifyItemRemoved(p);
                    return true;
                }
            });

            iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    strings.remove(p);
                    Adapter3.this.notifyItemRemoved(p);
                }
            });

            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,getAdapterPosition()+"",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
