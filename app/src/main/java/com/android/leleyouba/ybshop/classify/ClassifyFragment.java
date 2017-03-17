package com.android.leleyouba.ybshop.classify;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.bean.AdvBean;
import com.android.leleyouba.ybshop.classify.bean.SmallClassifyBean;
import com.android.leleyouba.ybshop.classify.bean.TitleBean;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.common.Visitable;
import com.android.leleyouba.ybshop.mine.activity.CommunicateActivity;
import com.android.leleyouba.ybshop.mine.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import q.rorbin.verticaltablayout.TabAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends Fragment implements View.OnClickListener {
    VerticalTabLayout vertical_tab_classify;
    ArrayList<String> titleList;
    ViewPager vp_classify_show;

    public ClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        setToolBar(view);
        initView(view);
        return view;
    }

    /**
     * 设置导航栏
     *
     * @param view
     */
    private void setToolBar(View view) {
        Toolbar toolBar_classify_show = (Toolbar) view.findViewById(R.id.toolBar_classify_show);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBar_classify_show);
        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
        }
//        SearchView search_classify_show = view.findViewById(R.id.search_classify_show);
        ImageView iv_search_communate = (ImageView) view.findViewById(R.id.iv_search_communate);
//        search_classify_show.setOnClickListener(this);
        iv_search_communate.setOnClickListener(this);

    }
    private void initView(View view) {

        vp_classify_show = (ViewPager) view.findViewById(R.id.vp_classify_show);
        vp_classify_show.setAdapter(new VPPagerAdapter());
        vp_classify_show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //取消viewpager上下滑动切换
                return false;
            }
        });
        initTab(view);
    }

    private void initTab(View view) {
        vertical_tab_classify = (VerticalTabLayout) view.findViewById(R.id.vertical_tab_classify);
        vertical_tab_classify.setupWithViewPager(vp_classify_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_classify_show:
                break;
            case R.id.iv_search_communate:
                startActivity(new Intent(getContext(),CommunicateActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in,R.anim.translation_animation_enter_out);
                break;
            default:
                break;

        }
    }


    private class VPPagerAdapter extends PagerAdapter implements TabAdapter {
        List<String> titles;

        public VPPagerAdapter() {
            titles = new ArrayList<>();
            for (int i = 0; i < 27; i++) {
                titles.add("第" + i + "分类");
            }

//            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
//                    ".NET", "JavaScript", "Swift", "PHP", "Python", "C#", "Groovy", "SQL", "Ruby");
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public int getBadge(int position) {
            return 0;
        }

        @Override
        public QTabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public QTabView.TabTitle getTitle(int position) {
            return new QTabView.TabTitle.Builder(getContext())
                    .setContent(titles.get(position))
                    .setTextColor(Color.RED, Color.DKGRAY)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_classify_item, null);
            bindRecycleViewInClassify(view);
            container.addView(view);
            return view;
        }

        private void bindRecycleViewInClassify(View view) {

            RecyclerView rl_classify_content = (RecyclerView) view.findViewById(R.id.rl_classify_content);

            ArrayList<Visitable> data = new ArrayList<>();
            data.add(new AdvBean("http://pic4.nipic.com/20090919/3372381_123043464790_2.jpg"));
            for (int i = 0; i < 3; i++) {
                data.add(new TitleBean("热门分类", true));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));
                data.add(new SmallClassifyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488365894908&di=aa4826f5af0e8c4512d8cd71666e16ec&imgtype=0&src=http%3A%2F%2Fwww.uml.org.cn%2Fitnews%2Fimages%2F20160421081.jpg", "安卓"));

            }
            final MultiTypeAdapter adapter = new MultiTypeAdapter(data, getContext());

            //设置列数为3列的网格布局
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
            manager.setOrientation(LinearLayoutManager.VERTICAL);

            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return adapter.getSpanSize(position);
                }
            });
            rl_classify_content.setLayoutManager(manager);


            rl_classify_content.setAdapter(adapter);


        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
