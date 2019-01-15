package com.stylefeng.guns.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.util.ResKit;
import com.stylefeng.guns.modular.system.dao.OrderMapper;
import com.stylefeng.guns.modular.system.model.Order;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: OrderRestfullController
 * @author: ant-loiter
 * @date: 2019-01-15 10:34
 * 功能描述：测试订单的API接口类
 */
@Api(tags = "订单逻辑处理接口", value = "order operator interface")
@RestController
@RequestMapping("/api/order")
public class OrderRestfullController extends BaseController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value="根据状态和每页显示条数查询订单",notes="状态表订单状态字典，类型为数字；显示条数， 类型为数字， 取值范围[0,30]")
    @ApiImplicitParams({
            @ApiImplicitParam(name="status",value="订单的状态，与系统字典一致", paramType="query", dataType = "Integer"),
            @ApiImplicitParam(name="size",value="每页显示的条数",required=false ,paramType="query"),
            @ApiImplicitParam(name="offset",value="分页的偏移量",paramType="query",dataType="Integer"),
            @ApiImplicitParam(name="sort",value="指定用来排序的字段名",required=false,paramType="query",dataType="String"),
            @ApiImplicitParam(name="order",value="指定升序还是降序（ASC, DESC)",required=false,paramType="query",dataType="String")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="每页显示条数输入错误"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public Object findAllOrders(@RequestParam(required = true, defaultValue = "0") Integer status,
                                @RequestParam(defaultValue = "30") Integer size,
                                @RequestParam(required = true, defaultValue = "0") Integer offset,
                                @RequestParam String sort,
                                @RequestParam(defaultValue = "ASC") String order){
        if(size < 0 || size > 30) {
            return new ErrorTip(400, "输入的每页显示条数不正确，[0, 30]");
        }

        Page<Order> page = new PageFactory<Order>().restFullPage(size, offset, sort, order) ;

        List<Map<String, Object>> result = orderMapper.getOrderList(page, "", page.getOrderByField(), page.isAsc());

        return JSON.toJSON(result);

    }


}
