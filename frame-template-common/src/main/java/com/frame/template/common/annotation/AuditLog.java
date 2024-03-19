package com.frame.template.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {

  /**

   *There are currently only two types of logs

   *At 1, the exported CSV is the complete data with ID,

   *At 2, it is the complete data of AuditLogRecEntity

   *3. No exported content

   */
  int type() default 1;

  int businessType() default 0;

  String action() default "";

  String details() default "";

  String commonValueName() default "";

  String commonValue1Name() default "";

  String commonValue2Name() default "";

  /**

   *Class to be queried

   * @return

   */
  Class dataClass() default Class.class;


  /**
   * Whether to display
   * 0 No
   * 1 is
   */
  int isExhibit() default 1;

  /************************************The next three are related to the need to save value changes*******************************************/

  /**
   * The primary key is in that parameter, or the parameter name of id
   * When posting, the parameter name is xxx.id, and when getting, it is the parameter name
   * @return
   */
  String primaryKeyParamName1() default "";


  /**
   * The primary key is in that parameter, or the parameter name of id
   * When posting, the parameter name is xxx.id, and when getting, it is the parameter name
   * @return
   */
  String primaryKeyParamName2() default "";

  /**
   * 0 primary key is String (modify one, return one), 1 primary key is List (batch modification, return multiple)
   * @return
   */
  int primaryKeyAndResultType() default 0;
}
