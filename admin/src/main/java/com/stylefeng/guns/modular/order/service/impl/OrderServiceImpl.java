package com.stylefeng.guns.modular.order.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.dao.OrderMapper;
import com.stylefeng.guns.modular.order.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author ant-loiter
 * @since 2019-01-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public List<Map<String, Object>> getOrderList(Page<Order> page, String goodsName, String orderByField, boolean asc) {
        System.out.println("++++++++++++++" + goodsName);
        return this.baseMapper.getOrderList(page, goodsName, orderByField, asc);

    }
}