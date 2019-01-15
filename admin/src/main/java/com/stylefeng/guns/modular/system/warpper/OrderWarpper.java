package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.Map;
import java.util.Set;

public class OrderWarpper extends BaseControllerWarpper {

    public OrderWarpper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
//        System.out.println("空实现");
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "    "+ entry.getValue());
//        }
        map.put("loiter", "brenda-loiter");

        String userName_value = (String)map.get("user_name");

        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(userName_value) && userName_value.length() >= 100) {
            String subMessage = userName_value.substring(0, 100) + "...";
            map.put("message", subMessage);
        }

        map.put("user_name", userName_value+"_loiter新添内容");

    }


}
