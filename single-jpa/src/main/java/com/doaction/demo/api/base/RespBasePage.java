package com.doaction.demo.api.base;

import lombok.Data;

import java.util.List;

/**
 * 分页数据封装类
 * Created by macro on 2019/4/19.
 */
@Data
public class RespBasePage<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> RespBasePage<T> restPage(List<T> list) {
        RespBasePage<T> result = new RespBasePage<T>();
        /*PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());*/
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
//    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
//        CommonPage<T> result = new CommonPage<T>();
//        result.setTotalPage(pageInfo.getTotalPages());
//        result.setPageNum(pageInfo.getNumber());
//        result.setPageSize(pageInfo.getSize());
//        result.setTotal(pageInfo.getTotalElements());
//        result.setList(pageInfo.getContent());
//        return result;
//    }
}
