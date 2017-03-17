package com.android.leleyouba.ybshop.classify.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.bean.AdvBean;
import com.android.leleyouba.ybshop.classify.bean.SmallClassifyBean;
import com.android.leleyouba.ybshop.classify.bean.TitleBean;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.common.Visitable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyItemFragment extends Fragment {
    RecyclerView rl_classify_content;

    public ClassifyItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classify_item, container, false);
//        initView(view);
        return view;
    }

    private void initView(View view) {
        rl_classify_content = (RecyclerView) view.findViewById(R.id.rl_classify_content);
        //设置列数为3列的网格布局
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 0;
            }
        });
        rl_classify_content.setLayoutManager(manager);

        ArrayList<SmallClassifyBean> lists = new ArrayList<>();
        for (int i = 0; i < 27; i++) {

            lists.add(new SmallClassifyBean("", ""));
        }
        ArrayList<Visitable> data = new ArrayList<>();
        data.add(new AdvBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg"));
        for (int i = 0; i < 3; i++) {
            data.add(new TitleBean("热门分类",true));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
            data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg","安卓"));
        }
        MultiTypeAdapter adapter = new MultiTypeAdapter(data,getContext());
        rl_classify_content.setAdapter(adapter);
    }

}
