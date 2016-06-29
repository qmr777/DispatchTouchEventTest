package com.example.administrator.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.example.administrator.customview.R;

/**
 * Created by Administrator on 2016/6/27.
 *
 */
public class MyViewPager extends ViewPager {

    private Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.a1223);
    int count,bgW,bgH;
    float widthForItem,widthForPerPx;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(bg!=null){
            bgW = bg.getWidth();
            bgH = bg.getHeight();
            count = getAdapter().getCount();
            widthForItem = bgW*1.0f/count;
            widthForPerPx = widthForItem/getWidth();
            //int x = getScrollX();
            Rect rectF1 = new Rect(getScrollX()*(int)widthForPerPx,0
                    ,(int)(getScrollX()*widthForPerPx+widthForItem),bgH);

            Rect rectF2 = new Rect(getScrollX(),0,getScrollX()+getWidth(),getHeight());
            canvas.drawBitmap(bg,rectF1,rectF2,new Paint());
        }

        super.dispatchDraw(canvas);
    }
}
