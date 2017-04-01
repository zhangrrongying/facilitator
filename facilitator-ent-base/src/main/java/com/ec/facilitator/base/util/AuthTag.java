package com.ec.facilitator.base.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: AuthTag
 * @Description: 用于限制该Action只能人认证授权后才能访问
 * @author ryan
 * @date Sep 26, 2014 3:46:59 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTag {
	String value() default "";
}
