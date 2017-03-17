package com.android.leleyouba.ybshop.homepage.bean;


/**
 *
 */

public class HomepageBaseClassifyBean {
    private String titleStr;//标题
    private String contentStr;//内容
    private String discountStr;//优惠
    private int imgStr;//图片

    /**
     * 外部类提供一个私有构造函数供内部类调用，在该构造函数中完成成员变量的赋值，取值为Builder对象中对应的值
     *
     * @param builder
     */
    private HomepageBaseClassifyBean(Builder builder) {
        this.titleStr = builder.titleStr;
        this.contentStr = builder.contentStr;
        this.discountStr = builder.discountStr;
        this.imgStr = builder.imgStr;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getDiscountStr() {
        return discountStr;
    }

    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    public int getImgStr() {
        return imgStr;
    }

    public void setImgStr(int imgStr) {
        this.imgStr = imgStr;
    }

    /**
     * 定义一个静态内部类Builder，内部的成员变量和外部类一样
     */
    public static class Builder {
        private String titleStr;//标题
        private String contentStr;//内容
        private String discountStr;//优惠
        private int imgStr;//图片
        /**
         * Builder类通过一系列的方法用于成员变量的赋值，并返回当前对象本身（this）
         * @param titleStr
         * @return
         */
        public Builder titleStr(String titleStr) {
            this.titleStr = titleStr;
            return this;
        }

        public Builder contentStr(String contentStr) {
            this.contentStr = contentStr;
            return this;
        }

        public Builder discountStr(String discountStr) {
            this.discountStr = discountStr;
            return this;
        }

        public Builder imgStr(int imgStr) {
            this.imgStr = imgStr;
            return this;
        }

        public HomepageBaseClassifyBean build() {
            return new HomepageBaseClassifyBean(this);
        }
    }
}
