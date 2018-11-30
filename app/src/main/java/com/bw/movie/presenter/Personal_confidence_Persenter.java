package com.bw.movie.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.Personal_confidence_Activity;
import com.bw.movie.model.Upload_picture;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/*
 * 作者：秦永聪
 *日期：2018/11/29
 * */public class Personal_confidence_Persenter extends AppDelegate implements View.OnClickListener {
    private RelativeLayout tv_login_exit;
    private SharedPreferences login;
    private ImageView im_persenter_username;
    private ImageView im_persenter_sex;
    private ImageView im_persenter_emila;
    private ImageView im_persenter_phone;
    private ImageView im_persenter_birthday;
    private TextView tv_login_the_complete;
    private TextView tv_login_the_editor;
    private ImageView im_persenter_heand1;
    private TextView tv_persenter_username;
    private TextView tv_persenter_sex;
    private TextView tv_persenter_emila;
    private TextView tv_persenter_phone;
    private TextView tv_persenter_birthday;
    private String nickName;
    private String phone;
    private String sex;
    private String birthday;
    private String headpic;
    private ImageView im_persenter_heand;
    private static String path = "/sdcard/myHead/";// sd路径
    private Bitmap head;
    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_confidence;
    }
    Context context;
    @Override
    public void getContext(Context context) {
    this.context=context;
    }

    @Override
    public void initData() {
        //存值
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
      //找控件
        tv_login_exit = get(R.id.tv_login_exit);
        tv_login_the_editor = get(R.id.tv_login_The_editor);
        tv_login_the_complete = get(R.id.tv_login_The_complete);
        im_persenter_heand1 = get(R.id.im_persenter_heand1);
        im_persenter_heand = get(R.id.im_persenter_heand);
        im_persenter_username = get(R.id.im_persenter_username);
        tv_persenter_username = get(R.id.tv_personal_username);
        im_persenter_sex = get(R.id.im_persenter_sex);
        tv_persenter_sex = get(R.id.tv_personal_sex);

        im_persenter_phone = get(R.id.im_persenter_phone);
        tv_persenter_phone = get(R.id.tv_personal_phone);
        im_persenter_birthday = get(R.id.im_persenter_birthday);
        tv_persenter_birthday = get(R.id.tv_personal_birthday);
        //点击事件
        tv_login_exit.setOnClickListener(this);
        tv_login_the_complete.setOnClickListener(this);
        tv_login_the_editor.setOnClickListener(this);
        //获取值：
        nickName = login.getString("nickName", "");
        phone = login.getString("phone", "");
        sex = login.getString("sex", "");
        birthday = login.getString("birthday", "");
        headpic = login.getString("headpic", "");
        im_persenter_heand.setOnClickListener(this);
        Assignment();
    }

    private void Assignment() {
        if(sex.contains("1")){
            tv_persenter_sex.setText("男");
        }
        else if(sex.contains("2")){
            tv_persenter_sex.setText("女");
        }
        tv_persenter_birthday.setText(birthday+"");

        tv_persenter_phone.setText(phone+"");
        tv_persenter_username.setText(nickName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login_exit:
                login.edit().putString("phone","")
                        .putString("pwd","")
                        .putString("sex","")
                        .putString("email","")
                        .putString("birthday","")
                        .putString("nickName","").commit();
                Toast.makeText(context, "注销成功", Toast.LENGTH_SHORT).show();
                ((Personal_confidence_Activity)context).finish();
                break;
            case R.id.tv_login_The_editor:
                  tv_login_the_complete.setVisibility(View.VISIBLE);
                  tv_login_the_editor.setVisibility(View.GONE);
                  im_persenter_birthday.setVisibility(View.VISIBLE);
                  im_persenter_phone.setVisibility(View.VISIBLE);
                  im_persenter_sex.setVisibility(View.VISIBLE);
                  im_persenter_username.setVisibility(View.VISIBLE);
                  im_persenter_heand1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_login_The_complete:
                tv_login_the_editor.setVisibility(View.VISIBLE);
                tv_login_the_complete.setVisibility(View.GONE);
                im_persenter_birthday.setVisibility(View.GONE);
                im_persenter_phone.setVisibility(View.GONE);
                im_persenter_sex.setVisibility(View.GONE);
                im_persenter_username.setVisibility(View.GONE);
                im_persenter_heand1.setVisibility(View.GONE);

                break;
            case R.id.im_persenter_heand:
                setPhoto();
                break;

        }
    }

    private void setPhoto() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.activity_alert, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.photo_alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.png")));
                ((Personal_confidence_Activity) context).startActivityForResult(intent2, 2);//采用ForResult打开
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.pic_alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                ((Personal_confidence_Activity) context).startActivityForResult(intent1, 1);//采用ForResult打开
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.cancel_alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void setData(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.png");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras == null) {
                        return;
                    }
                    head = extras.getParcelable("data");
                    if (head != null) {
                        im_persenter_heand.setImageBitmap(head);
                        String fileName = path + "/head.png";//图片名字
                        setPicToView(head);//保存在SD卡中
                        File file1 = new File(fileName);
                        uploadPic(file1);

                    }
                }
                break;
            default:
                break;
        }

    }
    private void uploadPic(File file1) {
        RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"),file1);
        MultipartBody.Part part= MultipartBody.Part.createFormData("file","head.png",file);
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        Toast.makeText(context,userld+"jj"+sessionId,Toast.LENGTH_LONG).show();
        Map<String,String> map = new HashMap<>();
       // map.put("uid",s.getString("uid",""));
        map.put("userld",userld);
        map.put("sessionId",sessionId);
        new Utility().part("/verify/uploadHeadPic",map,part).result(new HttpListener() {
            @Override
            public void success(String data) {
              Toast.makeText(context,"jj",Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                Upload_picture upload_picture = gson.fromJson(data, Upload_picture.class);

                if("0000".equals(upload_picture.getStatus())){
                    Toast.makeText(context, data+"", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void fail(String error) {

            }
        });

    }
//    private void doHttp() {
//        Map<String,String> map = new HashMap<>();
//        map.put("uid",s.getString("uid",""));
//        new Utility().get("/user/getUserInfo",map).result(new HttpListener() {
//            @Override
//            public void success(String data) {
//                Gson gson = new Gson();
//                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
//                String code = loginBean.getCode();
//                if("0".equals(code)){
//                    String icon = (String) loginBean.getData().getIcon();
//                    s.edit().putString("icon",icon).commit();
//                }
//            }
//
//            @Override
//            public void fail(String error) {
//
//            }
//        });
//    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        ((Personal_confidence_Activity) context).startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.png";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
