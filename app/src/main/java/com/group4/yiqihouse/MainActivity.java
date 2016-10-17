package com.group4.yiqihouse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.group4.classinstance.Advert;
import com.group4.fragment.Home_fragment;
import com.group4.okhttputil.MyOkHttpUtils;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public static final int SCROLL_STATE_DOWN = 0;
    public static final int SCROLL_STATE_IDLE = 1;
    List<Advert> advertmodel;
    Home_fragment mhome ;
    RelativeLayout mfragmentlayout;
    FragmentManager manger;
    FragmentTransaction mTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
       // mfragmentlayout = (RelativeLayout) findViewById(R.id.main_fragment);
        Log.d("lyh", "进来");
        addfragment();
        initdatel();
    }

    private void addfragment() {
        mhome = new Home_fragment(MyHander);
        manger = getSupportFragmentManager();
        mTransaction = manger.beginTransaction();
        mTransaction.add(R.id.main_fragment,mhome);
        mTransaction.commit();
        Log.d("lyh","添加碎片完毕");
    }

    @Override
    protected void onStart() {
        MyOkHttpUtils.getmOkHttpUtils().OnOkHttp(1,new MyOkHttpUtils.OnMyOkHttp() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("lyh", "失败" + request);
            }

            @Override
            public void onResponse(String response) {
                Log.d("lyh", "结果" + response);
                Gson gosn = new Gson();
                Advert adverts = gosn.fromJson(response, Advert.class);
                List<Advert.DataBean> data = adverts.getData();
                mhome.setMviewpagerdate(data);
            }
        });
        //广告轮播
        MyOkHttpUtils.getmOkHttpUtils().OnOkHttp(2, new MyOkHttpUtils.OnMyOkHttp() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("lyh", "失败" + request);
            }

            @Override
            public void onResponse(String response) {
                Log.d("lyh", "结果" + response);
            }
        });
        super.onStart();
    }

    //初始化数据
    private void initdatel() {
        advertmodel = new ArrayList<>();
        String url = "http://123.56.145.151:8080/YiQiHouse/HomeAD";
        String url1 = "http://123.56.145.151:8080/YiQiHouse/HomeBBS?page=1";
        MyOkHttpUtils.getmOkHttpUtils().gethttp(url);
          MyOkHttpUtils.getmOkHttpUtils().gethttp(url1);
    }

    //初始化控件




    public void showtoast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void homeonclick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.home_bt:
                showtoast("回顶部");
                break;
            case R.id.home_title_scan:
                showtoast("扫一扫");
                break;
            case R.id.home_title_scan_title_position:
                showtoast("定位");
                break;
            case R.id.home_menu_homebt:
                showtoast("首页");
                break;
            case R.id.home_menu_ownerbt:
                showtoast("业主说");
                break;
            case R.id.home_menu_infobt:
                showtoast("消息");
                break;
            case R.id.home_menu_mybt:
                showtoast("我的");
                break;

        }
    }
    Handler   MyHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCROLL_STATE_DOWN:
                    this.removeMessages(SCROLL_STATE_IDLE);
                    break;
                case SCROLL_STATE_IDLE:
                    int arg1 = msg.arg1;
                    Log.d("lyh","Handler离开"+arg1);
                       mhome.getMviewpager().setCurrentItem(arg1);
                    break;
            }
        }
    };

}

