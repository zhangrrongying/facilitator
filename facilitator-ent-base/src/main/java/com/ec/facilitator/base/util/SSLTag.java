package com.ec.facilitator.base.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于限制SSL的ACTION只能在https下面访问
 * @author 张荣英
 * @date 2017年4月7日 下午10:44:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SSLTag {
}
