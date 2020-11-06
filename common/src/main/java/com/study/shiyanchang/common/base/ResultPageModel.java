package com.study.shiyanchang.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultPageModel<T> implements Serializable {

    private List<T> data;//数据列表

    private Integer pageSize;// 每页显示记录数

    private Integer total;//总条数

    private Integer pages;// 总页数

    private Integer current;// 当前页

    public ResultPageModel(List<T> records, Integer current, Integer size, Integer total){
        this.data=records;
        this.current=current;
        this.pageSize=size;
        this.total=total;
        if (size != null && total != null) {
            pages = total % size == 0 ? (total / size) : (total / size) + 1;
        }
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setTotal(Integer total) {
        this.total = total;
        setTotalPageAndIsLastPage(pages, total);
    }

    public void setPages(Integer pages) {
        this.pages = pages;
        setTotalPageAndIsLastPage(pages, total);
    }

    private void setTotalPageAndIsLastPage(Integer size, Integer total) {
        if (size != null && total != null) {
            pages = total % size == 0 ? (total / size) : (total / size) + 1;
        }
    }

}
