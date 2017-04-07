package com.ec.facilitator.base.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于限制该Action只能人认证授权后才能访问
 * @author 张荣英
 * @date 2017年4月7日 下午10:23:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTag {
	String value() default "";
}
