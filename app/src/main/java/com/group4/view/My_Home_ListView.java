package com.group4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by bwfadmin on 2016/10/18.
 */

public class My_Home_ListView extends ListView {
    public My_Home_ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public My_Home_ListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
