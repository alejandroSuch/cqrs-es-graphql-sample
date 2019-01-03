package com.grupoasv.patientmanagement.model.aggregate;

import com.grupoasv.patientmanagement.model.dto.Notes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PatientAddressAggregate extends AddressAggregate {
  @Embedded
  Notes notes;
}
