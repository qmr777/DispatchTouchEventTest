package com.example.administrator.customview.DragRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.administrator.customview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
        implements RecyclerView.OnItemTouchListener {

    Context context;
    List<String> strings;
    float downX,downY,changeX,changeY;
    boolean flag = false;
    boolean needDragIn = false;
    boolean canClick = false;
    View view;//待移动的view
    long time;//按下时的毫秒数

    onItemClickListener listener;

    public MyAdapter(Context context, List<String> strings){
        this.context = context;
        this.strings = strings;
    }

    private float dp2px(float dp){
        return context.getResources().getDisplayMetrics().density * dp + 0.5f;
    }

    public interface onItemClickListener{
        void onClick(int position);

        boolean onLongClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    private int dp2px(int dp){
        return (int)(context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    @Override
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
                flag = true;
                canClick = true;
                if(needDragIn) {//需要滑入

                    view.scrollTo(0, 0);
                    needDragIn = false;
                    view = null;
                    flag = false;
                    canClick = false;
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                changeX = downX - e.getX();
                changeY = downY - e.getY();
                Log.d("TAG",changeX+"");
                flag = changeX>30&&Math.abs(changeY)<100;//x移动超过30像素,y移动不过100 才传给onTouchEvent
                canClick = Math.abs(changeY)<5;
                break;
            case MotionEvent.ACTION_UP:
                if(canClick&&listener!=null) {
                    canClick = false;
                    int position = rv.getChildAdapterPosition(rv.findChildViewUnder(downX,downY));
                    long t = System.currentTimeMillis();
                    if (t - time > 150 && t - time<500)
                        listener.onClick(position);
                    else if((t - time >= 500) && !listener.onLongClick(position))
                            listener.onClick(position);
                }

        }
        return flag;
    }

    //左右滑动在这里
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.i("TAG",e.getAction()+" onTouchEvent");
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
