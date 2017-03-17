package com.android.leleyouba.ybshop.mine.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.BaseActivity;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.bean.VerifyQuestionBean;
import com.android.leleyouba.ybshop.mine.util.UploadUtil;
import com.android.leleyouba.ybshop.mine.util.UserInfoSave;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.android.leleyouba.ybshop.shoppingtrolley.util.FormFile;
import com.android.leleyouba.ybshop.shoppingtrolley.util.SocketHttpRequester;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "PersonInfoActivity";

    String accountStr = "";

    VerifyQuestionBean questionBean = new VerifyQuestionBean();


    public static final int NICKNAME_REQUESTCODE = 100;
    public static final int GENDER_REQUESTCODE = 101;
    public static final int QUESTION_REQUESTCODE = 102;
    public static final String VERIFY_KEY = "启动填写界面";
    Toolbar mToolbar;
    RelativeLayout rela_head_img,//顶部的带图片的布局
            rela_person_user_name,//用户名的布局
            rela_person_nickname,//昵称的布局
            rela_person_gender,//性别的布局
            rela_person_birthday,//生日的布局
            rela_verify_question,//验证问题的布局
            rela_phoneNum;//电话号码的布局
    ImageView person_user_img;//用户头像
    TextView tv_person_user_name,//用户名，不支持修改
            tv_person_nickname,//昵称
            tv_person_gender,//性别
            tv_person_birthday,//生日
            tv_phoneNum,//电话号码
            tv_person_verify;//验证问题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        getfiel();
        initView();
        setData();


        Intent in = getIntent();
        accountStr = in.getStringExtra("name");
        Log.d("--------", "onCreate: " + accountStr);
    }

    private void setData() {


        Log.d(TAG, "setData: "+UserInfoSave.getInstace().getQuestion()+UserInfoSave.getInstace());


        if (UserInfoSave.getInstace().getPhoto() != null){
            Glide.with(this).load(UserInfoSave.getInstace().getPhoto()).asBitmap().into(person_user_img);
        }
        tv_person_user_name.setText(UserInfoSave.getInstace().getUsername());

        if (UserInfoSave.getInstace().getNickname() != null){
            tv_person_nickname.setText(UserInfoSave.getInstace().getNickname());
        }
        if (UserInfoSave.getInstace().getSex() != null){
            tv_person_gender.setText(UserInfoSave.getInstace().getSex());
        }
        if (UserInfoSave.getInstace().getBirth() != null){
            tv_person_birthday.setText(UserInfoSave.getInstace().getBirth());
        }

        if (UserInfoSave.getInstace().getPhoneNum() != null){
            tv_phoneNum.setText(UserInfoSave.getInstace().getPhoneNum());
        }
        if (UserInfoSave.getInstace().getQuestion() != null){
            tv_person_verify.setText("已验证");
        }


    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.person_info_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        rela_head_img = (RelativeLayout) findViewById(R.id.rela_head_img);
        rela_person_user_name = (RelativeLayout) findViewById(R.id.rela_person_user_name);
        rela_person_nickname = (RelativeLayout) findViewById(R.id.rela_person_nickname);
        rela_person_gender = (RelativeLayout) findViewById(R.id.rela_person_gender);
        rela_person_birthday = (RelativeLayout) findViewById(R.id.rela_person_birthday);
        rela_verify_question = (RelativeLayout) findViewById(R.id.rela_verify_question);
        rela_phoneNum = (RelativeLayout) findViewById(R.id.rela_phoneNum);

        person_user_img = (ImageView) findViewById(R.id.person_user_img);
        tv_person_user_name = (TextView) findViewById(R.id.tv_person_user_name);
        tv_person_nickname = (TextView) findViewById(R.id.tv_person_nickname);
        tv_person_gender = (TextView) findViewById(R.id.tv_person_gender);
        tv_person_birthday = (TextView) findViewById(R.id.tv_person_birthday);
        tv_person_verify = (TextView) findViewById(R.id.tv_person_verify);
        tv_phoneNum = (TextView) findViewById(R.id.tv_phoneNum);

        rela_head_img.setOnClickListener(this);
        rela_person_user_name.setOnClickListener(this);
        rela_person_nickname.setOnClickListener(this);
        rela_person_gender.setOnClickListener(this);
        rela_person_birthday.setOnClickListener(this);
        rela_verify_question.setOnClickListener(this);
        rela_phoneNum.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "点击了返回按钮", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.toolbar_completion:
                final String nickname = tv_person_nickname.getText().toString();
                final String gender = tv_person_gender.getText().toString();
                final String birthday = tv_person_birthday.getText().toString();
                final String phone = tv_phoneNum.getText().toString();
                final String question = questionBean.getQuestion();
                final String answer = questionBean.getAnswer();

//                if (!TextUtils.isEmpty(nickname)&&!TextUtils.isEmpty(gender)&&!TextUtils.isEmpty(birthday)
//                        &&!TextUtils.isEmpty(phone) &&!TextUtils.isEmpty(question)&&!TextUtils.isEmpty(answer)){
                //发起网络请求


                        postFile(nickname, gender, birthday, phone, question, answer);



                Log.d("----", "onOptionsItemSelected: " + nickname + gender + birthday + phone + question + answer);
//                }else {
//                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rela_head_img:
                //更换图片
                break;
            case R.id.rela_person_user_name:
                //提示用户名不能修改
                Toast.makeText(this, "用户名不支持修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rela_person_nickname:
                //更改昵称
                startVerifyActivityForResult(NICKNAME_REQUESTCODE);
                break;
            case R.id.rela_person_gender:
                //更换性别
                startVerifyActivityForResult(GENDER_REQUESTCODE);
                break;
            case R.id.rela_person_birthday:
                //更换生日
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int newMOnth = month + 1;
                        String bir = year + "年" + newMOnth + "月" + dayOfMonth + "日";


                        tv_person_birthday.setText(bir);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();


                break;
            case R.id.rela_verify_question:
                //完善验证问题
                startVerifyActivityForResult(QUESTION_REQUESTCODE);
                break;
            case R.id.rela_phoneNum:
                //提示电话不可修改
                Toast.makeText(this, "电话号码不支持修改", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void startVerifyActivityForResult(int code) {

        Intent intent = new Intent(PersonInfoActivity.this, VerifyQuestionActivity.class);
        intent.putExtra(VERIFY_KEY, code);
        startActivityForResult(intent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case GENDER_REQUESTCODE:

                    String gender = data.getStringExtra("gender");
                    tv_person_gender.setText(gender);
                    break;
                case NICKNAME_REQUESTCODE:

                    String nickname = data.getStringExtra("nickname");
                    tv_person_nickname.setText(nickname);

                    break;
                case QUESTION_REQUESTCODE:
                    String[] qu = data.getStringArrayExtra("question");
                    if (qu != null && qu.length == 2) {
                        tv_person_verify.setText("已验证");
                        questionBean.setQuestion(qu[0]);
                        questionBean.setAnswer(qu[1]);
                    }

                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_completion, menu);
        return true;
    }

    String str23 = "";
    private void postAsynHttp(final String nickname, final String gender, final String birthday, String phone, final String question, final String answer) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("message.birth", birthday);
                params.put("message.sex", gender);
                params.put("message.nickname", nickname);
                params.put("message.question", question);
                params.put("message.answer", answer);
                params.put("message.phoneNum", "123456789");

                File file = new File(getExternalCacheDir().getPath() + "f456.jpg");
                try {
                    InputStream is = getAssets().open("f456.jpg");
                    str23 =    encode(is);


                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    int ch = 0;
                    while ((ch = is.read()) != -1) {
                        fileOutputStream.write(ch);
                    }

                    FormFile formfile = new FormFile("cachef456.jpg",file,"message.photo","image/jpg");
                    Log.d("-------", "run: "+file.getAbsolutePath());

                    SocketHttpRequester.post(URLCollection.PERSONURL,params,formfile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();




//       Map<String, String> params = new HashMap<String, String>();
//params.put("send_userId", String.valueOf(id));
//        params.put("message.username", accountStr);
//        params.put("message.birth", birthday);
//        params.put("message.sex", gender);
//        params.put("message.nickname", nickname);
//        params.put("message.question", question);
//        params.put("message.answer", answer);
//        params.put("message.phoneNum", "123456789");
//        params.put("message.photo",str);
//        File file = new File(getExternalCacheDir().getPath() + "apple.jpeg");
//        try {
//            //拿到输入流
//            InputStream is = getAssets().open("apple.jpeg");
//            str23 =    encode(is);
//            Log.d("-------", "postAsynHttp: "+str23);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//
//            int ch = 0;
//            while ((ch = is.read()) != -1) {
//                fileOutputStream.write(ch);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    OkHttpClient mOkHttpClient = new OkHttpClient();



   File file;

public void getfiel(){

    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                file = new File(getExternalFilesDir("DIRECTORY_PICTURES")+ "f456.jpg");
                //拿到输入流
                InputStream is = getAssets().open("f456.jpg");
//            str23 = encode(is);
                Log.d("-------", "postAsynHttp: "+str23);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                int ch = 0;
                while ((ch = is.read()) != -1) {
                    fileOutputStream.write(ch);
                }
                fileOutputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();


}


private void postFile(final String nickname, final String gender, final String birthday, String phone, final String question, final String answer){





    Log.d("----------", "postFile: "+file.exists()+file.getTotalSpace());
    RequestBody fileBody1 = RequestBody.create(MediaType.parse("image/jpg") , file);

    Bitmap bit = BitmapFactory.decodeFile(file.getAbsolutePath());
    person_user_img.setImageBitmap(bit);
    Log.d("---------", "File: "+file.isFile()+file.getAbsolutePath()+fileBody1.contentType());

    MultipartBody mBOdy = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file",file.getName(),fileBody1)
            .addFormDataPart("message.birth", birthday)
            .addFormDataPart("message.sex", gender)
            .addFormDataPart("message.nickname", nickname)
            .addFormDataPart("message.question", question)
            .addFormDataPart("message.answer", answer)
            .addFormDataPart("message.phoneNum", "123456789")

            .build();

    Log.d("-------", "postFile: ooo"+mBOdy);
    Log.d("-----", "postFile: cookie"+UserStatusUtil.getInstanse().getCookie());
    Request request = new Request.Builder().url(URLCollection.PERSONURL)
            .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
            .post(mBOdy)
            .build();
    mOkHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, final IOException e) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(PersonInfoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            final String bodyStr = response.body().string();
            final boolean ok = response.isSuccessful();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (ok){


                        Log.d("-------", "run: "+bodyStr);
                        Toast.makeText(PersonInfoActivity.this, bodyStr, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(PersonInfoActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    });

//    params.put("message.username", accountStr);
//        params.put("message.birth", birthday);
//        params.put("message.sex", gender);
//        params.put("message.nickname", nickname);
//        params.put("message.question", question);
//        params.put("message.answer", answer);
//        params.put("message.phoneNum", "123456789");
//        params.put("message.photo",str);
//        File file = new File(getExternalCacheDir().getPath() + "apple.jpeg");
//        try {
//            //拿到输入流
//            InputStream is = getAssets().open("apple.jpeg");
//            str23 =    encode(is);
//            Log.d("-------", "postAsynHttp: "+str23);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//
//            int ch = 0;
//            while ((ch = is.read()) != -1) {
//                fileOutputStream.write(ch);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
}



    private String encode(InputStream path) {
        //decode to bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(path);
        Log.d("---------", "bitmap width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());
        //convert to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();

        //base64 encode
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        String encodeString = new String(encode);
        return  encodeString;
    }

}
