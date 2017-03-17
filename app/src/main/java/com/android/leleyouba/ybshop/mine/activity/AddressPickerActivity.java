package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.Window;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.util.CityDataHelper;
import com.android.leleyouba.ybshop.mine.widget.OnWheelChangedListener;
import com.android.leleyouba.ybshop.mine.widget.WheelView;
import com.android.leleyouba.ybshop.mine.widget.adapters.AreaAdapter;
import com.android.leleyouba.ybshop.mine.widget.adapters.CitysAdapter;
import com.android.leleyouba.ybshop.mine.widget.adapters.ProvinceAdapter;
import com.android.leleyouba.ybshop.mine.widget.model.CityModel;
import com.android.leleyouba.ybshop.mine.widget.model.DistrictModel;
import com.android.leleyouba.ybshop.mine.widget.model.ProvinceModel;

import java.util.ArrayList;
import java.util.List;

public class AddressPickerActivity extends AppCompatActivity implements OnWheelChangedListener, View.OnClickListener {


    private WheelView provinceView;
    private WheelView cityView;
    private WheelView districtView;
    private List<ProvinceModel> provinceDatas = new ArrayList<>();
    private List<CityModel> cityDatas = new ArrayList<>();
    private List<DistrictModel> districtDatas = new ArrayList<>();
    private String mCurrentProvince;
    private String mCurrentCity;
    private String mCurrentDistrict;
    private TextView tvSave, tvCancel;
    private ProvinceAdapter provinceAdapter;
    private CitysAdapter citysAdapter;
    private AreaAdapter areaAdapter;
    private SQLiteDatabase db;
    private CityDataHelper dataHelper;
    private final int TEXTSIZE = 15;//选择器的字体大小

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_address_picker);

        initView();
    }

    private void initView() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pick_address);
        setSupportActionBar(toolbar);
        ActionBar actionBAr = getSupportActionBar();
        if (actionBAr != null){
            actionBAr.setDisplayShowTitleEnabled(false);
            actionBAr.setDisplayHomeAsUpEnabled(false);
            actionBAr.setDisplayShowHomeEnabled(false);
        }

        provinceView = (WheelView) findViewById(R.id.provinceView);
        cityView = (WheelView) findViewById(R.id.cityView);
        districtView = (WheelView) findViewById(R.id.districtView);
        tvSave = (TextView) findViewById(R.id.tv_sure);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        // 设置可见条目数量
        provinceView.setVisibleItems(7);
        cityView.setVisibleItems(7);
        districtView.setVisibleItems(7);

        // 添加change事件
        provinceView.addChangingListener(this);
        // 添加change事件
        cityView.addChangingListener(this);
        // 添加change事件
        districtView.addChangingListener(this);

        initData();
    }


    private void initData() {
        //初始化数据
        dataHelper = CityDataHelper.getInstance(this);
        db = dataHelper.openDataBase();
        provinceDatas = dataHelper.getProvice(db);
        if (provinceDatas.size() > 0) {
            mCurrentProvince = provinceDatas.get(0).NAME;
            cityDatas = dataHelper.getCityByParentId(db, provinceDatas.get(0).CODE);
        }
        if (cityDatas.size() > 0) {
            districtDatas = dataHelper.getDistrictById(db, cityDatas.get(0).CODE);
        }
        provinceAdapter = new ProvinceAdapter(this, provinceDatas);
        provinceAdapter.setTextSize(TEXTSIZE);//设置字体大小
        provinceView.setViewAdapter(provinceAdapter);
        mCurrentProvince = provinceDatas.get(provinceView.getCurrentItem()).NAME;
        mCurrentCity = cityDatas.get(cityView.getCurrentItem()).NAME;
        mCurrentDistrict = districtDatas.get(districtView.getCurrentItem()).NAME;
        updateCitys();
        updateAreas();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_sure:


                intent.putExtra("addressPick",mCurrentProvince+mCurrentCity+mCurrentDistrict);
                setResult(RESULT_OK,intent);
                break;
            case R.id.tv_cancel:

                setResult(RESULT_CANCELED);

                break;
            default:
                break;
        }
        finish();

    }

    /**
     * Callback method to be invoked when current item changed
     *
     * @param wheel    the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == provinceView) {
            mCurrentProvince = provinceDatas.get(newValue).NAME;
            updateCitys();
        }
        if (wheel == cityView) {
            mCurrentCity = cityDatas.get(newValue).NAME;
            updateAreas();
        }
        if (wheel == districtView) {
            mCurrentDistrict = districtDatas.get(newValue).NAME;
        }
    }

    private void updateAreas() {
        int cCurrent = cityView.getCurrentItem();
        if (cityDatas.size() > 0) {
            districtDatas = dataHelper.getDistrictById(db, cityDatas.get(cCurrent).CODE);
        } else {
            districtDatas.clear();
        }
        areaAdapter = new AreaAdapter(this, districtDatas);
        areaAdapter.setTextSize(TEXTSIZE);
        districtView.setViewAdapter(areaAdapter);
        if (districtDatas.size() > 0) {
            mCurrentDistrict = districtDatas.get(0).NAME;
            districtView.setCurrentItem(0);
        } else {
            mCurrentDistrict = "";
        }
    }

    private void updateCitys() {
        int pCurrent = provinceView.getCurrentItem();
        if (provinceDatas.size() > 0) {
            cityDatas = dataHelper.getCityByParentId(db, provinceDatas.get(pCurrent).CODE);
        } else {
            cityDatas.clear();
        }
        citysAdapter = new CitysAdapter(this, cityDatas);
        citysAdapter.setTextSize(TEXTSIZE);
        cityView.setViewAdapter(citysAdapter);
        if (cityDatas.size() > 0) {
            cityView.setCurrentItem(0);
            mCurrentCity = cityDatas.get(0).NAME;
        } else {
            mCurrentCity = "";
        }
        updateAreas();
    }

}
