package com.sjl.buglylearn;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

/**
 * App
 *
 * @author 沈建林
 * @date 2019/7/29
 */
public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        initBugly();
    }

    private void initBugly() {
        System.out.println("initBugly");
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {
                System.out.println("onPatchReceived:" + s);
            }

            @Override
            public void onDownloadReceived(long l, long l1) {
                System.out.println("onDownloadSuccess:" + l + ",l1");
            }

            @Override
            public void onDownloadSuccess(String s) {
                System.out.println("onDownloadSuccess:" + s);
            }

            @Override
            public void onDownloadFailure(String s) {
                System.out.println("onDownloadFailure:" + s);
            }

            @Override
            public void onApplySuccess(String s) {
                System.out.println("onApplySuccess:" + s);
            }

            @Override
            public void onApplyFailure(String s) {
                System.out.println("onApplyFailure:" + s);
            }

            @Override
            public void onPatchRollback() {

            }
        };
        Bugly.init(this, "3b912dd8d7", true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }
}
