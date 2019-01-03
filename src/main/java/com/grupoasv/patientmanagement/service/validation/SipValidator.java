package com.grupoasv.patientmanagement.service.validation;

import com.grupoasv.patientmanagement.model.dto.Sip;

import static java.util.Objects.requireNonNull;

public class SipValidator {
  private static final String WILDCARD_SIP = "00000000";

  public static Boolean allowsDuplicates(Sip sip) {
    requireNonNull(sip);

    return WILDCARD_SIP.equals(sip.getValue());
  }
}
