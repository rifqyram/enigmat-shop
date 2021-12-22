package com.enigma.enigmat_shop.response;

import java.util.List;

public class PageResponse<T> {

    List<T> content;

    private Long count;

    private Integer totalPage;

    private Integer page;

    private Integer size;

    public PageResponse(List<T> content, Long count, Integer totalPage, Integer page, Integer size) {
        this.content = content;
        this.count = count;
        this.totalPage = totalPage;
        this.page = page;
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
