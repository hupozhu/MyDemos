package com.sampson.android.utillib.asynac;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程管理单例类，通过线程池管理线程
 * <p>
 * Created by chengyang on 2017/4/13.
 */

public class ThreadManager {

    private ExecutorService threadPool;

    private static ThreadManager instance;

    public static ThreadManager getInstance() {
        if (instance == null) {
            synchronized (ThreadManager.class) {
                if (instance == null) {
                    instance = new ThreadManager();
                }
            }
        }
        return instance;
    }

    private ThreadManager() {
        this.threadPool = Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }


}
