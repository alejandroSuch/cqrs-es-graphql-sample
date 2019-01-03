package com.grupoasv.patientmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @Embedded
  FullName fullName;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "BIRTH_DATE"))
  })
  BirthDate birthDate;

}
