package com.sampson.android.mydemos.hencoder.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sampson.android.mydemos.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by chengyang on 2017/8/21.
 */

public class CustomViewCatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_catalog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.paint1)
    public void goToPaint1() {
        startActivity(new Intent(CustomViewCatalogActivity.this, CustomView1Activity.class));
    }
}
