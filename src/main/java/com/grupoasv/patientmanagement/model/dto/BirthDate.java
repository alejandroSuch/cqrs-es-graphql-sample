package com.grupoasv.patientmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BirthDate {
  @Column
  Date value;
}
