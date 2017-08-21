package com.sampson.android.mydemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by chengyang on 2017/4/12.
 */

public class BasicService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private MyBinder binder = new MyBinder();

    class MyBinder extends Binder {

        public String doTest() {
            return "service运行成功！";
        }
    }

}
