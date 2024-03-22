package com.frame.template.gateway.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TokenDTO {

  @NotEmpty
  private String token;

  @NotEmpty
  private String tokenType;

}
