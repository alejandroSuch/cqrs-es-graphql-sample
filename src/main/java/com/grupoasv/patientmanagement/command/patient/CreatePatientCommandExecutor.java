package com.grupoasv.patientmanagement.command.patient;

import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.patient.DuplicateSipException;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.Sip;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import com.grupoasv.patientmanagement.service.validation.SipValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.Instant.now;

@Component
@AllArgsConstructor
public class CreatePatientCommandExecutor implements CommandExecutor<CreatePatient> {

  private PatientAggregateRootRepository patientAggregateRootRepository;

  @Override
  public void execute(CreatePatient command, String user) {
    // TODO: COUNT ACTIVE PATIENTS WITH GIVEN SIP AND THROW EXCEPTION IF DUPLICATE
    PatientAggregateRoot patient = createPatient(command, user);
    patientAggregateRootRepository.save(patient);
  }

  private PatientAggregateRoot createPatient(CreatePatient command, String user) {
    PatientAggregateRoot patient = new PatientAggregateRoot(command.getId());

    Sip sip = command.getSip();
    if (!SipValidator.allowsDuplicates(sip) && patientAggregateRootRepository.countActivePatientsBy(sip) > 0) {
      throw new DuplicateSipException(sip);
    }
    patient.setSip(sip);
    patient.setFullName(command.getFullName());
    patient.setBirthDate(command.getBirthDate());
    patient.setNotes(command.getNotes());

    patient.updatedBy(user, now());

    return patient;
  }
}
