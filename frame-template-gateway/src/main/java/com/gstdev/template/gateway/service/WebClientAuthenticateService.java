package com.gstdev.template.gateway.service;


import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.gateway.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientAuthenticateService {

  private static final String AUTHENTICATE_SERVICE_NAME = "gstdev-identity";
  private static final String AUTHENTICATE_PATH = "/authenticate";
  private static final String TOKEN_CHECK_PATH = "/oauth2/authorize";
  @Autowired
  private WebClient.Builder webClient;

  public Mono<Result> checkToken(TokenDTO tokenDto) {
    Mono<Result> mono = webClient
      .baseUrl("lb://" + AUTHENTICATE_SERVICE_NAME + "/")
      .build()
      .post()
      .uri(TOKEN_CHECK_PATH)
      //.header("Content-Type", "application/json")
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(tokenDto), TokenDTO.class)
      .retrieve().onStatus(HttpStatus::isError, clientResponse -> {
        return Mono.error(new Exception(clientResponse.toString()));
      }).bodyToMono(Result.class);

    return mono;
  }
}
