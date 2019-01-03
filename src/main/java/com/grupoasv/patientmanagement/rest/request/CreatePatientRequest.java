package com.grupoasv.patientmanagement.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {
  @NotBlank
  @Pattern(regexp = "^\\d{8}$")
  private String sip;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotNull
  @PastOrPresent
  private Date birthDate;

  private String notes;
}
