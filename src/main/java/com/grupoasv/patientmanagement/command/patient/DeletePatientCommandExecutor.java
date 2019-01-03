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
public class DeletePatientCommandExecutor implements CommandExecutor<DeletePatient> {
  private PatientAggregateRootRepository patientAggregateRootRepository;


  @Override
  public void execute(DeletePatient command, String user) {
    PatientAggregateRoot patient = patientAggregateRootRepository.get(command.getId())
      .orElseThrow(() -> new PatientNotFoundException(command.getId()));

    patient.delete();
    patient.updatedBy(user, now());

    patientAggregateRootRepository.save(patient);
  }
}
