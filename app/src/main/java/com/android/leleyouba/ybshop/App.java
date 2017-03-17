package com.android.leleyouba.ybshop;

import android.app.Application;

import com.android.leleyouba.ybshop.mine.util.CityDataHelper;

import java.io.InputStream;

/**
 * Created by xalo on 2017/3/11.
 */

public class App extends Application {

    private CityDataHelper mDataHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        //拷贝数据库文件
        mDataHelper = CityDataHelper.getInstance(this);
        InputStream in = this.getResources().openRawResource(R.raw.province);
        mDataHelper.copyFile(in,CityDataHelper.DATABASE_NAME,CityDataHelper.DATABASES_DIR);
    }
}
