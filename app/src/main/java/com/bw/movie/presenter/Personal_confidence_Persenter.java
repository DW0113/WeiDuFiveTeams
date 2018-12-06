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
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.Personal_confidence_Activity;
import com.bw.movie.activity.Sign_in_Activity;
import com.bw.movie.activity.UpdatePassword;
import com.bw.movie.model.Upload_picture;
import com.bw.movie.mvp.view.AppDelegate;
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
    private TextView tv_personal_username;
    private View tv_personal_sex;
    private EditText et_personal_sex;
    private EditText et_personal_username;
    private String username;
    private String sex1;
    private String sessionId;
    private String userld;
    private String sex2;
    private String email;
    private SharedPreferences update;
    private String headPath;

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
        SharedPreferences register = context.getSharedPreferences("register", Context.MODE_PRIVATE);
        email = register.getString("et_register_email_get", "");
        update = context.getSharedPreferences("update", Context.MODE_PRIVATE);
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
        et_personal_sex = get(R.id.et_personal_sex);
        et_personal_username = get(R.id.et_personal_username);
        im_persenter_phone = get(R.id.im_persenter_phone);
        tv_persenter_phone = get(R.id.tv_personal_phone);
        im_persenter_birthday = get(R.id.im_persenter_birthday);
        tv_persenter_birthday = get(R.id.tv_personal_birthday);
       ImageView persenter_into= get(R.id.persenter_into);
        persenter_into.setOnClickListener(this);
        ImageView im_persenter_banck= get(R.id.im_persenter_banck);
        im_persenter_banck.setOnClickListener(this);
        //编辑
        tv_personal_username = get(R.id.tv_personal_username);
        tv_personal_sex = get(R.id.tv_personal_sex);
        im_persenter_username.setOnClickListener(this);
        im_persenter_sex.setOnClickListener(this);
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
        sessionId =login.getString("sessionId", "");
        userld =login.getString("userld", "");
        im_persenter_heand.setOnClickListener(this);
        String headPath = login.getString("headPath", "");
        if(TextUtils.isEmpty(headPath)){
            Glide.with(context).load(R.drawable.fragment_my_head_portrait).into(im_persenter_heand);
        }
        else{
            Glide.with(context).load(headPath +"").into(im_persenter_heand);
        }
        Assignment();
    }

    private void Assignment() {
        if(sex.contains("1")){
            tv_persenter_sex.setText("男");
            et_personal_sex.setText("男");
        }
        else if(sex.contains("2")){
            tv_persenter_sex.setText("女");
            et_personal_sex.setText("女");
        }
        tv_persenter_birthday.setText(birthday+"");

        tv_persenter_phone.setText(phone+"");
        tv_persenter_username.setText(nickName);
        et_personal_username.setText(nickName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_persenter_banck:
                ((Personal_confidence_Activity)context).finish();
                break;
            case R.id.tv_login_exit:
                login.edit().putString("phone","")
                        .putString("login_pwd_get","")
                        .putString("sex","")
                        .putString("email","")
                        .putString("birthday","")
                        .putString("headPath","")
                        .putString("headpic","")
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
                username = et_personal_username.getText().toString().trim();
                sex1 = et_personal_sex.getText().toString().trim();
                dohttp();
                break;
            case R.id.im_persenter_heand:
                setPhoto();
                break;
            case R.id.im_persenter_username:
                tv_personal_username.setText("");
                tv_personal_username.setVisibility(View.GONE);
                et_personal_username.setVisibility(View.VISIBLE);

                break;
            case R.id.im_persenter_sex:
                tv_persenter_sex.setText("");
                tv_persenter_sex.setVisibility(View.GONE);
                et_personal_sex.setVisibility(View.VISIBLE);

                break;
            case R.id.persenter_into:
                  context.startActivity(new Intent(context, UpdatePassword.class));
                break;

        }
    }

    private void dohttp() {
        Map m=new HashMap<>();
        m.put("userId",userld);
        m.put("sessionId",sessionId);
        Map map=new HashMap<>();
        if(sex1.equals("男")){
            sex2="1";
        }else{
            sex2="2";
        }
        map.put("nickName",username);
        map.put("sex",sex2);
        map.put("email",email);
        new Utility().postupdate(m,"movieApi/user/v1/verify/modifyUserInfo",map).result(new HttpListener() {
         @Override
         public void success(String data) {
             Toast.makeText(context,"修改成功",Toast.LENGTH_LONG).show();
             login.edit().putString("nickName",username)
                     .putString("sex",sex1)
                     .commit();
         }

         @Override
         public void fail(String error) {

         }
     });

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
        MultipartBody.Part part= MultipartBody.Part.createFormData("image","head.png",file);
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        Toast.makeText(context,userld+"jj"+sessionId,Toast.LENGTH_LONG).show();
        Map<String,String> map = new HashMap<>();
        // map.put("uid",s.getString("uid",""));
        map.put("userId",userld);
        map.put("sessionId",sessionId);
        new Utility().part("/movieApi/user/v1/verify/uploadHeadPic",map,part).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Upload_picture upload_picture = gson.fromJson(data, Upload_picture.class);

                if("0000".equals(upload_picture.getStatus())){
                    String headPath = upload_picture.getHeadPath();
                    Toast.makeText(context, data+"上传成功", Toast.LENGTH_SHORT).show();
                    //SharedPreferences update = context.getSharedPreferences("update", Context.MODE_PRIVATE);
                    Glide.with(context).load(headPath +"").into(im_persenter_heand);
                    login.edit().putString("headpic",headPath).commit();
                    onResume();
                }

            }

            @Override
            public void fail(String error) {

            }
        });

    }
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

    public void onResume() {
         nickName = login.getString("nickName", "");
         phone = login.getString("phone", "");
         sex = login.getString("sex", "");
         headPath = login.getString("headPath", "");
         birthday = login.getString("birthday", "");
         headpic = login.getString("headpic", "");
         sessionId =login.getString("sessionId", "");
         userld =login.getString("userld", "");

        if(TextUtils.isEmpty(headpic)){
            Glide.with(context).load(R.drawable.fragment_my_head_portrait).into(im_persenter_heand);

        }
        else{
            Glide.with(context).load(headpic +"").into(im_persenter_heand);
        }
        Assignment();
    }
}
