package com.frame.template.gateway.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("spring.security.oauth2.resourceserver.jwt")
public class JwtConfig {
  private String issuerUri;
  private List<String> whitelist;

  public String getIssuerUri() {
    return issuerUri;
  }

  public void setIssuerUri(String issuerUri) {
    this.issuerUri = issuerUri;
  }

  public List<String> getWhitelist() {
    return whitelist;
  }

  public void setWhitelist(List<String> whitelist) {
    this.whitelist = whitelist;
  }
}
