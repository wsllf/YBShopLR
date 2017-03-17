package com.android.leleyouba.ybshop.shoppingtrolley.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.alipay.AuthResult;
import com.android.leleyouba.ybshop.common.alipay.OrderInfoUtil2_0;
import com.android.leleyouba.ybshop.common.alipay.PayResult;

import java.util.Map;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016080300156139";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQD3Sesp3rOEmoJV\n" + "fvmjlG/ZQHEMieVa8wPM3dl0Mf9g1BQm/9qcJZfdl4xtmL2iajPU1fXcluB1s2/r\n" + "ZwEdHBpcesxcyJvOKTcit95jRU9tBfjTcSeYx+1TTwNFMvr2VpVelhv2troPeFAn\n" + "ptc4sY6vatUJlDVW/hVn88O7Oo4QesSFHvfI0ZIMA7cft1uRBJlxmDWFBK2U9Qkg\n" + "BLGwArAoCjxa3gHyPFeQ8bxZK5yoC3MzghdMYcxy8gPQ4F8CQlG4ElmXWDW7aeG7\n" + "13oopP3cvyKNIPRw2SUd4OimsnNbuDXs1u80UgTczwQznLIl+eE/1wVliCu3zLuv\n" + "dAgNoOO1AgMBAAECggEADu1TWK+svnBlNav4fbtUFRUivdMFGTxaGl+55J2wALRM\n" + "kx4WoB5U0Hd5bpQtvkQS5OIVeT9SRUiTlO4ezxcHMFXpSCCD5yntvhbDQdLJPJeD\n" + "sZ+++CX5WXIHjIHLTFtloNQG1BmrmYt1qy8TBsURC92zBmT/40RrHl9EjWS3dp8U\n" + "JSxz0Zayn0z5cCByDUFNTgt923wkHCKUQCOPzIAyvxEZLsb498tc/u2C4HXIUgg5\n" + "m2Uet97pusXalnZmy2sOQ71QrbR0GYaOaJVyq40WJJJhdLmYOw7MVQQKWJqY4Zq4\n" + "72jDLFWtnx6RUkUZn8aKlyh/2qslxTrSueXTvOYkcQKBgQD+GMBXcl2J/XA5hLBt\n" + "wGKtVAkS0QGpM8kUJ5MEVdGrhfNLKB2ls2oZy7BRaKMiQbb8huCKg3TbX9RD5GJz\n" + "XLjyCN3tAT7cWtHJkswwnYSo1/+UWlqdkAcKIXGRwUCVg2flXMoD7sk0R8miHJEs\n" + "pQ9/oAoSRqdQ6fZvE5QYOWSVgwKBgQD5JBzQnC3exVkzw0kNiwtdyq0rDXGSNcUr\n" + "aVBKNbG2xIa/gfLaH/86xmM1v5F8vcj33R4tdwY0hlSJi550M0Pkz1Q8PstRvh/+\n" + "VMxvh4SnvgyPjOpB3o3PJ+WsiHUir3EGIrUAr1kPbi6C6NBAl/E3lLqirEYJHQGH\n" + "lFUWvROUZwKBgDbreJ8t++O4YIo+ELgYDfvynudvP1Gn/L0bdJ5KQkA1eolzCT1o\n" + "UvVyXhCqciAUXg2kxWGK2jYUN1fZRCI1F1BQ9E6mp0SPaKqNzQ5w7VxlGzugb9eA\n" + "atUYt2hOgbwPI+SiMpO5B8oB/iXI0OP6ZSgrmiGrHbzSZM5LSAYYWZIrAoGAPZyH\n" + "n7MvyMqHD7z4gOfkzLIFdwMhFeZEN0MY/vEeXLNnG074jqahCJjtCeqxAOcyHtHI\n" + "17d6ily55TXo3pWa8aewDiH9R967rLqtv9sEnDLsikdv2xA9g4A7VRp91TELw1er\n" + "6VpzrNQznl4SuhaZKYSXTwlJXJGWwj+Vd0UU5XsCgYEAvc+PcazzXUvgp8X23dnl\n" + "/w3KTR7ejYy4s4fS0k+8TV5ntZytgFKW1oAVURUbp8RY1jzYqSKJG1oqx1JxjD4W\n" + "hYIHY8sF5PK6qsItliGa00xOoWVGY2q0hphMDjQXFsXQesELubhIAIKnb0tXcgdQ\n" + "/pQGebYdtQWCfJrhanj9cbE=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        //设置导航栏
        setToolbar();
        //
        initView();
    }


    private void setToolbar() {
        Toolbar toolbar_payment_show = (Toolbar) findViewById(R.id.toolbar_payment_show);
        setSupportActionBar(toolbar_payment_show);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        TextView tv_payment_order_price = (TextView) findViewById(R.id.tv_payment_order_price);
        TextView tv_payment_order_detail = (TextView) findViewById(R.id.tv_payment_order_detail);
        RelativeLayout rl_payment_youba = (RelativeLayout) findViewById(R.id.rl_payment_youba);
        RelativeLayout rl_payment_alipay = (RelativeLayout) findViewById(R.id.rl_payment_alipay);
        tv_payment_order_detail.setOnClickListener(this);
        rl_payment_youba.setOnClickListener(this);
        rl_payment_alipay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_payment_order_detail:
                startActivity(new Intent(PaymentActivity.this, OrderDetailActivity.class));
                overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
            case R.id.rl_payment_youba:
                Toast.makeText(PaymentActivity.this, "有吧支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_payment_alipay:
                Toast.makeText(PaymentActivity.this, "支付宝支付", Toast.LENGTH_SHORT).show();
                payV2(v);
                break;
            default:
                break;
        }
    }



    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PaymentActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PaymentActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };



    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PaymentActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
