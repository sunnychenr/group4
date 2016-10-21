package com.group4.yiqihouse;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.group4.classinstance.HomeListViewInfo;
import com.group4.fragment.Home_fragment;
import com.group4.okhttputil.MyOkHttpUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.StringCallback;

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
    Gson gosn;
    String date[] ;
    List<Advert.DataBean> advertdata = new ArrayList<>();//广告数据
    List<HomeListViewInfo.DataBean> listdata = new ArrayList<>();//listview数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        Log.d("lyh", "进来");
        gosn  = new Gson();
        addfragment();
        getcachedate();
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



        super.onStart();
    }

    private void getcachedate() {
        getdata("advert","homeListViewInfo");
        if(date[0].length()<10&&date[1].length()<10){
            initdatel();
            initdateinfo();
        }
        else {
            if (date[0].length() < 10 && date[1].length() > 10) {
                Log.d("lyh", "广告请求");
                initdatel();

            } else {
                if (date[1].length() < 10 && date[0].length() > 10) {
                    initdateinfo();
                } else {
                    Log.d("lyh", "进缓存");
                    Advert adverts = gosn.fromJson(date[0], Advert.class);
                    advertdata= adverts.getData();
                    HomeListViewInfo homeListViewInfo = gosn.fromJson(date[1], HomeListViewInfo.class);
                    listdata = homeListViewInfo.getData();
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("adverts",adverts);
                   bundle.putSerializable("homeListViewInfo",homeListViewInfo);
                    mhome.setArguments(bundle);
                    Log.d("lyh", "数据丢到碎片中");
                }
            }
        }
    }

    //初始化数据
    private void initdatel() {
        advertmodel = new ArrayList<>();
        String url = "http://123.56.145.151:8080/YiQiHouse/HomeAD";

        MyOkHttpUtils.getmOkHttpUtils().gethttp(url, new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }
            @Override
            public void onResponse(String response) {
                Advert adverts = gosn.fromJson(response, Advert.class);
                advertdata= adverts.getData();
               mhome.setMviewpagerdate(advertdata);
                cachedatel("advert",response);
            }
        });
    }

    private void initdateinfo() {
        String url1 = "http://123.56.145.151:8080/YiQiHouse/HomeBBS?page=1";
        MyOkHttpUtils.getmOkHttpUtils().gethttp(url1, new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                HomeListViewInfo homeListViewInfo = gosn.fromJson(response, HomeListViewInfo.class);
                listdata = homeListViewInfo.getData();
                mhome.setlistviewdate(listdata);
                cachedatel("homeListViewInfo",response);
            }
        });
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
                       mhome.getMviewpager().setCurrentItem(arg1);
                    break;
            }
        }
    };

    //缓存写入数据
    public void cachedatel(String filename, String date) {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        int hh = (int) (mi.availMem / 1024 / 1024);
        if (hh > (hh - (hh / 7))) {
            Log.d("lyh", "缓存大小" + (hh - (hh / 7)));
            SharedPreferences advert = getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = advert.edit();
            edit.putString(filename, date);
            edit.commit();
        }
      }
    public String[] getdata(String key,String key1){
        date = new String[2];
        SharedPreferences advert = getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences advert1 = getSharedPreferences(key1, Context.MODE_PRIVATE);
       date[0] = advert.getString(key,"0");
        date[1] =advert1.getString(key1,"0");
       return date;
    }
}

