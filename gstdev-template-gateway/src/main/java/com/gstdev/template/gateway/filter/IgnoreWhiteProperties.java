package com.gstdev.template.gateway.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 放行白名单配置
 *
 * @author zcy
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {
  /**
   * 放行白名单配置，网关不校验此处的白名单
   */
  private List<String> whites = new ArrayList<>();

  public List<String> getWhites() {
    return whites;
  }

  public void setWhites(List<String> whites) {
    this.whites = whites;
  }
}
