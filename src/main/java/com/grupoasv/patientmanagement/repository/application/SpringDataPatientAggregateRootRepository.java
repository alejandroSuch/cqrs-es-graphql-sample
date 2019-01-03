package com.grupoasv.patientmanagement.repository.application;

import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.projection.PatientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataPatientAggregateRootRepository extends JpaRepository<PatientAggregateRoot, UUID> {
  @Query("SELECT p FROM PatientAggregateRoot p")
  List<PatientProjection> findAllProjections();

  List<PatientAggregateRoot> findAllBySip(String sip);

  Long countAllBySipAndDeletedIsFalse(String sip);
}
