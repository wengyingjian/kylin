package com.wengyingjian.kylin.common.model.query;

import java.io.Serializable;

/**
 * 分页查询父类,查询类可继承此类<br/>
 * <p>
 * 默认参数: 页码 = 1 ,大小 = 100
 * Created by wengyingjian on 16/2/1.
 */
public class PageQuery implements Serializable {

    private static final int DEFAULT_INDEX = 1;
    private static final int DEFAULT_SIZE = 100;

    private int pageIndex;
    //default size
    private int pageSize;


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return offset if pageIndex was set , default 0
     */
    public Integer getOffset() {
        return pageIndex * pageSize > 0 ? (pageIndex - 1) * pageSize : DEFAULT_INDEX;
    }

    /**
     * @return limit if pageSize was set , default 10
     */
    public Integer getLimit() {
        return pageSize > 0 ? pageSize : DEFAULT_SIZE;
    }
}
