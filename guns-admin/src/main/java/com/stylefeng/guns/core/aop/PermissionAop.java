/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stylefeng.guns.core.aop;

import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.log.factory.LogFactory;
import com.stylefeng.guns.core.shiro.check.PermissionCheckManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

/**
 * AOP 权限自定义检查
 */
@Aspect
@Component
@Order(200)
public class PermissionAop {

    private static Logger logger = LoggerFactory.getLogger(PermissionAop.class);


    @Pointcut(value = "@annotation(com.stylefeng.guns.core.common.annotion.Permission)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        logger.info(">> 进入权限切面处理类： " + point.getKind());
        MethodSignature ms = (MethodSignature) point.getSignature();
        logger.info(">> ms = " + ms.getName());
        Method method = ms.getMethod();
        logger.info(">> method = " + method.getName());
        // 在对应的方法上获取指定类的注解，
        Permission permission = method.getAnnotation(Permission.class);
        Object[] permissions = permission.value();

        if (permissions == null || permissions.length == 0) {
            logger.info(">>>> permissions = 没有查到权限相关的注解" );
            //检查全体角色
            boolean result = PermissionCheckManager.checkAll();
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        } else {
            logger.info(">>>> permissions = 查到了权限相关的注解 = " + permissions.length );
            for(Object o : permissions ) {
                logger.info(">>>> o  = " + o.toString());
            }
            //检查指定角色
            boolean result = PermissionCheckManager.check(permissions);
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        }

    }

}
