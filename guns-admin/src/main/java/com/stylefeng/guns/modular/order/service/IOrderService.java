package com.stylefeng.guns.modular.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.model.Order;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author ant-loiter
 * @since 2019-01-07
 */
public interface IOrderService extends IService<Order> {

    /**
     * 分页获取订单数据
     */
    List<Map<String, Object>> getOrderList(Page<Order> page, String goodsName,  String orderByField, boolean asc);
}
