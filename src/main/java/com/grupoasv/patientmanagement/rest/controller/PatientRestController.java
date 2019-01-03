package com.grupoasv.patientmanagement.rest.controller;

import com.grupoasv.patientmanagement.command.patient.CreatePatient;
import com.grupoasv.patientmanagement.command.patient.DeletePatient;
import com.grupoasv.patientmanagement.command.patient.PatientCommandExecutor;
import com.grupoasv.patientmanagement.command.patient.UpdatePatient;
import com.grupoasv.patientmanagement.rest.request.CreatePatientRequest;
import com.grupoasv.patientmanagement.rest.request.UpdatePatientRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@AllArgsConstructor
@RequestMapping("patients")
public class PatientRestController {
  private PatientCommandExecutor patientCommandExecutor;

  @PostMapping
  public ResponseEntity<Void> createPatient(@Valid @RequestBody CreatePatientRequest request, UriComponentsBuilder uriComponentsBuilder) {
    final UUID patientId = randomUUID();
    final URI location = uriComponentsBuilder.path("/patients/" + patientId.toString()).build().toUri();

    // @formatter:off
    final CreatePatient command = CreatePatient.builder()
        .withId(patientId)
        .withName(request.getFirstName(), request.getLastName())
        .withBirthdate(request.getBirthDate())
        .withSip(request.getSip())
        .withNotes(request.getNotes())
      .build();
    // @formatter:on

    patientCommandExecutor.execute(command, null);

    return created(location).build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> updatePatient(@PathVariable("id") UUID patientId, @Valid @RequestBody UpdatePatientRequest request) {
    // @formatter:off
    final UpdatePatient command = UpdatePatient.builder()
        .withId(patientId)
        .withName(request.getFirstName(), request.getLastName())
        .withBirthdate(request.getBirthDate())
        .withSip(request.getSip())
        .withNotes(request.getNotes())
      .build();
    // @formatter:on

    patientCommandExecutor.execute(command, null);

    return noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID patientId) {
    final DeletePatient command = DeletePatient.builder().withId(patientId).build();

    patientCommandExecutor.execute(command, null);

    return noContent().build();
  }
}
