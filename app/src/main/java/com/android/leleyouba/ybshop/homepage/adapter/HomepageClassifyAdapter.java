package com.android.leleyouba.ybshop.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.homepage.bean.HomepageBaseClassifyBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageClassifyBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageTitleBean;
import com.android.leleyouba.ybshop.homepage.util.PicsView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xalo on 2017/3/3.
 */

public class HomepageClassifyAdapter extends RecyclerView.Adapter<HomepageClassifyAdapter.ViewHolder> {

    private List<HomepageClassifyBean> datas;//数据集合
    Context context;


    public HomepageClassifyAdapter(List<HomepageClassifyBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public HomepageClassifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_classify_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomepageClassifyAdapter.ViewHolder holder, int position) {
        HomepageClassifyBean classifyBean = datas.get(position);

        //设置轮播图
        setItemCarouselFigure(holder, classifyBean);
        //设置标题
        setItemTitle(holder, classifyBean);

        //设置上四个
        setItemTop(holder, classifyBean);

        //设置下四个
        setItemBottom(holder, classifyBean);

    }

    /**
     * 设置轮播图
     *
     * @param holder
     */
    private void setItemCarouselFigure(ViewHolder holder, HomepageClassifyBean classifyBean) {
        List<String> listId = classifyBean.getCarouselImgs();
        ArrayList<ImageView> imgList = new ArrayList<>();
        for (int i = 0; i < listId.size(); i++) {
            ImageView img = new ImageView(context);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(listId.get(i)).into(img);
            imgList.add(img);
        }
        holder.pic_homepage_item.setTilesAndImages(null, imgList);
        holder.pic_homepage_item.setTvTitleVisibility(View.GONE);
        // 设置点击事件    别人封装的轮播图 如果不设置点击事件 点击的时候会crash
        holder.pic_homepage_item.setOnLunBoClickListener(new PicsView.OnLunBoClickListener() {

            @Override
            public void clickLunbo(int position) {
                System.out.println("点击有效");
                Toast.makeText(context, "点击有效，位置为：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 当Item出现是调用此方法
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {

        holder.pic_homepage_item.startAutoScroll();
    }

    /**
     * 当Item被回收的时候调用此方法
     *
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {

        holder.pic_homepage_item.stopAutoScroll();
    }

    /**
     * 设置标题
     *
     * @param holder
     */
    private void setItemTitle(ViewHolder holder, HomepageClassifyBean classifyBean) {
        HomepageTitleBean titleBean = classifyBean.getClassifyTitles();
        holder.tv_homepage_item_title.setText(titleBean.getContentStr());
        holder.tv_homepage_item_title.setBackgroundResource(titleBean.getBackgroundId());
    }

    /**
     * 设置上四个
     *
     * @param holder
     */
    private void setItemTop(ViewHolder holder, HomepageClassifyBean classifyBean) {
        ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans = classifyBean.getTopBaseClassifyBeans();
        HomepageBaseClassifyBean topBaseClassifyBean_1 = topBaseClassifyBeans.get(0);
        holder.tv_homepage_classify_top_title_one.setText(topBaseClassifyBean_1.getTitleStr());
        holder.tv_homepage_classify_top_content_one.setText(topBaseClassifyBean_1.getContentStr());
        holder.tv_homepage_classify_top_discount_one.setText(topBaseClassifyBean_1.getDiscountStr());
        holder.iv_homepage_top_find_one.setBackgroundResource(topBaseClassifyBean_1.getImgStr());

        HomepageBaseClassifyBean topBaseClassifyBean_2 = topBaseClassifyBeans.get(1);
        holder.tv_homepage_classify_top_title_two.setText(topBaseClassifyBean_2.getTitleStr());
        holder.tv_homepage_classify_top_content_two.setText(topBaseClassifyBean_2.getContentStr());
        holder.tv_homepage_classify_top_discount_two.setText(topBaseClassifyBean_2.getDiscountStr());
        holder.iv_homepage_top_find_two.setBackgroundResource(topBaseClassifyBean_2.getImgStr());

        HomepageBaseClassifyBean topBaseClassifyBean_3 = topBaseClassifyBeans.get(2);
        holder.tv_homepage_classify_top_title_three.setText(topBaseClassifyBean_3.getTitleStr());
        holder.tv_homepage_classify_top_content_three.setText(topBaseClassifyBean_3.getContentStr());
        holder.tv_homepage_classify_top_discount_three.setText(topBaseClassifyBean_3.getDiscountStr());
        holder.iv_homepage_top_find_three.setBackgroundResource(topBaseClassifyBean_3.getImgStr());

        HomepageBaseClassifyBean topBaseClassifyBean_4 = topBaseClassifyBeans.get(3);
        holder.tv_homepage_classify_top_title_four.setText(topBaseClassifyBean_4.getTitleStr());
        holder.tv_homepage_classify_top_content_four.setText(topBaseClassifyBean_4.getContentStr());
        holder.tv_homepage_classify_top_discount_four.setText(topBaseClassifyBean_4.getDiscountStr());
        holder.iv_homepage_top_find_four.setBackgroundResource(topBaseClassifyBean_4.getImgStr());


    }

    /**
     * 设置下四个
     *
     * @param holder
     */
    private void setItemBottom(ViewHolder holder, HomepageClassifyBean classifyBean) {
        ArrayList<HomepageBaseClassifyBean> bottomBaseClassifyBeans = classifyBean.getBottomBaseClassifyBeans();

        HomepageBaseClassifyBean bottomBaseClassifyBean_1 = bottomBaseClassifyBeans.get(0);
        holder.tv_homepage_classify_bottom_title_one.setText(bottomBaseClassifyBean_1.getTitleStr());
        holder.tv_homepage_classify_bottom_content_one.setText(bottomBaseClassifyBean_1.getContentStr());
        holder.iv_homepage_classify_bottom_img_one.setBackgroundResource(bottomBaseClassifyBean_1.getImgStr());

        HomepageBaseClassifyBean bottomBaseClassifyBean_2 = bottomBaseClassifyBeans.get(1);
        holder.tv_homepage_classify_bottom_title_two.setText(bottomBaseClassifyBean_2.getTitleStr());
        holder.tv_homepage_classify_bottom_content_two.setText(bottomBaseClassifyBean_2.getContentStr());
        holder.iv_homepage_classify_bottom_img_two.setBackgroundResource(bottomBaseClassifyBean_2.getImgStr());

        HomepageBaseClassifyBean bottomBaseClassifyBean_3 = bottomBaseClassifyBeans.get(2);
        holder.tv_homepage_classify_bottom_title_three.setText(bottomBaseClassifyBean_3.getTitleStr());
        holder.tv_homepage_classify_bottom_content_three.setText(bottomBaseClassifyBean_3.getContentStr());
        holder.iv_homepage_classify_bottom_img_three.setBackgroundResource(bottomBaseClassifyBean_3.getImgStr());

        HomepageBaseClassifyBean bottomBaseClassifyBean_4 = bottomBaseClassifyBeans.get(3);
        holder.tv_homepage_classify_bottom_title_four.setText(bottomBaseClassifyBean_4.getTitleStr());
        holder.tv_homepage_classify_bottom_content_four.setText(bottomBaseClassifyBean_4.getContentStr());
        holder.iv_homepage_classify_bottom_img_four.setBackgroundResource(bottomBaseClassifyBean_4.getImgStr());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        PicsView pic_homepage_item;
        TextView tv_homepage_item_title;
        TextView tv_homepage_classify_top_title_one, tv_homepage_classify_top_title_two, tv_homepage_classify_top_title_three, tv_homepage_classify_top_title_four;
        TextView tv_homepage_classify_top_content_one, tv_homepage_classify_top_content_two, tv_homepage_classify_top_content_three, tv_homepage_classify_top_content_four;
        TextView tv_homepage_classify_top_discount_one, tv_homepage_classify_top_discount_two, tv_homepage_classify_top_discount_three, tv_homepage_classify_top_discount_four;
        ImageView iv_homepage_top_find_one, iv_homepage_top_find_two, iv_homepage_top_find_three, iv_homepage_top_find_four;
        TextView tv_homepage_classify_bottom_title_one, tv_homepage_classify_bottom_title_two, tv_homepage_classify_bottom_title_three, tv_homepage_classify_bottom_title_four;
        TextView tv_homepage_classify_bottom_content_one, tv_homepage_classify_bottom_content_two, tv_homepage_classify_bottom_content_three, tv_homepage_classify_bottom_content_four;
        ImageView iv_homepage_classify_bottom_img_one, iv_homepage_classify_bottom_img_two, iv_homepage_classify_bottom_img_three, iv_homepage_classify_bottom_img_four;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_homepage_item = (PicsView) itemView.findViewById(R.id.pic_homepage_item);
            tv_homepage_item_title = (TextView) itemView.findViewById(R.id.tv_homepage_item_title);
            RelativeLayout rl_homepage_classify_one = (RelativeLayout) itemView.findViewById(R.id.rl_homepage_classify_one);
            RelativeLayout rl_homepage_classify_two = (RelativeLayout) itemView.findViewById(R.id.rl_homepage_classify_two);
            RelativeLayout rl_homepage_classify_three = (RelativeLayout) itemView.findViewById(R.id.rl_homepage_classify_three);
            RelativeLayout rl_homepage_classify_four = (RelativeLayout) itemView.findViewById(R.id.rl_homepage_classify_four);
            LinearLayout ll_homepage_classify_one = (LinearLayout) itemView.findViewById(R.id.ll_homepage_classify_one);
            LinearLayout ll_homepage_classify_two = (LinearLayout) itemView.findViewById(R.id.ll_homepage_classify_two);
            LinearLayout ll_homepage_classify_three = (LinearLayout) itemView.findViewById(R.id.ll_homepage_classify_three);
            LinearLayout ll_homepage_classify_four = (LinearLayout) itemView.findViewById(R.id.ll_homepage_classify_four);

            rl_homepage_classify_one.setOnClickListener(this);
            rl_homepage_classify_two.setOnClickListener(this);
            rl_homepage_classify_three.setOnClickListener(this);
            rl_homepage_classify_four.setOnClickListener(this);
            ll_homepage_classify_one.setOnClickListener(this);
            ll_homepage_classify_two.setOnClickListener(this);
            ll_homepage_classify_three.setOnClickListener(this);
            ll_homepage_classify_four.setOnClickListener(this);


            tv_homepage_classify_top_title_one = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_title_one);
            tv_homepage_classify_top_title_two = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_title_two);
            tv_homepage_classify_top_title_three = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_title_three);
            tv_homepage_classify_top_title_four = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_title_four);

            tv_homepage_classify_top_content_one = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_content_one);
            tv_homepage_classify_top_content_two = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_content_two);
            tv_homepage_classify_top_content_three = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_content_three);
            tv_homepage_classify_top_content_four = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_content_four);

            tv_homepage_classify_top_discount_one = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_discount_one);
            tv_homepage_classify_top_discount_two = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_discount_two);
            tv_homepage_classify_top_discount_three = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_discount_three);
            tv_homepage_classify_top_discount_four = (TextView) itemView.findViewById(R.id.tv_homepage_classify_top_discount_four);

            iv_homepage_top_find_one = (ImageView) itemView.findViewById(R.id.iv_homepage_top_find_one);
            iv_homepage_top_find_two = (ImageView) itemView.findViewById(R.id.iv_homepage_top_find_two);
            iv_homepage_top_find_three = (ImageView) itemView.findViewById(R.id.iv_homepage_top_find_three);
            iv_homepage_top_find_four = (ImageView) itemView.findViewById(R.id.iv_homepage_top_find_four);

            tv_homepage_classify_bottom_title_one = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_title_one);
            tv_homepage_classify_bottom_title_two = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_title_two);
            tv_homepage_classify_bottom_title_three = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_title_three);
            tv_homepage_classify_bottom_title_four = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_title_four);

            tv_homepage_classify_bottom_content_one = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_content_one);
            tv_homepage_classify_bottom_content_two = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_content_two);
            tv_homepage_classify_bottom_content_three = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_content_three);
            tv_homepage_classify_bottom_content_four = (TextView) itemView.findViewById(R.id.tv_homepage_classify_bottom_content_four);

            iv_homepage_classify_bottom_img_one = (ImageView) itemView.findViewById(R.id.iv_homepage_classify_bottom_img_one);
            iv_homepage_classify_bottom_img_two = (ImageView) itemView.findViewById(R.id.iv_homepage_classify_bottom_img_two);
            iv_homepage_classify_bottom_img_three = (ImageView) itemView.findViewById(R.id.iv_homepage_classify_bottom_img_three);
            iv_homepage_classify_bottom_img_four = (ImageView) itemView.findViewById(R.id.iv_homepage_classify_bottom_img_four);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_homepage_classify_one:
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_homepage_classify_two:
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_homepage_classify_three:
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_homepage_classify_four:
                    Toast.makeText(context, "4", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_homepage_classify_one:
                    Toast.makeText(context, "5", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_homepage_classify_two:
                    Toast.makeText(context, "6", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_homepage_classify_three:
                    Toast.makeText(context, "7", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_homepage_classify_four:
                    Toast.makeText(context, "8", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
