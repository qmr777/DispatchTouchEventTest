package com.example.administrator.customview.PullToRefereshRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by qmr on 2016/7/5.
 */
public class asd implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
