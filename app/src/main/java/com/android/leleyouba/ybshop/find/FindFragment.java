package com.android.leleyouba.ybshop.find;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.common.Visitable;
import com.android.leleyouba.ybshop.find.bean.FindBean;
import com.android.leleyouba.ybshop.find.bean.FindClassifyBean;
import com.android.leleyouba.ybshop.find.bean.FindRightImgItemBean;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgBean;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgItemBean;
import com.android.leleyouba.ybshop.find.util.MyDecoration;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        RecyclerView recycle_find_show = (RecyclerView) view.findViewById(R.id.recycle_find_show);
        ArrayList<Visitable> datas = new ArrayList<>();
        ArrayList<FindClassifyBean> findClassifyBeen = new ArrayList<>();
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "直播"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "社区"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "优选清单"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "好东西"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "品牌头条"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "送礼神器"));
        findClassifyBeen.add(new FindClassifyBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "拍照购"));
        FindBean findBean = new FindBean();
        findBean.setFindClassifyBeanDatas(findClassifyBeen);
        datas.add(findBean);

        FindThreeImgBean findThreeImgBean = new FindThreeImgBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg", "武装牙齿", "颜值速报", "超级品类日", "从此拒绝大黄牙", "王子文大玩抹胸", "有钱人必入电器");
        datas.add(findThreeImgBean);
        for (int i = 0; i < 9; i++) {
            FindRightImgItemBean rightImgItemBean = new FindRightImgItemBean();
            rightImgItemBean.setTitleStr("除了巨星签名鞋，还有这些经典系列的球鞋（续）");
            rightImgItemBean.setContentStr("在上一期，和大家一起回顾了Nike经典的Flight系列，这一期我们接着来说");
            rightImgItemBean.setAuthorStr("虎扑识货");
            rightImgItemBean.setDateStr("03月05日");
            rightImgItemBean.setBrowseStr("3022");
            rightImgItemBean.setLeftCirImg("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            rightImgItemBean.setImg("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            rightImgItemBean.setMedia(true);
            if (i == 2 || i == 0) {
                rightImgItemBean.setMedia(false);
            }

            FindThreeImgItemBean threeImgItemBean = new FindThreeImgItemBean();
            threeImgItemBean.setTitleStr("踏青,怎能少了自拍杆");
            threeImgItemBean.setContentStr("春天到了,大地万物复苏,周末踏青成了上班族丰富业余生活的主要活动");
            threeImgItemBean.setImgOne("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            threeImgItemBean.setImgTwo("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            threeImgItemBean.setImgThree("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            threeImgItemBean.setAuthorStr("虎扑识货");
            threeImgItemBean.setLeftCivImg("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg");
            threeImgItemBean.setBrowseStr("989799899");
            if (i == 1 || i == 5 || i == 8) {
                datas.add(threeImgItemBean);
            } else {
                datas.add(rightImgItemBean);
            }

        }

        Log.d("datas", datas + "");
        recycle_find_show.addItemDecoration(new MyDecoration(getContext(), MyDecoration.VERTICAL_LIST));
        recycle_find_show.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MultiTypeAdapter adapter = new MultiTypeAdapter(datas, getContext());
        recycle_find_show.setAdapter(adapter);

    }
}
