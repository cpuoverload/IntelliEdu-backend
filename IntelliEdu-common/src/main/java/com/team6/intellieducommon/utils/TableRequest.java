package com.team6.intellieducommon.utils;

import lombok.Data;

@Data
public class TableRequest {
    /**
     * 当前页码
     */
    private Long current = 1L;

    /**
     * 每页多少条记录
     */
    private Long pageSize = 20L;

    /**
     * 排序的字段
     */
    private String sortField;

    /**
     * 是否升序
     * 对于 boolean 类型，lombok 生成的方法不是 get 开头，而是 is 开头；对于 Boolean 类型，生成的方法是 get 开头
     */
    private Boolean isAscend = false;
}
