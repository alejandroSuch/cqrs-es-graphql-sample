package com.grupoasv.patientmanagement.event.patient.contactdata;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientContactDataUpdated extends DomainEvent {
  @Type(type = "uuid-char")
  private UUID patientId;

  @Embedded
  private ContactData contactData;

  public PatientContactDataUpdated(UUID aggregateId, UUID patientId, ContactData contactData) {
    super(aggregateId);

    this.patientId = patientId;
    this.contactData = contactData;
  }
}
