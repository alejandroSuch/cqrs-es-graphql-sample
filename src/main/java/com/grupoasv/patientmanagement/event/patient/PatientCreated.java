package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientCreated extends DomainEvent {
  public PatientCreated(UUID aggregateId) {
    super(aggregateId);
  }
}
