package com.ecut.frozenpearassistant.orm.entity;


import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */
public class PageProduct {
    private List<ProductEntity> products;
    private  int totalPage;
    private  int currentPage;

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageProduct() {
    }

    @Override
    public String toString() {
        return "PageLaw{" +
                "products=" + products +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
