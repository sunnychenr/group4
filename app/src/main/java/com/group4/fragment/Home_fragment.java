package com.group4.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.group4.classinstance.Advert;
import com.group4.madapter.GridViewAdapter;
import com.group4.madapter.MyMainViewPagerAdapter;
import com.group4.okhttputil.MyOkHttpUtils;
import com.group4.okhttputil.MyUtils;
import com.group4.view.MyHeaderView;
import com.group4.yiqihouse.MainActivity;
import com.group4.yiqihouse.R;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by bwfadmin on 2016/10/17.
 */

public class Home_fragment extends Fragment {
    PtrClassicFrameLayout mptrClassicFrameLayout;
    ViewPager mviewpager;
    GridView mgridview;
    ListView mlistview;
    TextView mtitletext, mMenuhometext, mMenuownertext, mMenuinfotext, mMenumytext;
    MyMainViewPagerAdapter mviewpagersp;
    List<View> mview;
    GridViewAdapter mgirdsp;
    SimpleDraweeView mSimp1, mSimp2, mSimp3, mSimp4, mSimp5;
    Handler MyHander;
    public Home_fragment() {
    }
    @SuppressLint("ValidFragment")
    public Home_fragment(Handler MyHander) {
        this.MyHander =MyHander;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View vv = inflater.inflate(R.layout.home_fragment,null);
        initview(vv);
        return vv;
    }
  public void setMviewpagerdate(List<Advert.DataBean> data){
      addadvertview(data);
      mviewpagersp = new MyMainViewPagerAdapter(getContext(), mview);
      mviewpager.setAdapter(mviewpagersp);
      MyUtils util = new MyUtils();
      Message msg = Message.obtain();
      msg.what = MainActivity.SCROLL_STATE_IDLE;
      msg.arg1 = 1;
      MyHander.sendMessageDelayed(msg, 4000);
      util.setvierpageradvert(mviewpager, MyHander);
  }
    @Override
    public void onStart() {
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
                addadvertview(data);
                mviewpagersp = new MyMainViewPagerAdapter(getContext(), mview);
                mviewpager.setAdapter(mviewpagersp);
                MyUtils util = new MyUtils();
                Message msg = Message.obtain();
                msg.what = MainActivity.SCROLL_STATE_IDLE;
                msg.arg1 = 1;
                MyHander.sendMessageDelayed(msg, 4000);
                util.setvierpageradvert(mviewpager, MyHander);
            }
        });
        super.onStart();
    }

    private void initview(View vv) {
        mview = new ArrayList<>();
        mptrClassicFrameLayout = (PtrClassicFrameLayout) vv.findViewById(R.id.mptr);
        mviewpager = (ViewPager) vv.findViewById(R.id.home_viewpager);
        mgridview = (GridView) vv.findViewById(R.id.home_gridview);
        mlistview = (ListView) vv.findViewById(R.id.home_liseview);
        mtitletext = (TextView) vv.findViewById(R.id.home_title_search);
        mMenuhometext = (TextView) vv.findViewById(R.id.home_menu_homebt);
        mMenuownertext = (TextView) vv.findViewById(R.id.home_menu_ownerbt);
        mMenuinfotext = (TextView) vv.findViewById(R.id.home_menu_infobt);
        mMenumytext = (TextView) vv.findViewById(R.id.home_menu_mybt);
        mgirdsp = new GridViewAdapter(getContext());
        mgridview.setAdapter(mgirdsp);
        final MyHeaderView mheader = new MyHeaderView(getContext());
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
    //初始化广告view
    private void addadvertview(List<Advert.DataBean> data) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
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
   public ViewPager getMviewpager(){
       return this.mviewpager;
   }

}
