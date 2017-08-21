package com.sampson.android.mydemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by chengyang on 2017/4/12.
 */

public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    RemoteAIDLService.Stub stub = new RemoteAIDLService.Stub() {
        @Override
        public void test1() throws RemoteException {

        }

        @Override
        public String test2(int a, int b) throws RemoteException {
            return String.valueOf(a + b);
        }
    };

}
