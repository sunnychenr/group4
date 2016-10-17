package com.group4.yiqihouse;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.group4.classinstance.Advert;
import com.group4.madapter.GridViewAdapter;
import com.group4.madapter.MyMainViewPagerAdapter;
import com.group4.okhttputil.MyOkHttpUtils;
import com.group4.okhttputil.MyUtils;
import com.group4.view.MyHeaderView;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {
    public static final int SCROLL_STATE_DOWN = 0;
    public static final int SCROLL_STATE_IDLE = 1;
    PtrClassicFrameLayout mptrClassicFrameLayout;
    ViewPager mviewpager;
    GridView mgridview;
    ListView mlistview;
    TextView mtitletext, mMenuhometext, mMenuownertext, mMenuinfotext, mMenumytext;
    MyMainViewPagerAdapter mviewpagersp;
    List<Advert> advertmodel;
    List<View> mview;
    Context context;
    GridViewAdapter mgirdsp;
    SimpleDraweeView mSimp1, mSimp2, mSimp3, mSimp4, mSimp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        Log.d("lyh", "进来");
        context = this;
        initview();
        initdatel();
    }

    @Override
    protected void onStart() {
        MyOkHttpUtils.getmOkHttpUtils().OnOkHttp(new MyOkHttpUtils.OnMyOkHttp() {
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
                addadvertview(data);
                mviewpagersp = new MyMainViewPagerAdapter(context, mview);
                mviewpager.setAdapter(mviewpagersp);
                MyUtils util = new MyUtils();
                Message msg = Message.obtain();
               /* msg.what = SCROLL_STATE_IDLE;
                msg.arg1 = 1;
                MyHander.sendMessageDelayed(msg, 2000);*/
                util.setvierpageradvert(mviewpager, MyHander);
            }
        });
        //广告轮播

        super.onStart();
    }

    //初始化数据
    private void initdatel() {
        advertmodel = new ArrayList<>();
        String url = "http://123.56.145.151:8080/YiQiHouse/HomeAD";
        String url1 = "http://123.56.145.151:8080/YiQiHouse/HomeBBS?page=1";
        MyOkHttpUtils.getmOkHttpUtils().gethttp(url);
        //  MyOkHttpUtils.getmOkHttpUtils().gethttp(url1);
    }

    //初始化控件
    private void initview() {
        mview = new ArrayList<>();
        mptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.mptr);
        mviewpager = (ViewPager) findViewById(R.id.home_viewpager);
        mgridview = (GridView) findViewById(R.id.home_gridview);
        mlistview = (ListView) findViewById(R.id.home_liseview);
        mtitletext = (TextView) findViewById(R.id.home_title_search);
        mMenuhometext = (TextView) findViewById(R.id.home_menu_homebt);
        mMenuownertext = (TextView) findViewById(R.id.home_menu_ownerbt);
        mMenuinfotext = (TextView) findViewById(R.id.home_menu_infobt);
        mMenumytext = (TextView) findViewById(R.id.home_menu_mybt);
        mgirdsp = new GridViewAdapter(context);
        mgridview.setAdapter(mgirdsp);
        final MyHeaderView mheader = new MyHeaderView(context);
        mptrClassicFrameLayout.disableWhenHorizontalMove(true);
        mptrClassicFrameLayout.setHeaderView(mheader);
        mptrClassicFrameLayout.addPtrUIHandler(mheader);
        mptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mheader.settime();
                frame.refreshComplete();
            }
        });


    }

    private void addadvertview(List<Advert.DataBean> data) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.advertviewpagerlayout, null);
        mSimp1 = (SimpleDraweeView) view1.findViewById(R.id.advert_src);
        mSimp1.setImageURI(data.get(0).getImagesrc2());
        View view2 = inflater.inflate(R.layout.advertviewpagerlayout, null);
        mSimp2 = (SimpleDraweeView) view2.findViewById(R.id.advert_src);
        mSimp2.setImageURI(data.get(1).getImagesrc2());
        View view3 = inflater.inflate(R.layout.advertviewpagerlayout, null);
        mSimp3 = (SimpleDraweeView) view3.findViewById(R.id.advert_src);
        mSimp3.setImageURI(data.get(2).getImagesrc2());
        View view4 = inflater.inflate(R.layout.advertviewpagerlayout, null);
        mSimp4 = (SimpleDraweeView) view4.findViewById(R.id.advert_src);
        mSimp4.setImageURI(data.get(3).getImagesrc2());
        View view5 = inflater.inflate(R.layout.advertviewpagerlayout, null);
        mSimp5 = (SimpleDraweeView) view5.findViewById(R.id.advert_src);
        mSimp5.setImageURI(data.get(4).getImagesrc2());
        mview.add(view1);
        mview.add(view2);
        mview.add(view3);
        mview.add(view4);
        mview.add(view5);
    }

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
                        mviewpager.setCurrentItem(arg1);
                    break;
            }
        }
    };

}

