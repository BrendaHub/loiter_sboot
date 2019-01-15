package com.stylefeng.guns.multi.service.impl;

import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.multi.entity.Test;
import com.stylefeng.guns.multi.mapper.TestMapper1;
import com.stylefeng.guns.multi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-10
 */
@Service
public class Test1ServiceImpl implements TestService {

    @Autowired
    private TestMapper1 testMapper1;

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public void testBiz() {
        Test test = new Test();
        test.setBbb("bizTestbbbb");
        System.out.println("++++++++++++++++++++++++++");
        testMapper1.insert(test);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    @Transactional
    public void testGuns() {
        Test test = new Test();
        test.setBbb("gunsTestsssss");
        System.out.println("===========================");
        testMapper1.insert(test);
    }

    @Override
    @Transactional
    public void testAll() {
        testGuns();
        testBiz();
        //int i = 1 / 0;
    }

}
