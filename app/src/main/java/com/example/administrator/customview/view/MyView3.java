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
public class MyView3 extends LinearLayout {

    final String msg = this.getClass().getSimpleName();


    public MyView3(Context context) {
        super(context);
    }

    public MyView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(MainActivity.TAG,msg+" dispatch " + ev.getAction());
        //return false;
        return super.dispatchTouchEvent(ev);
        //return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(MainActivity.TAG,msg+" onTouchEvent "+event.getActionMasked());
        return false;
    }

}
