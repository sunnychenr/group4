package com.group4.okhttputil;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

/**
 * Created by bwfadmin on 2016/10/14.
 */

public class MyOkHttpUtils extends StringCallback {

    //单列模式
    private void MyOkHttpUtils(){

    }
    Map<Integer,OnMyOkHttp>map;
     private static MyOkHttpUtils mOkHttpUtils;
    public static MyOkHttpUtils getmOkHttpUtils(){
            if(mOkHttpUtils==null){
                mOkHttpUtils = new MyOkHttpUtils();
            }

        return  mOkHttpUtils;
        }
    public void gethttp(String url){
        Log.d("lyh","进入请求");
        GetBuilder  builder = OkHttpUtils.get();
        builder.url(url);
            builder.build().execute(this);
    }
    public void posthttp(String url, Map<String,String> map){

        PostFormBuilder post = OkHttpUtils.post();
        post.url(url);
        for (Map.Entry s:map.entrySet()){
            post.addParams(s.getKey().toString(),s.getValue().toString());
        }
      post.build().execute(this);
    }
    @Override
    public void onError(Request request, Exception e) {
        mOnMyOkHttp.onError(request,e);
    }

    @Override
    public void onResponse(String response) {
        mOnMyOkHttp.onResponse(response);
    }
    //回调接口
    public interface OnMyOkHttp{
        void onError(Request request, Exception e);
        void onResponse(String response);
    }
    OnMyOkHttp mOnMyOkHttp;
    //接口
    int oknumber;
    public void OnOkHttp(OnMyOkHttp mOnMyOkHttp){
        this.mOnMyOkHttp = mOnMyOkHttp;

    }
}


