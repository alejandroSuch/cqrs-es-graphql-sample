package com.grupoasv.patientmanagement.event.patient.contactdata;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientContactDataAdded extends DomainEvent {
  private UUID patientId;
  private ContactData contactData;

  public PatientContactDataAdded(UUID aggregateId, UUID patientId, ContactData contactData) {
    super(aggregateId);

    this.patientId = patientId;
    this.contactData = contactData;
  }
}
