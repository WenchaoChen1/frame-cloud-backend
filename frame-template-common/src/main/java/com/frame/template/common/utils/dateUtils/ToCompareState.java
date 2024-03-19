package com.frame.template.common.utils.dateUtils;

import lombok.Data;

@Data
public class ToCompareState {
  Boolean before = false;
  Boolean after = false;
  Boolean equals = false;

  public ToCompareState() {
    this.before = false;
    this.after = false;
    this.equals = false;
  }

  public ToCompareState(Boolean before, Boolean after, Boolean equals) {
    this.before = before;
    this.after = after;
    this.equals = equals;
  }


  public Boolean isBefore() {
    return before;
  }

  public Boolean isAfter() {
    return after;
  }

  public Boolean isEquals() {
    return equals;
  }
}
