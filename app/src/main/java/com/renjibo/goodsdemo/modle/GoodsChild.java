package com.renjibo.goodsdemo.modle;

/**
 * 任继波
 * Created by Administrator on 2017/2/10.
 */

public class GoodsChild {
    private boolean childChe;
    private String imageUrl;
    private String name;
    private double price;
    private int num;
        public GoodsChild(boolean childChe, String imageUrl, String name, double price, int num) {
            this.childChe = childChe;
            this.imageUrl = imageUrl;
            this.name = name;
            this.price = price;
            this.num = num;
    }

    public boolean isChildChe() {
        return childChe;
    }

    public void setChildChe(boolean childChe) {
        this.childChe = childChe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "GoodsChild{" +
                "childChe=" + childChe +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
