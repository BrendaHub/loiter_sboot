package com.stylefeng.guns.multi.test;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.multi.service.TestService;
import com.stylefeng.guns.multi.service.impl.Test1ServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务测试
 *
 * @author fengshuonan
 * @date 2017-06-23 23:12
 */
public class BizTest extends BaseJunit {

    @Autowired
    TestService testService;


    @Test
//    @Ignore
    public void test() {
//        testService.testGuns();
//
        testService.testBiz();

//        testService.testAll();
    }
}
