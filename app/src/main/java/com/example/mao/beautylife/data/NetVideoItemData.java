package com.example.mao.beautylife.data;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Tecmry on 2018/3/26.
 */

public class NetVideoItemData {

    private List<ProductsBean> products;

    public static NetVideoItemData objectFromData(String str) {

        return new Gson().fromJson(str, NetVideoItemData.class);
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * id : 1
         * title : Dior迪奥魅惑唇膏玩色狂想系列
         * colorname : ---469 绮遇---575 梦幻仙境---343 春日舞会---476 Plaza---479 Holiday---536 Lucky---553 Princess Extreme---639 Riviera---643 Diablotine Extreme---667 Avenue---
         * colornameList : ["","469 绮遇","575 梦幻仙境","343 春日舞会","476 Plaza","479 Holiday","536 Lucky","553 Princess Extreme","639 Riviera","643 Diablotine Extreme","667 Avenue"]
         * price : ￥320.00
         * imgurl : https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/390/100/0/v6_packshots_pdg%252FPDG_Y0028623.jpg
         * colorurl : https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901210270_cs.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901212762_cs.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155168_cs.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901087247.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901083034.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901087230.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155205_cs.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901076623.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155212_cs.png---https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901076593.png
         * colorurlList : ["https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901210270_cs.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901212762_cs.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155168_cs.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901087247.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901083034.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901087230.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155205_cs.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901076623.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252F3348901155212_cs.png","https://www.dior.cn/beauty/version-5.1432748111912/resize-image/ep/0/0/90/0/swatches%252Fcs_3348901076593.png"]
         * likeTimes : 0
         * saletime : 2018-03-07
         * brand : 迪奥
         */

        private int id;
        private String title;
        private String colorname;
        private String price;
        private String imgurl;
        private String colorurl;
        private int likeTimes;
        private String saletime;
        private String brand;
        private List<String> colornameList;
        private List<String> colorurlList;

        public static ProductsBean objectFromData(String str) {

            return new Gson().fromJson(str, ProductsBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getColorname() {
            return colorname;
        }

        public void setColorname(String colorname) {
            this.colorname = colorname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getColorurl() {
            return colorurl;
        }

        public void setColorurl(String colorurl) {
            this.colorurl = colorurl;
        }

        public int getLikeTimes() {
            return likeTimes;
        }

        public void setLikeTimes(int likeTimes) {
            this.likeTimes = likeTimes;
        }

        public String getSaletime() {
            return saletime;
        }

        public void setSaletime(String saletime) {
            this.saletime = saletime;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public List<String> getColornameList() {
            return colornameList;
        }

        public void setColornameList(List<String> colornameList) {
            this.colornameList = colornameList;
        }

        public List<String> getColorurlList() {
            return colorurlList;
        }

        public void setColorurlList(List<String> colorurlList) {
            this.colorurlList = colorurlList;
        }
    }
}
