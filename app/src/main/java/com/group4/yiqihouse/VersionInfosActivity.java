package com.group4.yiqihouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class VersionInfosActivity extends AppCompatActivity {

    @InjectView(R.id.version_back)
    ImageView versionBack;
    @InjectView(R.id.version_appIcon)
    ImageView versionAppIcon;
    @InjectView(R.id.version_appName)
    TextView versionAppName;
    @InjectView(R.id.version_num)
    TextView versionNum;
    @InjectView(R.id.version_toUpdate)
    TextView versionToUpdate;
    @InjectView(R.id.activity_version_infos)
    LinearLayout activityVersionInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_infos);
        ButterKnife.inject(this);

        versionToUpdate();
    }

    private void versionToUpdate() {

    }

    @OnClick(R.id.version_back)
    public void onClick() {
        finish();
    }
}
