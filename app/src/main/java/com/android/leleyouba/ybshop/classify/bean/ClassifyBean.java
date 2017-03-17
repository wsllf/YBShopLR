package com.android.leleyouba.ybshop.classify.bean;

import java.util.List;

/**
 * Created by xalo on 2017/3/14.
 */

public class ClassifyBean {

    private List<服装Bean> 服装;

    public List<服装Bean> get服装() {
        return 服装;
    }

    public void set服装(List<服装Bean> 服装) {
        this.服装 = 服装;
    }

    public static class 服装Bean {
        /**
         * name2 : 修身型
         * content : [{"name":"Vivo Y67","id":"1e970e7a-9b94-4eb9-8514-29a4d94aeb30","pic":3},{"name":"Vivo Y67","id":"1e970e7a-9b94-4eb9-8514-29a4d94aeb30","pic":3},{"name":"Vivo Y67","id":"1e970e7a-9b94-4eb9-8514-29a4d94aeb30","pic":3}]
         */

        private String name2;
        private List<ContentBean> content;

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * name : Vivo Y67
             * id : 1e970e7a-9b94-4eb9-8514-29a4d94aeb30
             * pic : 3
             */

            private String name;
            private String id;
            private int pic;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getPic() {
                return pic;
            }

            public void setPic(int pic) {
                this.pic = pic;
            }
        }
    }
}
