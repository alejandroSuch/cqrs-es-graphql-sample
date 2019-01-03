package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.BirthDate;
import lombok.AllArgsConstructor;
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
public class PatientBirthDateChanged extends DomainEvent {
  @Embedded
  private BirthDate birthDate;

  public PatientBirthDateChanged(UUID aggregateId, BirthDate birthDate) {
    super(aggregateId);

    this.birthDate = birthDate;
  }
}
