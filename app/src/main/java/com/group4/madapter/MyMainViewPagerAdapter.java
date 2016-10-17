package com.group4.madapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by bwfadmin on 2016/10/14.
 */

public class MyMainViewPagerAdapter extends PagerAdapter {
    List<View> mview;
    Context context;
    public MyMainViewPagerAdapter(Context context,  List<View> mview ) {
        this.mview = mview;
        this.context = context;
    }
    @Override
    public int getCount() {
        return mview.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mview.get(position));
        Log.d("lyh","instantiateItem");
        return mview.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mview.get(position));
    }
}
