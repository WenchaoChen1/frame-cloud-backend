package com.gstdev.template.common.constant;

public class RedisConstants {
  //  Current login user, account id
  public static final String KET_CURRENT_LOGIN_ACCOUNT_ID = "token::login::account";

  public static final Long TIMEOUT = 10L;

  public static String buildKey(String type, String KEY) {
    return String.format("%s::%s", type, KEY);
  }
}
