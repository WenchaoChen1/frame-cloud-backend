package com.gstdev.template.common.utils.crypto;

/**
 * @Author: zcy
 * @Data: 2022/12/07
 * @Description:
 */
public class AsymKeyPairs {

  private String hexPublicKey;
  private String hexPrivateKey;

  public String getHexPublicKey() {
    return hexPublicKey;
  }

  public void setHexPublicKey(String hexPublicKey) {
    this.hexPublicKey = hexPublicKey;
  }

  public String getHexPrivateKey() {
    return hexPrivateKey;
  }

  public void setHexPrivateKey(String hexPrivateKey) {
    this.hexPrivateKey = hexPrivateKey;
  }
}
