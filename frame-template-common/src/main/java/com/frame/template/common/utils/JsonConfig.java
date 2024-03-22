//package com.frame.template.common.utils;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
///**
// * 全局序列化配置类
// */
//@Configuration
//@EnableWebMvc
//public class JsonConfig implements WebMvcConfigurer {
//
//  /**
//   * 全局序列化方式
//   *
//   * @param converters
//   */
//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    //Jackson的全局序列化方式
//    configureJacksonHttpMessageConverter(converters);
//  }
//
//  /**
//   * Jackson的全局序列化方式
//   *
//   * @param converters
//   */
//  private void configureJacksonHttpMessageConverter(List<HttpMessageConverter<?>> converters) {
//    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//    ObjectMapper objectMapper = new ObjectMapper();
//    //序列换成json时,将所有的long变成string.因为js中得数字类型不能包含所有的java long值，超过16位后会出现精度丢失
//    SimpleModule simpleModule = new SimpleModule();
//    simpleModule.addSerializer(Long.class, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
//    simpleModule.addSerializer(Long.TYPE, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
//    objectMapper.registerModule(simpleModule);
//    //反序列化的时候如果多了其他属性,不抛出异常
//    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    //日期格式处理
//    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//    converter.setObjectMapper(objectMapper);
//    converters.add(converter);
//    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//  }
//}
