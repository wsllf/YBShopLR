package com.android.leleyouba.ybshop.common;

import android.view.View;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.adapter.AdvBeanViewHolder;
import com.android.leleyouba.ybshop.classify.adapter.SmallClassifyBeanViewHolder;
import com.android.leleyouba.ybshop.classify.adapter.TitleBeanViewHolder;
import com.android.leleyouba.ybshop.classify.bean.AdvBean;
import com.android.leleyouba.ybshop.classify.bean.SmallClassifyBean;
import com.android.leleyouba.ybshop.classify.bean.TitleBean;
import com.android.leleyouba.ybshop.find.bean.FindBean;
import com.android.leleyouba.ybshop.find.bean.FindClassifyBean;
import com.android.leleyouba.ybshop.find.adapter.FindClassifyViewHodler;
import com.android.leleyouba.ybshop.find.bean.FindRightImgItemBean;
import com.android.leleyouba.ybshop.find.adapter.FindRightImgItemViewHolder;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgBean;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgItemBean;
import com.android.leleyouba.ybshop.find.adapter.FindThreeImgItemViewHolder;
import com.android.leleyouba.ybshop.find.adapter.FindThreeImgViewHolder;
import com.android.leleyouba.ybshop.shoppingtrolley.adapter.RecommendWaresViewHolder;
import com.android.leleyouba.ybshop.shoppingtrolley.adapter.ShoppingTrolleyWaresViewHolder;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.RecommendWaresModel;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShoppingTrolleyWaresModel;

/**
 * Created by xalo on 2017/2/28.
 */

public class TypeFactoryForList implements TypeFactory {

    private final int TYPE_RESOURCE_RECOMMEND_WARES = R.layout.shopping_recommend_wares;//推荐
    private final int TYPE_RESOURCE_SHOPPING_TROLLEY_WARES = R.layout.shopping_trolley_wares;//购物车
    private final int TYPE_RESOURCE_CLASSIFY_ADV = R.layout.classify_adv;//广告
    private final int TYPE_RESOURCE_CLASSIFY_TITLE = R.layout.classify_title;//分类标题
    private final int TYPE_RESOURCE_CLASSIFY_SMALL = R.layout.classify_small;//商品分类展示

    private final int TYPE_FIND_CLASSIFY = R.layout.find_classify;//发现界面标题
    private final int TYPE_FIND_CONTENT = R.layout.find_three_img;//
    private final int TYPE_FIND_RIGHTIMGITEM = R.layout.find_right_img_item;//
    private final int TYPE_FIND_THREEIMGITEM = R.layout.find_three_img_item;//

    @Override
    public int type(FindClassifyBean findClassifyBean) {
        return TYPE_FIND_CLASSIFY;
    }

    @Override
    public int type(FindBean findBean) {
        return TYPE_FIND_CLASSIFY;
    }

    @Override
    public int type(FindThreeImgBean findThreeImgBean) {
        return TYPE_FIND_CONTENT;
    }

    @Override
    public int type(FindRightImgItemBean findRightImgItemBean) {
        return TYPE_FIND_RIGHTIMGITEM;
    }

    @Override
    public int type(FindThreeImgItemBean findThreeImgItemBean) {
        return TYPE_FIND_THREEIMGITEM;
    }


    @Override
    public int type(RecommendWaresModel recommendWaresModel) {
        return TYPE_RESOURCE_RECOMMEND_WARES;
    }

    @Override
    public int type(ShoppingTrolleyWaresModel shoppingTrolleyWaresModel) {
        return TYPE_RESOURCE_SHOPPING_TROLLEY_WARES;
    }

    @Override
    public int type(SmallClassifyBean smallClassifyBean) {
        return TYPE_RESOURCE_CLASSIFY_SMALL;
    }

    @Override
    public int type(AdvBean advBean) {
        return TYPE_RESOURCE_CLASSIFY_ADV;
    }

    @Override
    public int type(TitleBean titleBean) {
        return TYPE_RESOURCE_CLASSIFY_TITLE;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {

        if (TYPE_RESOURCE_RECOMMEND_WARES == type) {
            return new RecommendWaresViewHolder(itemView);
        } else if (TYPE_RESOURCE_SHOPPING_TROLLEY_WARES == type) {
            return new ShoppingTrolleyWaresViewHolder(itemView);
        } else if (TYPE_RESOURCE_CLASSIFY_ADV == type) {
            return new AdvBeanViewHolder(itemView);
        } else if (TYPE_RESOURCE_CLASSIFY_TITLE == type) {
            return new TitleBeanViewHolder(itemView);
        } else if (TYPE_RESOURCE_CLASSIFY_SMALL == type) {
            return new SmallClassifyBeanViewHolder(itemView);
        }else if (TYPE_FIND_CLASSIFY == type) {
            return new FindClassifyViewHodler(itemView);
        }else if (TYPE_FIND_CONTENT == type){
            return new FindThreeImgViewHolder(itemView);
        }else if (TYPE_FIND_RIGHTIMGITEM == type){
            return new FindRightImgItemViewHolder(itemView);
        }else if (TYPE_FIND_THREEIMGITEM == type){
            return new FindThreeImgItemViewHolder(itemView);
        }

        return null;
    }
}
