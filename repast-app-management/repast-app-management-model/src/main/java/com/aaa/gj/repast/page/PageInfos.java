package com.aaa.gj.repast.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-09 22:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageInfos<T> {
    /**
     * 当前页码数
     */
    private Integer pageNum;

    /**
     * 每一页显示的条数
     */
    private Integer pageSize;

    /**
     * 查询出来的分页数据
     */
    private T t;
}
