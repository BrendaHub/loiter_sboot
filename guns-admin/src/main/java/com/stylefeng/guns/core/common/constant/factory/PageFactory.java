package com.stylefeng.guns.core.common.constant.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.state.Order;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.NumUtil;
import com.stylefeng.guns.core.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.valueOf(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((offset / limit + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }

    /**
     * @description:
     * @author: ant-loiter
     * @date: 2019-01-15 11:12
     *  limit: 每页多少条数据
     *  offset: 每页的偏移量
     *  soft: 排序字段名称
     *  order: asc 或 desc （升序或降序）
     * @return:
     * 功能描述：
     */
    public Page<T> restFullPage(int limit, int offset, String sort, String order) {
        if(limit <= 0 || limit > 30 ) {
            limit = 30;
        }
        if(ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((offset / limit + 1), limit, sort);
            if(Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
