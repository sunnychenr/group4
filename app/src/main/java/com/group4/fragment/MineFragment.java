package com.group4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.group4.yiqihouse.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ChenR on 2016/10/18.
 */

public class MineFragment extends Fragment {

    @InjectView(R.id.iv_mine_avatar)
    ImageView ivMineAvatar;
    @InjectView(R.id.mine_inte_rule)
    LinearLayout mineInteRule;
    @InjectView(R.id.mine_invitation)
    LinearLayout mineInvitation;
    @InjectView(R.id.mine_order_new)
    RelativeLayout mineOrderNew;
    @InjectView(R.id.mine_collect_new)
    RelativeLayout mineCollectNew;
    @InjectView(R.id.mine_like_new)
    RelativeLayout mineLikeNew;
    @InjectView(R.id.mine_setting)
    RelativeLayout mineSetting;
    @InjectView(R.id.mine_feedback_new)
    RelativeLayout mineFeedbackNew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);

        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.mine_inte_rule, R.id.mine_invitation, R.id.mine_order_new, R.id.mine_collect_new, R.id.mine_like_new, R.id.mine_setting, R.id.mine_feedback_new})
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.mine_inte_rule:
                break;
            case R.id.mine_invitation:
                break;
            case R.id.mine_order_new:
                break;
            case R.id.mine_collect_new:
                break;
            case R.id.mine_like_new:
                break;
            case R.id.mine_setting:
                break;
            case R.id.mine_feedback_new:
                break;
        }
    }
}
