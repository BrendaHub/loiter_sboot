package com.stylefeng.guns.modular.order.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.modular.system.warpper.OrderWarpper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.order.service.IOrderService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-07 11:22:32
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private String PREFIX = "/order/order/";

    @Autowired
    private IOrderService orderService;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/order_add")
    public String orderAdd() {
        return PREFIX + "order_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/order_update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("item",order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String goodsName) {
        //创建后台分页的分布对象
        Page<Order> page = new PageFactory<Order>().defaultPage();
        // 获取数据列表
        System.out.println(">>>>>>>>>>>>>>>. " + goodsName);
        List<Map<String, Object>> result = orderService.getOrderList(page, goodsName, page.getOrderByField(),page.isAsc());
//        return orderService.selectList(null);
        page.setRecords((List<Order>)new OrderWarpper(result).warp());
        PageInfoBT<Order> list = super.packForBT(page);
        List<Order> l = list.getRows();
        for(Iterator it = l.iterator();it.hasNext(); ) {
            System.out.println(">>>>>>>>>>>>>>>>>>> " + JSON.toJSON(it.next()));
        }
        return super.packForBT(page);

//        page.setRecords(new )new
//        return null;
    }


    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(Order order) {
        logger.info("order jsoninfo = " + JSON.toJSONString(order));
        orderService.insert(order);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderId) {
        orderService.deleteById(orderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{orderId}")
    @ResponseBody
    public Object detail(@PathVariable("orderId") Integer orderId) {
        return orderService.selectById(orderId);
    }
}
