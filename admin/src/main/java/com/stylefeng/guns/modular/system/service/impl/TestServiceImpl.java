package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.system.dao.TestMapper;
import com.stylefeng.guns.modular.system.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ant-loiter
 * @since 2019-01-07
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
