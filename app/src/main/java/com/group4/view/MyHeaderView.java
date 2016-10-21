package com.group4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.yiqihouse.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by bwfadmin on 2016/10/11.
 */
public class MyHeaderView extends FrameLayout implements PtrUIHandler{
  ImageView msrc;
    TextView mtitle,mtme;
    String time;
    public MyHeaderView(Context context,String time) {
        super(context);
        this.time = time;
       initview(context);
    }

    private void initview(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        View vv = from.inflate(R.layout.myheaderayout,null);
       msrc = (ImageView) vv.findViewById(R.id.my_scrollviewhead_src);
       mtitle = (TextView) vv.findViewById(R.id.my_serollviewhead_title);
        mtme = (TextView) vv.findViewById(R.id.my_serollviewhead_time);
        mtme.setText("上次更新时间"+time);
        addView(vv);
    }


    public MyHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public MyHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        int headerHeight = ptrIndicator.getHeaderHeight();
        int currentPosY = ptrIndicator.getCurrentPosY();
        String s = mtitle.getText().toString();
        if(s.equals("松开刷新")){
            mtitle.setText("一起装修网省钱有保障");
        }
        if(currentPosY>=headerHeight){
            mtitle.setText("松开刷新");
        }
        invalidate();
    }
    public String settime(){
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String time = format.format(data.getTime());
        mtme.setText("上次更新"+time);
        Log.d("lyh","改变时间"+time);
        return time;
    }
}
