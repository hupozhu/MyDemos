package com.sampson.android.utillib.asynac;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by chengyang on 2017/4/13.
 */

public class SafeHandler<T extends SafeHandler.HandlerContainer> extends Handler {

    private WeakReference<T> mRef;

    public SafeHandler(T obj) {
        mRef = new WeakReference<T>(obj);
    }

    public T getContainer() {
        return mRef.get();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        HandlerContainer container = getContainer();

        if (container != null) {
            container.handleMessage(msg);
        }
    }

    public interface HandlerContainer {
        void handleMessage(Message msg);
    }

}
