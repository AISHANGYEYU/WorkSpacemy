package com.easytest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})//切面范围
@Retention(RetentionPolicy.RUNTIME)//什么时候生效（运行时生效）
public @interface GlobalInterceptor {
    /**
     * 是否检验登录
     * @return
     */
    boolean checkLogin() default false;

    /**
     * 是否校验参数
     * @return
     */
    boolean checkParams() default false;
}
