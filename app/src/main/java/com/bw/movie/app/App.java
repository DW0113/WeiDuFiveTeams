package com.bw.movie.app;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/*
 *作者：刘进
 *日期：2018/11/29
 **/public class App extends MultiDexApplication {
    private static App mainApp;

    public static IWXAPI mWxApi;

    public static App getMainApp() {
        mainApp = new App();
        return mainApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWxApi = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516", false);
        // 将该app注册到微信
        mWxApi.registerApp("wxb3852e6a6b7d9516");
        Fresco.initialize(this);
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }
}
