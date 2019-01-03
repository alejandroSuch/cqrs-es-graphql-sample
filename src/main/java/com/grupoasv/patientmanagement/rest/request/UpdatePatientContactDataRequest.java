package com.grupoasv.patientmanagement.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatientContactDataRequest {
  @NotBlank
  String value;
  @NotBlank
  String description;
}
