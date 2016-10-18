package com.group4.okhttputil;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

/**
 * Created by bwfadmin on 2016/10/14.
 */

public class MyOkHttpUtils {

    //单列模式
    private void MyOkHttpUtils(){

    }
     private static MyOkHttpUtils mOkHttpUtils;
    public static MyOkHttpUtils getmOkHttpUtils(){
            if(mOkHttpUtils==null){
                mOkHttpUtils = new MyOkHttpUtils();
            }

        return  mOkHttpUtils;
        }
    public void gethttp(String url,StringCallback callback){
        Log.d("lyh","进入请求");
        GetBuilder  builder = OkHttpUtils.get();
        builder.url(url);
            builder.build().execute( callback);

    }
    public void posthttp(String url, Map<String,String> map,StringCallback callback){

        PostFormBuilder post = OkHttpUtils.post();
        post.url(url);
        for (Map.Entry s:map.entrySet()){
            post.addParams(s.getKey().toString(),s.getValue().toString());
        }
      post.build().execute(callback);
    }

}


