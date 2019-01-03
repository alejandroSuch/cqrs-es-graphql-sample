package com.grupoasv.patientmanagement.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static com.grupoasv.patientmanagement.model.dto.ContactDataType.PHONE;

@Data
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
public class ContactData {
  @Column
  @NonNull
  private String value;

  @Column
  @NonNull
  private String description;

  @Column
  private ContactDataType contactDataType = PHONE;
}
