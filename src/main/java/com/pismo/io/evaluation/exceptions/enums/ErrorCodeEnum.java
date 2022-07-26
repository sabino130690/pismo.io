package com.pismo.io.evaluation.exceptions.enums;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Errors code
 */
public enum ErrorCodeEnum {

  TRNS400001,
  TRNS404001,
  TRNS404002,
  TRNS422001,
  TRNS500001;

  public String getMessage(final Locale messageLocale) {
    return ResourceBundle.getBundle("messages/exceptions", messageLocale).getString(this.name() + ".message");
  }

}