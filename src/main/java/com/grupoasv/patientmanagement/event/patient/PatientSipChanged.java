package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.Sip;
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
public class PatientSipChanged extends DomainEvent {
  @Embedded
  private Sip sip;

  public PatientSipChanged(UUID aggregateId, Sip sip) {
    super(aggregateId);
    this.sip = sip;
  }
}
