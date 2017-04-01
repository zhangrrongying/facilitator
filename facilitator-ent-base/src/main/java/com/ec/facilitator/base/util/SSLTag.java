package com.ec.facilitator.base.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: SSLTag
 * @Description: 用于限制SSL的ACTION只能在https下面访问
 * @author ryan
 * @date Sep 26, 2014 3:46:33 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SSLTag {
}
