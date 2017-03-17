package com.android.leleyouba.ybshop.mine.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.common.util.ScreenUtil;
import com.android.leleyouba.ybshop.mine.bean.AddressBean;
import com.android.leleyouba.ybshop.mine.util.DBManager;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditAndNewAddressActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "EditAndNewAddress";
    Toolbar toolbar_edit_new_address;
    TextView tv_toolbar_edit_new_address, tv_address_location;
    EditText edt_add_address_name, edt_add_address_phone, edt_address_detail, edt_add_address_postcode;
    LinearLayout linear_address_choose_contact;
    ImageView img_address_location;
    Button btn_address_save;
    OkHttpClient okhttpClient;
    DisplayMetrics metrics;
    AddressBean addressBean;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_new_address);
        metrics = ScreenUtil.getScreenSize(this);
        initView();
        Intent intent = getIntent();
        if (intent.getBundleExtra("address") != null) {
            isEdit = true;
            Bundle bundle = intent.getBundleExtra("address");
             addressBean = (AddressBean) bundle.getSerializable("address");
            tv_toolbar_edit_new_address.setText("编辑收货地址");
            tv_address_location.setText(addressBean.getConsigneeAddress());
                edt_add_address_phone.setText(addressBean.getConsigneeNum());
            edt_add_address_name.setText(addressBean.getConsigneename());
            edt_add_address_postcode.setText(addressBean.getConsigneePC());
            edt_address_detail.setText(addressBean.getConsigneeAddress());
        } else {
            isEdit = false;
            tv_toolbar_edit_new_address.setText("新建收货地址");
        }
    }

    private void initView() {
        toolbar_edit_new_address = (Toolbar) findViewById(R.id.toolbar_edit_new_address);
        setSupportActionBar(toolbar_edit_new_address);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tv_toolbar_edit_new_address = (TextView) findViewById(R.id.tv_toolbar_edit_new_address);
        tv_address_location = (TextView) findViewById(R.id.tv_address_location);
        edt_add_address_name = (EditText) findViewById(R.id.edt_add_address_name);
        edt_add_address_phone = (EditText) findViewById(R.id.edt_add_address_phone);
        edt_address_detail = (EditText) findViewById(R.id.edt_address_detail);
        linear_address_choose_contact = (LinearLayout) findViewById(R.id.linear_address_choose_contact);
        img_address_location = (ImageView) findViewById(R.id.img_address_location);
        btn_address_save = (Button) findViewById(R.id.btn_address_save);
        edt_add_address_postcode = (EditText) findViewById(R.id.edt_add_address_postcode);
        tv_address_location.setOnClickListener(this);
        linear_address_choose_contact.setOnClickListener(this);
        img_address_location.setOnClickListener(this);
        btn_address_save.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_address_location:
            case R.id.img_address_location:

                Intent intent = new Intent(EditAndNewAddressActivity.this, AddressPickerActivity.class);
                startActivityForResult(intent, 102);


                break;

            case R.id.linear_address_choose_contact:
                jumpToUseSip();
                //跳转到联系人列表选择联系人
                break;
            case R.id.btn_address_save:
                //保存联系人
                if (isEdit){
                    saveEditConsigneeToServer();
                }else {
                    saveConsigneeToServer();
                }


                break;

        }
    }


    private void jumpToUseSip() {

        if (ContextCompat.checkSelfPermission(EditAndNewAddressActivity.this, Manifest.permission_group.CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditAndNewAddressActivity.this, new String[]{Manifest.permission
                    .READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}, 105);
        } else {
            jump();
        }
    }


    /**
     * 舔砖到联系人界面
     */
    private void jump() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = getPhoneContacts(uri);
                edt_add_address_name.setText(contacts[0]);
                edt_add_address_phone.setText(contacts[1]);
                break;

            case 102:

                if (resultCode == RESULT_OK) {
                    //处理返回的地区信息

                    if (data != null) {
                        String address = data.getStringExtra("addressPick");
                        if (address != null) {
                            tv_address_location.setText(address);
                        }
                    }
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        // 取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            // 取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            // 取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract
                    .CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        switch (requestCode) {
            case 105:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    jump();
                } else {
                    Toast.makeText(this, "Permission deny", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public boolean checkNull() {
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        if (TextUtils.isEmpty(edt_add_address_name.getText().toString().trim())) {

            toast.setText("收货人姓名不能为空");
            toast.show();
            return true;
        }

        if (TextUtils.isEmpty(edt_add_address_phone.getText().toString().trim())) {
            toast.setText("收货人电话不能为空");
            toast.show();
            return true;
        }

        if (TextUtils.isEmpty(tv_address_location.getText().toString().trim())) {
            toast.setText("收货人地址不能为空");
            toast.show();
            return true;
        }

        if (TextUtils.isEmpty(edt_address_detail.getText().toString().trim())) {
            toast.setText("收货人详细地址不能为空");
            toast.show();
            return true;
        }
        if (TextUtils.isEmpty(edt_add_address_postcode.getText().toString().trim())) {
            toast.setText("邮政编码不能为空");
            toast.show();
            return true;
        }

        return false;
    }


    public void saveConsigneeToServer() {
        if (!checkNull()) {



            okhttpClient = new OkHttpClient();

            FormBody formbody = new FormBody.Builder()
                    .add("address.consigneename", edt_add_address_name.getText().toString().trim())
                    .add("address.consigneeNum", edt_add_address_phone.getText().toString().trim())
                    .add("address.consigneePC", edt_add_address_postcode.getText().toString().trim())
                    .add("address.consigneeAddress", tv_address_location.getText().toString().trim() + edt_address_detail
                    .getText().toString().trim())
                    .add("address.default_address", "0")
                    .build();



            Request request = new Request.Builder().addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                    .post(formbody).url(URLCollection.ADD_ADDRESS).build();
            Call call = okhttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = handler.obtainMessage();
                    if (isEdit){
                        message.obj = "地址修改失败";
                    }else {
                        message.obj = "地址添加失败";
                    }
                    message.what = 102;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String str = response.body().string();
                    try {
                        JSONObject json = new JSONObject(str);
                        Message message = handler.obtainMessage();
                        String info = "";
                        if (json.has("info")) {
                            info = json.getString("info");
                        }
                        message.obj = info;
                        if (json.has("status")) {
                            int a = json.getInt("status");
                            if (a == 0) {
                                message.what = 100;
                            } else {
                                message.what = 101;
                            }
                            handler.sendMessage(message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void saveEditConsigneeToServer() {
        if (!checkNull()) {



            okhttpClient = new OkHttpClient();

            if (addressBean != null){
                FormBody  formbody = new FormBody.Builder()
                        .add("address.consigneename", edt_add_address_name.getText().toString().trim())
                        .add("address.consigneeNum", edt_add_address_phone.getText().toString().trim())
                        .add("address.consigneePC", edt_add_address_postcode.getText().toString().trim())
                        .add("address.consigneeAddress", tv_address_location.getText().toString().trim() + edt_address_detail
                                .getText().toString().trim())
                        .add("address.default_address", "0")
                        .add("address_id",addressBean.getId())
                        .build();
                Request request = new Request.Builder().addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                        .post(formbody).url(URLCollection.UPDATE_ADDRESS).build();
                Call call = okhttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        try {
                            JSONObject json = new JSONObject(str);
                            Message message = handler.obtainMessage();
                            String info = "";
                            if (json.has("info")) {
                                info = json.getString("info");
                            }
                            message.obj = info;
                            if (json.has("status")) {
                                int a = json.getInt("status");
                                if (a == 0) {
                                    message.what = 100;
                                } else {
                                    message.what = 101;
                                }
                                handler.sendMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case 100:
                    String info = (String) msg.obj;
                    Toast.makeText(EditAndNewAddressActivity.this, info, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 101:
                    String info1 = (String) msg.obj;
                    Toast.makeText(EditAndNewAddressActivity.this, info1, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                    finish();
                    break;
                case  102:
                    String info2 = (String) msg.obj;
                    Toast.makeText(EditAndNewAddressActivity.this, info2, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                    finish();
                    break;

            }

            return false;
        }
    });

}
