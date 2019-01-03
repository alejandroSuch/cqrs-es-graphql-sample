package com.grupoasv.patientmanagement.command.patient;

import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.patient.PatientNotFoundException;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.Instant.now;

@Component
@AllArgsConstructor
public class UpdatePatientCommandExecutor implements CommandExecutor<UpdatePatient> {

  private PatientAggregateRootRepository patientAggregateRootRepository;

  @Override
  public void execute(UpdatePatient command, String user) {
    PatientAggregateRoot patient = patientAggregateRootRepository.get(command.getId())
      .orElseThrow(() -> new PatientNotFoundException(command.getId()));

    update(patient, command, user);
    patientAggregateRootRepository.save(patient);
  }

  private PatientAggregateRoot update(PatientAggregateRoot patient, UpdatePatient command, String user) {
    patient.setSip(command.getSip());
    patient.setFullName(command.getFullName());
    patient.setBirthDate(command.getBirthDate());
    patient.setNotes(command.getNotes());
    patient.updatedBy(user, now());

    return patient;
  }
}
