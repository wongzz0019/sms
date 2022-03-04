package com.hzz.util;

import org.springframework.stereotype.Component;


@Component
public class PageSupport {
    //当前页面 来自于用户输入
    private int currentPageNo = 1;
    //总数量(表)
    private int totalCount = 0;
    //页面容量
    private int pageSize = 0;
    //总共显示的页数 为总表数量/单页容量 +1
    private int totalPageCount =1;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if(currentPageNo>0){
            this.currentPageNo = currentPageNo;
        }
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
            this.setTotalPageCountByRs();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize>0){
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public void setTotalPageCountByRs(){
        if(this.totalCount % this.pageSize == 0){
            this.totalPageCount = this.totalCount / this.pageSize;
        }else if(this.totalCount % this.pageSize > 0){
            this.totalPageCount = this.totalCount / this.pageSize +1;
        }else{
            this.totalPageCount = 0;
        }
    }
}
