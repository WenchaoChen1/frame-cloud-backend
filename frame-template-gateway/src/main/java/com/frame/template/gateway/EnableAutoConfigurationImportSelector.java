//package com.frame.template.gateway;
//
//import org.springframework.beans.factory.BeanClassLoaderAware;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.DeferredImportSelector;
//import org.springframework.core.io.support.SpringFactoriesLoader;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
//public class EnableAutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware {
//
//  private ClassLoader classLoader;
//
//  @Override
//  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, classLoader);
//    if (configurations.size() != 0) {
//      return StringUtils.toStringArray(configurations);
//    }
//    return new String[0];
//  }
//
//  @Override
//  public void setBeanClassLoader(ClassLoader classLoader) {
//    this.classLoader = classLoader;
//  }
//}
