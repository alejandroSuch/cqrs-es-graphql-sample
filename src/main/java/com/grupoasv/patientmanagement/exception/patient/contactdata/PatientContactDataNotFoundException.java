package com.grupoasv.patientmanagement.exception.patient.contactdata;

import com.grupoasv.patientmanagement.exception.aggregate.AggregateNotFoundException;

import java.util.UUID;

public class PatientContactDataNotFoundException extends AggregateNotFoundException {
  public PatientContactDataNotFoundException(UUID id) {
    super(id, "Patient contact data not found: " + id.toString());
  }
}
