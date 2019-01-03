package com.grupoasv.patientmanagement.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.grupoasv.patientmanagement.graphql.repository.SpringDataPatientContactDataRepository;
import com.grupoasv.patientmanagement.model.aggregate.PatientContactData;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientContactDataResolver implements GraphQLResolver<PatientContactData> {
  private SpringDataPatientContactDataRepository repository;

  public PatientAggregateRoot getPatient(PatientContactData patientContactData) {
    return null;
    /* return this.repository.findById(patientContactData.getId())
      .orElseThrow(() -> new PatientContactDataNotFoundException(patientContactData.getId()))
      .getPatient(); */
  }

}
