package com.example.administrator.customview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/28.
 *
 */
public class MyView2 extends TextView {
    String tag = "TAG";
    float x,y;
    boolean flag;

    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.i("MyTextView","OnTouch");
                x = event.getX();
                y = event.getY();
                flag = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("MyTextView","OnMove");
                float f = Math.abs(event.getY()-y)/Math.abs(event.getX()-x);
                Log.i(tag,f+"");
                if(f<0.4){
                    setBackgroundColor(Color.RED);
                    flag = false;
                }
                else {
                    setBackgroundColor(Color.LTGRAY);
                    flag = true;
                    //break;
                }
                break;

        }
        //Log.i("MyTextView",flag+"");
        //return flag;
        return true;
    }
}
