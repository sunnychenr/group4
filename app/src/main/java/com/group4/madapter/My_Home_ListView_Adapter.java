package com.group4.madapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.group4.classinstance.HomeListViewInfo;
import com.group4.yiqihouse.R;

import java.util.List;

/**
 * Created by bwfadmin on 2016/10/18.
 */

public class My_Home_ListView_Adapter extends BaseAdapter{
    List<HomeListViewInfo.DataBean> data;
    Context context;
    LayoutInflater mInflater;

    final int LAYOUT1=0;
    final int LAYOUT2=1;
    public My_Home_ListView_Adapter(Context context, List<HomeListViewInfo.DataBean> data) {
        this.data = data;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        name =  data.get(position).getAuthor();
        if(TextUtils.isEmpty(name)){
            return LAYOUT2;
        }else{
            return  LAYOUT1;
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    int type;
    String name;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
       // Log.d("lyh","typ值"+type);
        int type = getItemViewType(i);
        if(view==null){
              switch (type){
                  case LAYOUT1:
                    //  Log.d("lyh","进第一个布局");
                      holder1 = new ViewHolder1();
                      view=  mInflater.inflate(R.layout.home_list_layout1,null);
                      holder1.msim = (SimpleDraweeView) view.findViewById(R.id.head_src_list1);
                     holder1.mname = (TextView) view.findViewById(R.id.name_list1);
                     holder1.mtime = (TextView) view.findViewById(R.id.time_list1);
                     holder1.add_attention = (TextView) view.findViewById(R.id.add_attention);
                     holder1.mtitle = (TextView) view.findViewById(R.id.text_title_list1);
                     holder1.msrc = (SimpleDraweeView) view.findViewById(R.id.src_list1);
                     holder1.mforum = (TextView) view.findViewById(R.id.forum_list1);
                      holder1.mshare = (TextView) view.findViewById(R.id.share_list1);
                      holder1.minvitation_info = (TextView) view.findViewById(R.id.invitation_info_list1);
                      view.setTag(holder1);
                      break;
                  case LAYOUT2:
                     // Log.d("lyh","进第二个布局");
                      holder2 = new ViewHolder2();
                      view=  mInflater.inflate(R.layout.home_list_layout2,null);
                      holder2.mname = (TextView) view.findViewById(R.id.name_list2);
                      holder2.mtitle = (TextView) view.findViewById(R.id.text_title_list2);
                      holder2.msrc = (SimpleDraweeView) view.findViewById(R.id.src_list2);
                      holder2.mtime = (TextView) view.findViewById(R.id.time_list2);
                      holder2.mshare = (TextView) view.findViewById(R.id.invitation_info_list2);
                      holder2.minvitation_info = (TextView) view.findViewById(R.id.share_list2);
                      view.setTag(holder2);
                      break;
              }
          }else{
              switch (type){
                  case LAYOUT1:
                      holder1 = (ViewHolder1) view.getTag();
                      break;
                  case LAYOUT2:
                      holder2 = (ViewHolder2) view.getTag();
                      break;
              }
        }
        switch (type){
            case LAYOUT1:
               // Log.d("lyh","进第一个布局设置数据");
                holder1.msim.setImageURI(data.get(i).getAvtUrl());//头像
                holder1.msrc.setImageURI(data.get(i).getPath());//大图
                holder1.mname.setText(data.get(i).getAuthor());//发帖名
                holder1.mtitle.setText(data.get(i).getTitle());//发帖标题
                holder1.mtime.setText(data.get(i).getDateline());//发帖时间
                holder1.minvitation_info.setText(data.get(i).getViews());//详情
                holder1.mshare.setText(data.get(i).getReplies());//点击次数
                holder1.mforum.setText(data.get(i).getForum().getName());//论坛名字
                break;
            case LAYOUT2:
               // Log.d("lyh","进第二个布局设置数据");
                holder2.msrc.setImageURI(data.get(i).getPath());//图片
                holder2.mtitle.setText(data.get(i).getTitle());//标题
                holder2.mtime.setText(data.get(i).getDateline());//时间
                holder2.mshare.setText(data.get(i).getReplies());//点击次数
                holder2.minvitation_info.setText(data.get(i).getViews());
                break;
        }
        return view;
    }



    class  ViewHolder1{
        SimpleDraweeView msim;
        TextView mname;
        TextView mtime;
        TextView add_attention;
        TextView mtitle;
        TextView mforum;
        TextView mshare;
        TextView minvitation_info;
        SimpleDraweeView msrc;

    }
    class  ViewHolder2{
        TextView mname;
        TextView mtitle;
        TextView mtime;
        TextView mshare;
        TextView minvitation_info;
        SimpleDraweeView msrc;
    }
}

