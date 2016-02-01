package com.wengyingjian.kylin.common.model.query;

import java.io.Serializable;

/**
 * 分页查询父类,查询类可继承此类<br/>
 * 是否启用分页查询取决于 pageIndex == null
 * <p>
 * Created by wengyingjian on 16/2/1.
 */
public class PageQuery implements Serializable {

    private static final int DEFAULT_SIZE = 100;
    //是否启用分页查询
    private Boolean pageQuery;
    private Integer pageIndex;
    //default size
    private int pageSize;


    public void setPageIndex(Integer pageIndex) {
        if (pageIndex == null) {
            this.pageQuery = null;
            return;
        }
        this.pageQuery = true;
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return offset if pageIndex was set , default 0
     */
    public Integer getOffset() {
        if (pageIndex == null) {
            return null;
        }
        if (pageIndex <= 0) {
            return 1;
        }
        return (pageIndex - 1) * pageSize;
    }

    /**
     * @return limit if pageSize was set , default 10
     */
    public Integer getLimit() {
        return pageSize > 0 ? pageSize : DEFAULT_SIZE;
    }

}
