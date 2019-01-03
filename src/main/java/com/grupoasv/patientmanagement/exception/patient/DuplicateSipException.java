package com.grupoasv.patientmanagement.exception.patient;

import com.grupoasv.patientmanagement.model.dto.Sip;
import lombok.Getter;

public class DuplicateSipException extends RuntimeException {
  @Getter
  private Sip sip;

  public DuplicateSipException(Sip sip) {
    super("Duplicate sip " + sip.getValue());
    this.sip = sip;
  }
}
