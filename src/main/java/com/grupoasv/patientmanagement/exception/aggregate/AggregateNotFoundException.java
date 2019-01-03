package com.grupoasv.patientmanagement.exception.aggregate;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AggregateNotFoundException extends RuntimeException {
  private UUID id;

  public AggregateNotFoundException(UUID id, String message) {
    super(message);

    this.id = id;
  }
}
