package com.group4.yiqihouse;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class MainActivity extends AppCompatActivity {
    PtrClassicFrameLayout mptrClassicFrameLayout;
    ViewPager mviewpager;
    GridView mgridview;
    ListView mlistview;
    Button mscanbt;
    Button mpositionbt;
    Button mescupbt;
    Button mMenuhomebt;
    Button mMenuownerbt;
    Button mMenuinfobt;
    Button mMenumybt;
    TextView mtitletext,mMenuhometext,mMenuownertext,mMenuinfotext,mMenumytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
            
        
    }
    //初始化控件
    private void initview() {
        mptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.mptr);
        mviewpager = (ViewPager) findViewById(R.id.home_viewpager);
        mgridview = (GridView) findViewById(R.id.home_gridview);
        mlistview = (ListView) findViewById(R.id.home_liseview);
        mtitletext = (TextView) findViewById(R.id.home_title_search);
        mMenuhometext = (TextView) findViewById(R.id.home_menu_homebt);
        mMenuownertext = (TextView) findViewById(R.id.home_menu_ownerbt);
        mMenuinfotext = (TextView) findViewById(R.id.home_menu_infobt);
        mMenumytext = (TextView) findViewById(R.id.home_menu_mybt);
    }
    public void showtoast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

   public void homeonclick(View v){
       int id = v.getId();
       switch (id){
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
}
