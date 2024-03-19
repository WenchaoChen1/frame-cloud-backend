package com.frame.template.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 方法上使用的注解
@Retention(RetentionPolicy.RUNTIME) // 运行时通过反射访问
public @interface CheckDataLength {

  /**
   * requires a comparison class
   * @return
   */
  Class dataClass() default Class.class;

  /**
   * The first-level parameter name that needs to be verified
   * @return
   */
  String paramNameLevel1() default "";

  /**
   * The name of the second-level parameter that needs to be verified (currently only supports the second-level)
   * @return
   */
  String paramNameLevel2() default "";

  /**
   * Several levels of parameters,
   * For example, there is a List object in a @RequestBody InvoiceVo invoiceVo object
   * invoiceVo is a level 1 parameter, List is a level 2 parameter
   * @return
   */
  int level() default 1;

  /**
   * Whether it is a csv file, only do special processing for csv
   * @return
   */
  boolean isCsvFile()  default false;

}
