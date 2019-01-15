package com.stylefeng.guns.core.base.tips;

import java.io.Serializable;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */

public class ErrorTip extends Tip implements Serializable {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
