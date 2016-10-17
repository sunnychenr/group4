package com.group4.okhttputil;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.group4.yiqihouse.MainActivity;

/**
 * Created by bwfadmin on 2016/10/17.
 */

public class MyUtils {
    int number;
     //viewpager轮播广告
    public void setvierpageradvert(ViewPager vp, final Handler handler){
        final Message msg = Message.obtain();
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                number = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log.d("lyh","ViewPager触摸");
                       handler.sendEmptyMessage( MainActivity.SCROLL_STATE_DOWN);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.d("lyh","ViewPager离开");
                        msg.what = MainActivity.SCROLL_STATE_IDLE;
                        int a = number+1;
                       if(a>=5){
                           msg.arg1 = 0;
                       }else{
                           msg.arg1 =a;
                       }
                        Log.d("lyh","a值"+a);
                        handler.sendMessageDelayed(msg,4000);
                        break;
                }
            }
        });
    }
}
