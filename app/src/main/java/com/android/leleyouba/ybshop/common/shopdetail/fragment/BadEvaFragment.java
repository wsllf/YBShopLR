package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.adapter.EvalutationAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BadEvaFragment extends Fragment {


    private static final String TAG = "BadEvaFragment";
    public static final String ARGUMENT_KEY = "BadEvaFragment";

    RecyclerView recycleview_bad_evalutation;

    public BadEvaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bad_eva, container, false);

        getSendData();

        initView(view);

        initData();
        // Inflate the layout for this fragment
        return view;
    }



    private void initView(View view) {
        recycleview_bad_evalutation = (RecyclerView) view.findViewById(R.id.recycleview_bad_evalutation);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycleview_bad_evalutation.setLayoutManager(manager);

        recycleview_bad_evalutation.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(5,5,5,5);
            }
        });

    }

    private void getSendData() {

        Bundle bundle = getArguments();

        if (bundle != null){
            String url =   bundle.getString(ARGUMENT_KEY);
            Log.d(TAG, "getSendData: "+url);
        }







    }

    private void initData() {
        List<Map<String,String>> list = new ArrayList<>();

        List<List<String>> imgList = new ArrayList<>();

        for (int i = 0; i <30 ; i++) {


            List<String> img = new ArrayList<>();

            if (i%4 == 0){
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
            }else if(i%4 == 1){
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
            }else if (i%4 == 2){

                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
            }else {

            }

            imgList.add(img);

            Map<String,String> map = new HashMap<>();
            map.put("nickname","昵称"+i);
            map.put("eva_date",new Date().toString());
            map.put("content","是的发送到快来发货速度快解放后收到了看风景圣诞快乐发送登录的沙发斯蒂芬是的发送到是打发士大夫撒打发第三方过得好公司快递费激光焊接上岛咖啡光电开关");
            map.put("type","华硕电脑-黑色");
            map.put("rate",2+"");
            map.put("buy_date",new Date().toString());
            map.put("encouge_count",i%9+"");
            map.put("reply_count",i%19+"");
            list.add(map);
        }

        EvalutationAdapter adapter = new EvalutationAdapter(list,imgList);
        recycleview_bad_evalutation.setAdapter(adapter);

    }

}
