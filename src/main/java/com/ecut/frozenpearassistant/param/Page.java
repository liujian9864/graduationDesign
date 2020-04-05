package com.ecut.frozenpearassistant.param;

/**
 * Created by liujian on 2020/2/29.
 */
public class Page {

    private int pageSize;

    private int currentPage;

    private int totalPage;

    private int totalCount;

    private String searchKey;

    private String productType;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public int getTotalPage() {
        if(totalCount>pageSize){
            if(totalCount % pageSize == 0){
                totalPage = totalCount / pageSize;
            }else{
                totalPage = totalCount / pageSize +1;
            }

        }else{
            totalPage=1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public Page() {
    }
}
