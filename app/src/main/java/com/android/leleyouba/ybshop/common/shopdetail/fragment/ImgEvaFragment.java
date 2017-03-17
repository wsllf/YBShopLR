package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.adapter.EvalutationAdapter;
import com.android.leleyouba.ybshop.common.shopdetail.adapter.EvalutationImageAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgEvaFragment extends Fragment {

    private static final String TAG = "ImgEvaFragment";
    public static final String ARGUMENT_KEY = "ImgEvaFragment";


    RecyclerView recycleview_img_evalutation;


    public ImgEvaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_img_eva, container, false);

        getSendData();

        initView(view);

        initData();

        return view;
    }



    private void initView(View view) {

        recycleview_img_evalutation = (RecyclerView) view.findViewById(R.id.recycleview_img_evalutation);
       recycleview_img_evalutation.addItemDecoration(new RecyclerView.ItemDecoration() {

           @Override
           public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
               outRect.set(15,15,15,15);
           }
       });

        GridLayoutManager manager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        recycleview_img_evalutation.setLayoutManager(manager);

    }

    private void initData() {


        List<String> img = new ArrayList<>();

        for (int i = 0; i <30 ; i++) {

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
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
                img.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
                img.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
                img.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
        }

        EvalutationImageAdapter adapter = new EvalutationImageAdapter(img);
        recycleview_img_evalutation.setAdapter(adapter);

    }

    private void getSendData() {

        Bundle bundle = getArguments();

        if (bundle != null){
            String url =   bundle.getString(ARGUMENT_KEY);
            Log.d(TAG, "getSendData: "+url);
        }
    }

}
