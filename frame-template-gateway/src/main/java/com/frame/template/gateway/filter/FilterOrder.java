package com.frame.template.gateway.filter;

/**
 * <p> Description : 过滤器排序值统一管理 </p>
 * <p>
 * 值越小优先级越高
 *
 * @author : cc
 * @date : 2020/3/4 18:31
 */
public class FilterOrder {

    private static final int INITIAL_ORDER = -10;
    private static final int ORDER_STEP = 10;

    public static final int GLOBAL_CACHE_BODY_FILTER_ORDER = INITIAL_ORDER;
    public static final int GLOBAL_SQL_INJECTION_FILTER_ORDER = GLOBAL_CACHE_BODY_FILTER_ORDER + ORDER_STEP;
    public static final int GLOBAL_CERTIFICATION_FILTER_ORDER = GLOBAL_SQL_INJECTION_FILTER_ORDER + ORDER_STEP;
    public static final int GLOBAL_RESPONSE_FILTER_ORDER = GLOBAL_CERTIFICATION_FILTER_ORDER + ORDER_STEP;

}
