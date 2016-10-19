package com.group4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by bwfadmin on 2016/10/16.
 */

public class MyGridview extends GridView {
    public MyGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridview(Context context) {
        super(context);
    }

    public MyGridview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            Log.d("lyh","MotionEvent.ACTION_MOVE");
            return true;  //禁止GridView滑动
        }
        return super.dispatchTouchEvent(ev);
    }
}
