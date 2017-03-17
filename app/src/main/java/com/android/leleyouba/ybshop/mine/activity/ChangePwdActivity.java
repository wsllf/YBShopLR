package com.android.leleyouba.ybshop.mine.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText edt_old_change,edt_new_change,edt_confirm_change,edt_yan_change;
    ImageView img_change_pwd;
    Button btn_confirm;


    OkHttpClient okHttpClient;

    String mString = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        initView();
        //请求验证码
        getverificationImg();

    }



    private void initView() {
        edt_old_change = (TextInputEditText) findViewById(R.id.edt_old_change);
        edt_new_change = (TextInputEditText) findViewById(R.id.edt_new_change);
        edt_confirm_change = (TextInputEditText) findViewById(R.id.edt_confirm_change);
        edt_yan_change = (TextInputEditText) findViewById(R.id.edt_yan_change);
        img_change_pwd = (ImageView) findViewById(R.id.img_change_pwd);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        img_change_pwd.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    private void getverificationImg() {

        okHttpClient = new OkHttpClient();
        mString =   CommonMethod.changeImage1(okHttpClient,handler,CommonMethod.SUCCESS,this,URLCollection.MODIFYIMG);

    }





    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CommonMethod.SUCCESS:

                    byte[] img_byte = (byte[]) msg.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(img_byte,0,img_byte.length);
                    Log.d("--------", "handleMessage: "+img_byte.length);
                    img_change_pwd.setImageBitmap(bitmap);
                    break;

                case CommonMethod.COOKIE:
                    Toast.makeText(ChangePwdActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_change_pwd:

                getverificationImg();
                break;
            case R.id.btn_confirm:

                changePwd();
                break;
        }

    }
    String reason = new String();
    public boolean isEmpty(){
        boolean isEmpty = false;


        if (TextUtils.isEmpty(edt_old_change.getText().toString().trim())){
            isEmpty = true;
            reason = "旧密码不能为空";
            return isEmpty;
        }
        if (TextUtils.isEmpty(edt_new_change.getText().toString().trim())){
            isEmpty = true;
            reason = "新密码不能为空";
            return isEmpty;
        }
        if (TextUtils.isEmpty(edt_confirm_change.getText().toString().trim())){
            isEmpty = true;
            reason = "确认密码不能为空";
            return isEmpty;
        }
        if (TextUtils.isEmpty(edt_yan_change.getText().toString().trim())){
            isEmpty = true;
            reason = "验证码不能为空";
            return isEmpty;
        }
        if (!TextUtils.equals(edt_new_change.getText().toString().trim(),edt_confirm_change.getText().toString().trim())){
            isEmpty = true;
            reason = "两次密码不一致";
            return isEmpty;
        }

        return isEmpty;
    }


    private void changePwd() {
        if (isEmpty()){
            Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
        }else {

            okHttpClient = new OkHttpClient();
            FormBody body = new FormBody.Builder()
                    .add("user.password",edt_old_change.getText().toString().trim())
                    .add("newPassword",edt_new_change.getText().toString().trim())
                    .add("confirmPassword",edt_confirm_change.getText().toString().trim())
                    .add("verify",edt_yan_change.getText().toString().trim())
                    .build();

            Log.d("-----", "changePwd: "+UserStatusUtil.getInstanse().getCookie());
            Request request = new Request.Builder()
                    .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
//                    .addHeader("cookie", mString)
                    .post(body)
                    .url(URLCollection.MODIFYPWD)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChangePwdActivity.this, "网络失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String str = response.body().string();
                    Log.d("-----", "onResponse: "+str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChangePwdActivity.this, str, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }
}
