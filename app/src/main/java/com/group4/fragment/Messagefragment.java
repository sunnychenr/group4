package com.group4.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.group4.yiqihouse.R;

/**
 * Created by ChenR on 2016/10/18.
 */

public class Messagefragment extends Fragment {

    private RelativeLayout msg_comment;
    private RelativeLayout msg_praise;
    private RelativeLayout msg_call_me;
    private RelativeLayout msg_system;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.msg_fragment, container, false);

        msg_comment = (RelativeLayout) view.findViewById(R.id.msg_comment);
        msg_praise = (RelativeLayout) view.findViewById(R.id.msg_praise);
        msg_call_me = (RelativeLayout) view.findViewById(R.id.msg_call_me);
        msg_system = (RelativeLayout) view.findViewById(R.id.msg_system);

        setListener();

        return view;
    }

    private void setListener() {
        msg_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        msg_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        msg_call_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        msg_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
