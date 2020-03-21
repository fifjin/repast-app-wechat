package com.aaa.gj.repast.base;

import com.aaa.gj.repast.page.PageInfos;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-09 22:34
 **/
public abstract class BaseService<T> {
    public abstract Mapper<T> getMapper();
    //保存方法
    protected Integer save(T t) throws Exception {
        return getMapper().insert(t);
    }
    //通过主键更新
    protected Integer update(T t) throws Exception {
        return getMapper().updateByPrimaryKey(t);
    }
    //通过主键删除
    protected Integer deleteByPrimaryKey(Object key) throws Exception {
        return getMapper().deleteByPrimaryKey(key);
    }
    //通过实体类属性进行删除
    protected Integer delete(T t) throws Exception {
        return getMapper().delete(t);
    }
    //通过主键进行查询
    protected T get(Object key) throws Exception {
        return getMapper().selectByPrimaryKey(key);
    }
    //查询所有信息
    protected List<T> get() throws Exception {
        return getMapper().selectAll();
    }
    //通过实体类属性进行查询
    protected T getOne(T t) throws Exception {
        return getMapper().selectOne(t);
    }
    //通过实体类属性进行查询--->返回集合列表
    protected List<T> getModel(T t) throws Exception {
        return getMapper().select(t);
    }
    //带分页的条件查询
    //     *      offset:偏移量(当前页码数)
    //     *      limit:查询多少条
    //     *      select xxx from user limit (1,5);
    protected List<T> getPage(T t, PageInfos<T> pageInfos) throws Exception {
        return getMapper().selectByRowBounds(t, new RowBounds(pageInfos.getPageNum(),
                pageInfos.getPageSize()));
    }
    //查询数据(带条件)
    // *      select count(1) from user where username = 'zhangsan';
    // *      如果不需要条件，则直接传null
    protected Integer getCount(T t) throws Exception {
        return getMapper().selectCount(t);
    }

    protected PageInfo<T> getPageInfo(PageInfos<T> pageInfos) throws Exception {
        /**
         * 第一次点击菜单进入右侧页面
         * 说明是没有页码数的--->这就可能报错空指针--->所以需要赋值一个初始化的页码数
         */
        if (pageInfos.getPageNum() == null) {
            pageInfos.setPageNum(0);
        }
        // select * from user limit 0, 3
        PageHelper.startPage(pageInfos.getPageNum(), pageInfos.getPageSize());
        List<T> list = this.getModel(pageInfos.getT());
        PageInfo<T> pageInfo = new PageInfo<T>();
        /**
         * 为了计算总共的页数，所以需要传递一个总数量
         */
        pageInfo.setTotal(this.getCount(pageInfos.getT()));

        return pageInfo;
    }
}
