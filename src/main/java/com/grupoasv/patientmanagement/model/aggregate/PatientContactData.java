package com.grupoasv.patientmanagement.model.aggregate;

import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@Table
@Entity
@NoArgsConstructor
public class PatientContactData extends Aggregate {
  @Embedded
  ContactData contactData;

  /* @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "PATIENT_ID")
  PatientAggregateRoot patient; */

  public PatientContactData(UUID id, ContactData contactData) {
    super(id);
    this.contactData = contactData;
  }

  public String getDescription() {
    return this.contactData.getDescription();
  }

  public String getValue() {
    return this.contactData.getValue();
  }
}
