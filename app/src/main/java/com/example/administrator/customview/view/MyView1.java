package com.example.administrator.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.customview.MainActivity;

/**
 * Created by Administrator on 2016/6/29.
 *
 */
public class MyView1 extends LinearLayout {

    final String msg = this.getClass().getSimpleName();

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(MainActivity.TAG,msg+" dispatch " + ev.getAction()+" ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(MainActivity.TAG,msg+" onTouchEvent "+event.getAction());
        return false;
    }
}
