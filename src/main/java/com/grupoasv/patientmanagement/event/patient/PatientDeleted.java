package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
public class PatientDeleted extends DomainEvent {
  public PatientDeleted(UUID aggregateId) {
    super(aggregateId);
  }
}
