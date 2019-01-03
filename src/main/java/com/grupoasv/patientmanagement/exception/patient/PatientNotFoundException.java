package com.grupoasv.patientmanagement.exception.patient;

import com.grupoasv.patientmanagement.exception.aggregate.AggregateNotFoundException;

import java.util.UUID;

public class PatientNotFoundException extends AggregateNotFoundException {
  public PatientNotFoundException(UUID id) {
    super(id, "Patient " + id.toString() + " not found");
  }
}
