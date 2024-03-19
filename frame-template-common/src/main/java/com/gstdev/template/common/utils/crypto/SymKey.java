package com.gstdev.template.common.utils.crypto;

/**
 * @Author: zcy
 * @Data: 2022/12/07
 * @Description:
 */
public class SymKey {

  private String hexKey;
  private Integer keySize;

  public String getHexKey() {
    return hexKey;
  }

  public void setHexKey(String hexKey) {
    this.hexKey = hexKey;
  }

  public Integer getKeySize() {
    return keySize;
  }

  public void setKeySize(Integer keySize) {
    this.keySize = keySize;
  }
}
