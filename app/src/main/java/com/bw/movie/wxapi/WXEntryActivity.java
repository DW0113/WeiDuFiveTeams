package com.bw.movie.wxapi;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.app.App;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import android.content.Intent;

import com.bw.movie.activity.MainActivity;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * by:majunbao
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    //微信appid
    private final   String  APP_ID_WX ="wxb3852e6a6b7d9516";

    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果没回调onResp，八成是这句没有写
        App.mWxApi.handleIntent(getIntent(), this);


        api = WXAPIFactory.createWXAPI(this,APP_ID_WX,true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID_WX);
        Log.d("q","------------------------------------");
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                Log.d("q","参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        Log.d("WXEntryActivity", "错误码 : " + resp.errCode + "");
        String result = "";
        switch(resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result ="发送成功";
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    Log.i("show", "onResp: " + code);
                    //成功之后调用登录的网络请求
                    doWX_login(code);
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                finish();
                break;
            default:
                result = "发送返回";
                finish();
                break;
        }
        //支付成功
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("支付结果");
            builder.setMessage(resp.errCode + "");
            builder.show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,  this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent,  this);
        finish();
    }



    //第三方微信的请求
    private void doWX_login(String code) {
        Log.d("Wx",code);

        Map map= new HashMap<>();
        Map m= new HashMap<>();
        m.put("Content-Type","application/x-www-form-urlencoded");
        map.put("code",code);
        new Utility().postupdate(m,"movieApi/user/v1/weChatBindingLogin",map).result(new HttpListener() {
            @Override
            public void success(String data) {
                Toast.makeText(WXEntryActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
            }
            @Override
            public void fail(String error) {

            }
        });

    }
}

