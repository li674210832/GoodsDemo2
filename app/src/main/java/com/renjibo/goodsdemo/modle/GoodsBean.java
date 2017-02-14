package com.renjibo.goodsdemo.modle;

import java.util.List;

/**
 * 任继波
 * Created by Administrator on 2017/2/10.
 */

public class GoodsBean {
    private boolean groupChex;
    private boolean goneView;
    private String name;
    private List<GoodsChild> good;
    private double countMonery;
    public GoodsBean(String name, List<GoodsChild> good, boolean groupChex) {
        this.name = name;
        this.good = good;
        this.groupChex = groupChex;
    }

    public GoodsBean(boolean groupChex, String name, List<GoodsChild> good, double countMonery) {
        this.groupChex = groupChex;
        this.name = name;
        this.good = good;
        this.countMonery = countMonery;
    }

    public GoodsBean(boolean groupChex, boolean goneView, String name, List<GoodsChild> good, double countMonery) {
        this.groupChex = groupChex;
        this.goneView = goneView;
        this.name = name;
        this.good = good;
        this.countMonery = countMonery;
    }

    public boolean isGoneView() {
        return goneView;
    }

    public void setGoneView(boolean goneView) {
        this.goneView = goneView;
    }

    public double getCountMonery() {
        return countMonery;
    }

    public void setCountMonery(double countMonery) {
        this.countMonery = countMonery;
    }

    public boolean isGroupChex() {
        return groupChex;
    }

    public void setGroupChex(boolean groupChex) {
        this.groupChex = groupChex;
    }

    public List<GoodsChild> getGood() {
        return good;
    }

    public void setGood(List<GoodsChild> good) {
        this.good = good;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "groupChex=" + groupChex +
                ", name='" + name + '\'' +
                ", good=" + good +
                '}';
    }
}
