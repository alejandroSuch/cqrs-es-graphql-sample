package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.patient.PatientNotFoundException;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreatePatientContactDataCommandExecutor implements CommandExecutor<CreatePatientContactData> {
  private PatientAggregateRootRepository patientAggregateRootRepository;

  @Override
  public void execute(CreatePatientContactData command, String user) {
    final UUID patientId = command.getPatientId();
    final PatientAggregateRoot patient = patientAggregateRootRepository.get(patientId)
      .orElseThrow(() -> new PatientNotFoundException(patientId));

    patient.addContactData(new ContactData(command.getValue(), command.getDescription()), command.getId());
    patient.updatedBy(user, Instant.now());

    patientAggregateRootRepository.save(patient);
  }
}
