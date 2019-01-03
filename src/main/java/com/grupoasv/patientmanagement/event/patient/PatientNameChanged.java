package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.FullName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientNameChanged extends DomainEvent {
  @Embedded
  private FullName fullName;

  public PatientNameChanged(UUID aggregateId, FullName fullName) {
    super(aggregateId);

    this.fullName = fullName;
  }
}
