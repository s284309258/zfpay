package com.example.longecological.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 进行验签及TOKEN校验
 * 
 * @author huan.fu 
 * @date 2018/9/28 - 16:08 
 */  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface SIGNToken {

}
