package com.gstdev.template.gateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class TokenDTO {

  @NotEmpty
  private String token;

  @NotEmpty
  private String tokenType;

}
