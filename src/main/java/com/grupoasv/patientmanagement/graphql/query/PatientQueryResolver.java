package com.grupoasv.patientmanagement.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.grupoasv.patientmanagement.model.projection.PatientProjection;
import com.grupoasv.patientmanagement.repository.application.SpringDataPatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientQueryResolver implements GraphQLQueryResolver {

  SpringDataPatientAggregateRootRepository repository;

  /* public Iterable<PatientAggregateRoot> findAllPatients() {
    return repository.findAll();
  }*/
  public Iterable<PatientProjection> findAllPatients() {
    return repository.findAllProjections();
  }

  public Long countPatients() {
    return repository.count();
  }

}
