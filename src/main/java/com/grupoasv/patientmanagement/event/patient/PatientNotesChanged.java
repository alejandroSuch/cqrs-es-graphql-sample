package com.grupoasv.patientmanagement.event.patient;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.Notes;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientNotesChanged extends DomainEvent {
  @Embedded
  private Notes notes;

  public PatientNotesChanged(UUID aggregateId, Notes notes) {
    super(aggregateId);
    this.notes = notes;
  }
}
