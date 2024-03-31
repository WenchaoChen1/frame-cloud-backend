package com.frame.template.gateway.service;


import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.gateway.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Component
public class WebClientAuthenticateService {

  private static final String AUTHENTICATE_SERVICE_NAME = "gstdev-identity";
  private static final String AUTHENTICATE_PATH = "/authenticate";
  private static final String TOKEN_CHECK_PATH = "/oauth2/authorize";
//  @Autowired
//  private WebClient.Builder webClient;
//
//  public Mono<Result> checkToken(TokenDTO tokenDto) {
//    Predicate<HttpStatusCode> statusPredicate=new Predicate<HttpStatusCode>() {
//      @Override
//      public boolean test(HttpStatusCode httpStatusCode) {
//        return false;
//      }
//    };
//    Mono<Result> mono = webClient
//      .baseUrl("lb://" + AUTHENTICATE_SERVICE_NAME + "/")
//      .build()
//      .post()
//      .uri(TOKEN_CHECK_PATH)
//      //.header("Content-Type", "application/json")
//      .contentType(MediaType.APPLICATION_JSON)
//      .body(Mono.just(tokenDto), TokenDTO.class)
////      .retrieve().onStatus(HttpStatus::isError, clientResponse -> {
//      .retrieve().onStatus(statusPredicate, clientResponse -> {
//        return Mono.error(new Exception(clientResponse.toString()));
//      }).bodyToMono(Result.class);
//
//    return mono;
//  }
}
