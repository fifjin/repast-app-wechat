package com.aaa.gj.repast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-11 18:38
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
//第一个参数是标识在方法上，第二个是标识在在类上
@Retention(RetentionPolicy.RUNTIME)
//注解什么时候生效，这里是项目运行时生效
public @interface LoginLogAnnotation {
    //要执行的操作类型:
    //     *          eg: 删除操作，下单操作，登录操作...
    String operationType() default "";//default:叫做默认值，如果传递operationType的时候，直接使用的是默认值
    //   具体要执行的操作:
    //     *          eg:删除操作--->删除用户，清空购物车，删除商品...
    String operationName() default "";

}
