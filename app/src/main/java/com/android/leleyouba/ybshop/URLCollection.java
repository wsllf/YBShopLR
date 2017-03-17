package com.android.leleyouba.ybshop;

/**
 * 接口类
 */

public class URLCollection {
    //服务器
    public static final String BASE_URL = "http://192.168.10.124:8080";
    //登录注册时的接口
    public static final String LOGINURL = BASE_URL + "/YoubaShopping/User_userLogin";
    public static final String REGISTURL = BASE_URL + "/YoubaShopping/User_userRegist";
    public static final String REGISTIMGURL = BASE_URL + "/YoubaShopping/User_userRegist/img/rand.action";
    public static final String LOGINMGURL = BASE_URL + "/YoubaShopping/User_userLogin/img/rand.action";
    //提交个人信息
    public static final String PERSONURL = BASE_URL + "/YoubaShopping/User_messageAdd";
    //退出登录
    public static final String EXITLOGIN = BASE_URL + "/YoubaShopping/User_exitLogin";
    //忘记密码
    //验证码
    public static final String VERIFYURL = BASE_URL + "/YoubaShopping/User_getQuestion/img/rand.action";
    //获取验证问题
    public static final String GETVERIFYQUESTIONURL = BASE_URL + "/YoubaShopping/User_getQuestion";
    //回答验证问题
    public static final String ANSWERVERIFYQUESTIONURL = BASE_URL + "/YoubaShopping/User_answerQuestion";
    //提交新密码
    public static final String COMMITNEWPWDURL = BASE_URL + "/YoubaShopping/User_forgotPassword";
    //修改密码的验证码
    public static final String MODIFYIMG = BASE_URL + "/YoubaShopping/User_modifyPassword/img/rand.action";
    //修改密码
    public static final String MODIFYPWD = BASE_URL + "/YoubaShopping/User_modifyPassword";
    //查询购物车
    public static final String SHOPWARESINFO = BASE_URL + "/YoubaShopping/Shopping_selectCart";
    //添加收货地址
    public static final String ADD_ADDRESS = BASE_URL + "/YoubaShopping/User_addressAdd";
    //查询个人基本信息
    public static final String CHECK_USER_MESSAGE = BASE_URL + "/YoubaShopping/User_messageCheck";

    //查询收货地址
    public static final String CHECK_ADDRESS = BASE_URL + "/YoubaShopping/User_addressCheck";
    //收货地址更新
    public static final String UPDATE_ADDRESS = BASE_URL + "/YoubaShopping/User_addressUpdate";
    //收货地址删除
    public static final String DELETE_ADDRESS = BASE_URL + "/YoubaShopping/User_addressDelete";
    //默认地址设置
    public static final String SET_DEFAULT_ADDRESS = BASE_URL + "/YoubaShopping/User_addressDefault";


    //查询购物车的信息
    public static final String SHOPPING_SELECTCART = BASE_URL + "/YoubaShopping/Shopping_selectCart";

    //首页轮播图的数据
    public static final String HOMEPAGE_LUNBOPIC = BASE_URL + "/YoubaShopping/Admin_lunBoShow";
}
