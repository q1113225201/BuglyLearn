package com.sjl.buglylearn;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * App
 *
 * @author 沈建林
 * @date 2019/7/29
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initBugly();
    }

    private void initBugly() {
        Bugly.init(this, "3b912dd8d7", BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        // 安装tinker
        Beta.installTinker();
    }
}
