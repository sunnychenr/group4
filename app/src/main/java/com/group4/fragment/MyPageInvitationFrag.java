package com.group4.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group4.yiqihouse.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ChenR on 2016/10/21.
 */

public class MyPageInvitationFrag extends Fragment {

    @InjectView(R.id.my_page_noData)
    ImageView myPageNoData;
    @InjectView(R.id.my_page_toast)
    TextView myPageToast;
    @InjectView(R.id.my_page_toast_content)
    TextView myPageToastContent;
    @InjectView(R.id.my_page_noContent)
    RelativeLayout myPageNoContent;
    @InjectView(R.id.my_page_content)
    ListView myPageContent;

    private boolean isHaveData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_page_fragment, container, false);
        ButterKnife.inject(this, view);
        setVisibility();
        return view;
    }

    private void setVisibility() {
        if (isHaveData) {
            myPageNoContent.setVisibility(View.GONE);
            myPageContent.setVisibility(View.VISIBLE);
        } else {
            myPageNoContent.setVisibility(View.VISIBLE);
            myPageContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
