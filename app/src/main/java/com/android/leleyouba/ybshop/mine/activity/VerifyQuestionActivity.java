package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.BaseActivity;
import com.android.leleyouba.ybshop.R;

import org.w3c.dom.Text;

/**
 *
 */
public class VerifyQuestionActivity extends BaseActivity {

    private static final int QUESTION = R.layout.activity_verify_question;
    private static final int NICKNAME = R.layout.activity_verify_nickname;
    private static final int GENDER = R.layout.activity_verify_gender;

    private static final int QUESTION_TOOLBAR = R.id.verify_question_toolbar;
    private static final int NICKNAME_TOOLBAR = R.id.verify_nickname_toolbar;
    private static final int GENDER_TOOLBAR = R.id.verify_gender_toolbar;


    private EditText et_verify_question,et_verify_answer,et_nickname;
    private TextView tv_gender_man,tv_gender_woman,tv_gender_secrecy;
    private   int viewType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout();
    }

    /**
     * 判断是显示界面
     */

    private void setLayout(){
        Intent intent = getIntent();
        int type = intent.getIntExtra(PersonInfoActivity.VERIFY_KEY,88);
        switch (type){
            case PersonInfoActivity.NICKNAME_REQUESTCODE:
                setContentView(NICKNAME);
                viewType = NICKNAME;
                setToolBar(NICKNAME_TOOLBAR);
                initNickNameView();

                break;
            case PersonInfoActivity.GENDER_REQUESTCODE:
                setContentView(GENDER);
                viewType = GENDER;
                setToolBar(GENDER_TOOLBAR);
                initGenderView();

                break;
            case PersonInfoActivity.QUESTION_REQUESTCODE:
                setContentView(QUESTION);
                viewType = QUESTION;
                setToolBar(QUESTION_TOOLBAR);
                initQuestionView();
                break;
            default:
                break;
        }
    }

    private void initGenderView() {
        tv_gender_man = (TextView) findViewById(R.id.tv_gender_man);
        tv_gender_woman = (TextView) findViewById(R.id.tv_gender_woman);
        tv_gender_secrecy = (TextView) findViewById(R.id.tv_gender_secrecy);
        tv_gender_man.setOnClickListener(new MyOnClickListener());
        tv_gender_woman.setOnClickListener(new MyOnClickListener());
        tv_gender_secrecy.setOnClickListener(new MyOnClickListener());
    }

    private void initNickNameView() {
        et_nickname = (EditText) findViewById(R.id.et_nickname);

    }
    private void initQuestionView(){
        et_verify_question = (EditText) findViewById(R.id.et_verify_question);
        et_verify_answer = (EditText) findViewById(R.id.et_verify_answer);
    }


    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.tv_gender_man:
                case R.id.tv_gender_woman:
                case R.id.tv_gender_secrecy:
                    genderResult(v);
                    break;

            }

        }
    }

    //性别界面的返回
    private void genderResult(View v){
        TextView textView = (TextView) v;
        String gender = textView.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("gender",gender);
        setResult(RESULT_OK,intent);
        finish();
    }

    //设置ToolBar
    public void setToolBar(int resID){
        Toolbar toolbar = (Toolbar) findViewById(resID);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_completion:

                switch (viewType){
                    case NICKNAME:

                   String nickname = et_nickname.getText().toString();
                    if (!TextUtils.isEmpty(nickname)){
                        Intent intent = new Intent();
                        intent.putExtra("nickname",nickname);
                        setResult(RESULT_OK,intent);
                        finish();
                    }else {
                        Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case QUESTION:
                        String question = et_verify_question.getText().toString();
                        String answer = et_verify_answer.getText().toString();
                        if (!TextUtils.isEmpty(question) && !TextUtils.isEmpty(answer)){
                            Intent intent = new Intent();
                            String[] qu = new String[]{question,answer};
                            intent.putExtra("question",qu);
                            setResult(RESULT_OK,intent);
                            finish();
                        }else {
                            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_completion,menu);

        return true;
    }
}
