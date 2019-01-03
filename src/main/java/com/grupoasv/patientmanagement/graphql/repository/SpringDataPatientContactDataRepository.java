package com.grupoasv.patientmanagement.graphql.repository;

import com.grupoasv.patientmanagement.model.aggregate.PatientContactData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPatientContactDataRepository extends JpaRepository<PatientContactData, UUID> {
}
