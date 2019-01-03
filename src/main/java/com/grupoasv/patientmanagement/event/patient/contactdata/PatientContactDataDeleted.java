package com.grupoasv.patientmanagement.event.patient.contactdata;

import com.grupoasv.patientmanagement.event.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientContactDataDeleted extends DomainEvent {
  @Type(type = "uuid-char")
  private UUID patientId;

  public PatientContactDataDeleted(UUID aggregateId, UUID patientId) {
    super(aggregateId);
    this.patientId = patientId;
  }
}
