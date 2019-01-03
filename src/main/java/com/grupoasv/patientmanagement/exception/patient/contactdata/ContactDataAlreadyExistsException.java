package com.grupoasv.patientmanagement.exception.patient.contactdata;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ContactDataAlreadyExistsException extends RuntimeException {
  private String value;
  private UUID aggregateId;


  public ContactDataAlreadyExistsException(UUID aggregateId, String value) {
    super("Contact data " + value + " already exists for aggregate " + aggregateId.toString());

    this.aggregateId = aggregateId;
    this.value = value;
  }
}
