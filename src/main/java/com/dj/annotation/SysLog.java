package com.dj.annotation;



import com.dj.enums.BusinessType;
import com.dj.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author wxl
 * @date 2020-03-20 下午1:04
 * @company www.dfdk.com.cn
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
