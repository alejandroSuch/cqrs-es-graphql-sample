package com.grupoasv.patientmanagement.repository;

import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.Sip;

import java.util.Optional;
import java.util.UUID;

public interface PatientAggregateRootRepository {
  Optional<PatientAggregateRoot> get(UUID id);

  void save(PatientAggregateRoot patientAggregateRoot);

  void delete(PatientAggregateRoot patientAggregateRoot);

  Long countActivePatientsBy(Sip sip);
}
