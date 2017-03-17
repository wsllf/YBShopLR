package com.android.leleyouba.ybshop.common;

import android.view.View;

import com.android.leleyouba.ybshop.classify.bean.AdvBean;
import com.android.leleyouba.ybshop.classify.bean.SmallClassifyBean;
import com.android.leleyouba.ybshop.classify.bean.TitleBean;
import com.android.leleyouba.ybshop.find.bean.FindBean;
import com.android.leleyouba.ybshop.find.bean.FindClassifyBean;
import com.android.leleyouba.ybshop.find.bean.FindRightImgItemBean;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgBean;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgItemBean;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.RecommendWaresModel;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShoppingTrolleyWaresModel;

/**
 *
 */

public interface TypeFactory {

    int type(RecommendWaresModel recommendWaresModel);

    int type(ShoppingTrolleyWaresModel shoppingTrolleyWaresModel);
    int type(SmallClassifyBean smallClassifyBean);
    int type(AdvBean advBean);
    int type(TitleBean titleBean);

    int type(FindClassifyBean findClassifyBean);
    int type(FindBean findBean);



    int type(FindThreeImgBean findThreeImgBean);

    int type(FindRightImgItemBean findRightImgItemBean);

    int type(FindThreeImgItemBean findThreeImgItemBean);

    BaseViewHolder createViewHolder(int type, View itemView);
}
