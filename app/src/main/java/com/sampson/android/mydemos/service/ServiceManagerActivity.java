package com.sampson.android.mydemos.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sampson.android.mydemos.R;

/**
 * Created by chengyang on 2017/4/12.
 */

public class ServiceManagerActivity extends AppCompatActivity {

    Button startService, stopService, bindService, unBindService, bindRemoteService, unBindRemoteService;
    TextView tvLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_manager_layout);

        tvLog = (TextView) findViewById(R.id.tv_log);
        startService = (Button) findViewById(R.id.open_service);
        stopService = (Button) findViewById(R.id.close_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unBindService = (Button) findViewById(R.id.unbind_service);
        bindRemoteService = (Button) findViewById(R.id.bind_remote_service);
        unBindRemoteService = (Button) findViewById(R.id.unbind_remote_service);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(ServiceManagerActivity.this, BasicService.class));
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(ServiceManagerActivity.this, BasicService.class));
            }
        });

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceManagerActivity.this, BasicService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        unBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });

        //如果绑定别的程序的service，那么当前程序并没有这个service，此时只能通过隐试启动service。注意这种调用方式需要在另外的客户端中调用。
        bindRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.sampson.android.mydemos.service.RemoteAIDLService");
                bindService(intent, remoteAIDLConnection, BIND_AUTO_CREATE);
            }
        });

        unBindRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(remoteAIDLConnection);
            }
        });
    }

    private BasicService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("mydemos", "basicService onServiceConnected");
            myBinder = (BasicService.MyBinder) iBinder;
            tvLog.setText(myBinder.doTest());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private RemoteAIDLService remoteAIDLService;
    private ServiceConnection remoteAIDLConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            remoteAIDLService = RemoteAIDLService.Stub.asInterface(iBinder);
            //接下来就可以调用远程service的方法了
            try {
                tvLog.setText("远程service ： " + remoteAIDLService.test2(2, 5));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}
