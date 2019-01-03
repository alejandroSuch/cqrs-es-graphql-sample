package com.grupoasv.patientmanagement.rest.controller;

import com.grupoasv.patientmanagement.command.patient.contactdata.CreatePatientContactData;
import com.grupoasv.patientmanagement.command.patient.contactdata.DeletePatientContactData;
import com.grupoasv.patientmanagement.command.patient.contactdata.PatientContactDataCommandExecutor;
import com.grupoasv.patientmanagement.command.patient.contactdata.UpdatePatientContactData;
import com.grupoasv.patientmanagement.rest.request.CreatePatientContactDataRequest;
import com.grupoasv.patientmanagement.rest.request.UpdatePatientContactDataRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.UUID.randomUUID;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@AllArgsConstructor
@RequestMapping("patients/{patientId}/contact-data")
public class PatientContactDataRestController {
  private PatientContactDataCommandExecutor commandExecutor;

  @PostMapping
  public ResponseEntity<Void> create(
    @PathVariable("patientId") UUID patientId,
    @Valid @RequestBody CreatePatientContactDataRequest request,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    final UUID aggregateId = randomUUID();
    final String path = format("patients/%s/contact-data/%s", patientId.toString(), aggregateId.toString());
    final URI location = uriComponentsBuilder.path(path).build().toUri();

    final CreatePatientContactData command = CreatePatientContactData.builder()
      .withId(aggregateId)
      .withPatientId(patientId)
      .withValue(request.getValue())
      .withDescription(request.getDescription())
      .build();

    commandExecutor.execute(command, null);

    return created(location).build();
  }

  @PutMapping("{patientContactDataId}")
  public ResponseEntity<Void> update(
    @PathVariable("patientId") UUID patientId,
    @PathVariable("patientContactDataId") UUID aggregateId,
    @Valid @RequestBody UpdatePatientContactDataRequest request
  ) {
    final UpdatePatientContactData command = UpdatePatientContactData.builder()
      .withId(aggregateId)
      .withPatientId(patientId)
      .withValue(request.getValue())
      .withDescription(request.getDescription())
      .build();

    commandExecutor.execute(command, null);

    return noContent().build();
  }

  @DeleteMapping("{patientContactDataId}")
  public ResponseEntity<Void> delete(
    @PathVariable("patientId") UUID patientId,
    @PathVariable("patientContactDataId") UUID aggregateId
  ) {
    final DeletePatientContactData command = DeletePatientContactData.builder()
      .withId(aggregateId)
      .withPatientId(patientId)
      .build();

    commandExecutor.execute(command, null);

    return noContent().build();
  }
}
