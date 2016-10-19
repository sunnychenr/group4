package com.group4.yiqihouse;

import android.Manifest;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @InjectView(R.id.setting_back)
    ImageView settingBack;
    @InjectView(R.id.setting_hot_line)
    RelativeLayout settingHotLine;
    @InjectView(R.id.setting_feedback)
    RelativeLayout settingFeedback;
    @InjectView(R.id.mine_version)
    RelativeLayout mineVersion;
    @InjectView(R.id.setting_abort)
    RelativeLayout settingAbort;
    @InjectView(R.id.setting_login)
    Button settingLogin;
    @InjectView(R.id.activity_setting)
    LinearLayout activitySetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.setting_back, R.id.setting_hot_line, R.id.setting_feedback, R.id.mine_version, R.id.setting_abort, R.id.setting_login})
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.setting_hot_line:
                callPhone();
                break;
            case R.id.setting_feedback:
                break;
            case R.id.mine_version:
                intent.setAction("com.group4.yiqihouse.VersionInfosActivity");
                break;
            case R.id.setting_abort:
                intent.setAction("com.group4.yiqihouse.AboutActivity");
                break;
            case R.id.setting_login:
                intent.setAction("com.group4.yiqihouse.AccountActivity");
                break;
        }
        startActivity(intent);
    }

    private void callPhone() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("拨打电话");
        dialog.setMessage("4006861717");

        dialog.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4006891717"));
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(SettingActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(SettingActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                }
                startActivity(intent);
            }
        });

        dialog.setNegativeButton("取消", null);
        dialog.show();
    }

}
