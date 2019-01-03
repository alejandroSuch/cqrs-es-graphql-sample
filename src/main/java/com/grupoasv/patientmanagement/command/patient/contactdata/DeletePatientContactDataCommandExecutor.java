package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.patient.PatientNotFoundException;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.time.Instant.now;

@Component
@AllArgsConstructor
public class DeletePatientContactDataCommandExecutor implements CommandExecutor<DeletePatientContactData> {
  private PatientAggregateRootRepository patientAggregateRootRepository;

  @Override
  public void execute(DeletePatientContactData command, String user) {
    final UUID patientId = command.getPatientId();
    final PatientAggregateRoot patient = patientAggregateRootRepository.get(patientId)
      .orElseThrow(() -> new PatientNotFoundException(patientId));

    patient.deleteContactData(command.getId());
    patient.updatedBy(user, now());

    patientAggregateRootRepository.save(patient);
  }
}
