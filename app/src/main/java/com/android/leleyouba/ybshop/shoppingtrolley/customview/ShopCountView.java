package com.android.leleyouba.ybshop.shoppingtrolley.customview;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;

/**
 * Created by xalo on 2017/3/2.
 */

public class ShopCountView extends LinearLayout {

   private TextView deleteTv,addTv;
   private EditText countEdt;

    public ShopCountView(Context context) {
       super(context,null);

    }

    public ShopCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.shop_count_layout,this);
        deleteTv = (TextView) view.findViewById(R.id.left_tv);
        addTv = (TextView) view.findViewById(R.id.right_tv);
        countEdt = (EditText) view.findViewById(R.id.count_edt);

        deleteTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountChangeListenter != null){
                    mCountChangeListenter.deleteTvClickListenter(v);
                }
            }
        });
        addTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountChangeListenter != null){
                    mCountChangeListenter.addTvClickListenter(v);
                }
            }
        });
        countEdt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountChangeListenter != null){
                    mCountChangeListenter.countEdtClickListenter(v);
                }
            }
        });

    }

    public ShopCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCount(int count){
        if(count <1){
            return;
        }
        this.countEdt.setText(count+"");
    }

    public int getCount(){

        return Integer.parseInt(countEdt.getText().toString().trim());
    }


    public interface CountChangeListenter{

        void deleteTvClickListenter(View view);
        void addTvClickListenter(View view);
        void countEdtClickListenter(View view);
    }
    private CountChangeListenter mCountChangeListenter;

    public CountChangeListenter getCountChangeListenter() {
        return mCountChangeListenter;
    }

    public void setCountChangeListenter(CountChangeListenter countChangeListenter) {
        mCountChangeListenter = countChangeListenter;
    }
}
