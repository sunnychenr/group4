package com.group4.madapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.yiqihouse.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bwfadmin on 2016/10/16.
 */

 public class GridViewAdapter extends BaseAdapter {
    String name[] = {"装修公司","同城活动","装修学堂","装修预算","建材家居","效果图","自助下单","设计/量房"};
    List<Map<String ,Integer>> map;
    LayoutInflater minflater;
    public GridViewAdapter(Context context) {
        map = new ArrayList<>();
        minflater = LayoutInflater.from(context);
      for (int i =1;i<9;i++){
          Map<String ,Integer> hmap = new HashMap<>();
          int drawable = context.getResources().getIdentifier("q" + i, "drawable", context.getPackageName());
          hmap.put(name[i-1],drawable);
          map.add(hmap);
      }
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int i) {
        return map.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodel mview = null;
         mview = new ViewHodel();
         View mView = minflater.inflate(R.layout.gridviewadapter,null);
        mview.mimag = (ImageView) mView.findViewById(R.id.gridview_src);
        mview.mtext = (TextView) mView.findViewById(R.id.gridview_text);
        mview.mimag.setImageResource(map.get(i).get(name[i]));
        mview.mtext.setText(name[i]);
        return mView;
    }
}
class  ViewHodel{
    ImageView mimag;
    TextView mtext;
}
