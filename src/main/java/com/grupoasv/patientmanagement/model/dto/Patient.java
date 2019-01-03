package com.grupoasv.patientmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends Person {
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "SIP"))
  })
  Sip sip;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "NOTES"))
  })
  Notes notes;

  Boolean isFictional;
}
