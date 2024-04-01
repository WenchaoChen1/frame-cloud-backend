package com.frame.template.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AuditLogExport {

  /**
   * The smaller the sort, the higher the front
   *
   * @return
   */
  int sort() default 0;

  /**
   * export name
   *
   * @return
   */
  String exportName() default "";

  /**
   * whether
   *
   * @return
   */
  boolean currencyValue1() default false;


  /**
   * whether
   *
   * @return
   */
  boolean currencyValue2() default false;
}
