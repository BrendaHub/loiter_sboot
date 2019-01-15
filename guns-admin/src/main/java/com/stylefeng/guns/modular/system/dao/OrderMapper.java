package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.model.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author ant-loiter
 * @since 2019-01-07
 */

@Component
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 获取操作日志
     */
    List<Map<String, Object>> getOrderList(@Param("page") Page<Order> page,  @Param("goodsName") String goodsName, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

}
