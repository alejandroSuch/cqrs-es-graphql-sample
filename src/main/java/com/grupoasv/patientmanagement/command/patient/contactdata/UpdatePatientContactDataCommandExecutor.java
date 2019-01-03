package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.patient.PatientNotFoundException;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.time.Instant.now;

@Component
@AllArgsConstructor
public class UpdatePatientContactDataCommandExecutor implements CommandExecutor<UpdatePatientContactData> {
  private PatientAggregateRootRepository patientAggregateRootRepository;

  @Override
  public void execute(UpdatePatientContactData command, String user) {
    final UUID patientId = command.getPatientId();
    final PatientAggregateRoot patient = patientAggregateRootRepository.get(patientId)
      .orElseThrow(() -> new PatientNotFoundException(patientId));

    patient.updateContactData(new ContactData(command.getValue(), command.getDescription()), command.getId());
    patient.updatedBy(user, now());

    patientAggregateRootRepository.save(patient);
  }
}
