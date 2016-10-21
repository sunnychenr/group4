package com.group4.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.group4.classinstance.Advert;
import com.group4.classinstance.HomeListViewInfo;
import com.group4.madapter.GridViewAdapter;
import com.group4.madapter.MyMainViewPagerAdapter;
import com.group4.madapter.My_Home_ListView_Adapter;
import com.group4.okhttputil.MyUtils;
import com.group4.view.MyHeaderView;
import com.group4.yiqihouse.MainActivity;
import com.group4.yiqihouse.R;

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
    My_Home_ListView_Adapter mlistsp;
     MyHeaderView mheader;
    String time;
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
        SharedPreferences advert = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        time =  advert.getString("time","0");
        initview(vv);
      Bundle bundle =  Home_fragment.this.getArguments();
        getbundle(bundle);

        return vv;
    }
    //重Bundle中取数据
    private void getbundle(Bundle bundle) {
        if(bundle!=null){
         Object mdate = bundle.getSerializable("adverts");
            Object mdate1= bundle.getSerializable("homeListViewInfo");
            if(mdate instanceof  Advert){
                Advert advert = (Advert) mdate;
                setMviewpagerdate(advert.getData());
            }
            if(mdate1 instanceof HomeListViewInfo){
                HomeListViewInfo homeListViewInfo = (HomeListViewInfo) mdate1;
                setlistviewdate(homeListViewInfo.getData());
            }
        }
    }

    public void setMviewpagerdate(List<Advert.DataBean> data){
      addadvertview(data);
      mviewpagersp = new MyMainViewPagerAdapter(getActivity(), mview);
      mviewpager.setAdapter(mviewpagersp);
      MyUtils util = new MyUtils();
      Message msg = Message.obtain();
      msg.what = MainActivity.SCROLL_STATE_IDLE;
      msg.arg1 = 1;
      MyHander.sendMessageDelayed(msg, 4000);
      util.setvierpageradvert(mviewpager, MyHander);
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
        mheader = new MyHeaderView(getContext(),time);
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
    public void addadvertview(List<Advert.DataBean> data) {
       LayoutInflater inflater = LayoutInflater.from(getActivity());
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
   public void setlistviewdate(List<HomeListViewInfo.DataBean> data){
       if(mlistsp==null){
           mlistsp = new My_Home_ListView_Adapter(getContext(),data);
           mlistview.setAdapter(mlistsp);
       }else{
           mlistsp.notifyDataSetChanged();
       }
   }

    @Override
    public void onDestroy() {
        time =  mheader.settime();
        SharedPreferences advert = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = advert.edit();
        edit.putString("time", time);
        edit.commit();
        super.onDestroy();
    }
}
