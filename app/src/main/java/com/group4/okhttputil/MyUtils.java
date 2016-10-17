package com.group4.okhttputil;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.group4.yiqihouse.MainActivity;

/**
 * Created by bwfadmin on 2016/10/17.
 */

public class MyUtils {
    int number;
     //viewpager轮播广告
    public void setvierpageradvert(ViewPager vp, final Handler handler){
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
                Message msg = handler.obtainMessage();
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                       handler.sendEmptyMessage(MainActivity.SCROLL_STATE_DOWN);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        msg.what = MainActivity.SCROLL_STATE_IDLE;
                       if(++number>=5){
                           msg.arg1 = 0;
                       }else{
                           msg.arg1 =number;
                       }
                        handler.sendMessageDelayed(msg,4000);
                        break;
                }
            }

        });
    }
}
