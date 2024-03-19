//package com.gstdev.template.common.utils;
//
//import cn.hutool.json.JSON;
//import com.frame.template.common.domain.ParameterGstDev;
//import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
//import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.DefaultParameterNameDiscoverer;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AspectUtil {
//
//  /**
//   * Parameter processing
//   *
//   * @param joinPoint
//   * @param method
//   * @return
//   */
//  public static Map<String, ParameterGstDev> getParameterGstDev(ProceedingJoinPoint joinPoint, Method method) {
//    //Get all parameter values of the method
//    Object[] args = joinPoint.getArgs();
//    //Get all parameters of the method
//    String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
//    Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
//    Map<String, ParameterGstDev> stringParameterGstDevMap = new HashMap<>();
//    for (int i = 0; i < args.length; i++) {
//      String parameterName = parameterNames[i];
//      if (parameterName == null) {
//        parameterName = parameters[i].getName();
//      }
//      Object arg = args[i];
//      ParameterGstDev parameterGstDev = new ParameterGstDev();
//      parameterGstDev.setParamName(parameterName);
//      parameterGstDev.setParameter(parameters[i]);
//      parameterGstDev.setParamValue(arg);
//      stringParameterGstDevMap.put(parameterName, parameterGstDev);
//    }
//    return stringParameterGstDevMap;
//  }
//
//
//  /**
//   * Get the currently logged in user
//   * @return
//   */
//  public static AccountDto getLoginAccountDto(RedisCurrentLoginInformation redisCurrentLoginInformation){
//    AccountDto accountDto = null;
//    try {
//      CurrentLoginInformation currentLoginInformation = redisCurrentLoginInformation.getCurrentLoginInformation();
//      if(currentLoginInformation!=null){
//        JSON json = currentLoginInformation.getCurrentLoginAccount();
//        if(json!=null){
//          accountDto = json.toBean(AccountDto.class);
//          //Because of the mirror mode, record the tenant of the current operation
//          accountDto.setTenantId(currentLoginInformation.getTenantId());
//        }
//      }
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return accountDto;
//  }
//
//}
