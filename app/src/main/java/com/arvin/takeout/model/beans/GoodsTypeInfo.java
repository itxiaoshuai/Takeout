package com.arvin.takeout.model.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 一个商家会有多个商品类别.
 */
public class GoodsTypeInfo implements Serializable {
    private int id;
    private String info;
    private List<GoodsInfo> list;
    private String name;


    private int count;//同一种类型选中的数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GoodsTypeInfo() {
    }

    public GoodsTypeInfo(int id, String info, List<GoodsInfo> list, String name) {
        this.id = id;
        this.info = info;
        this.list = list;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<GoodsInfo> getList() {
        return list;
    }

    public void setList(List<GoodsInfo> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
