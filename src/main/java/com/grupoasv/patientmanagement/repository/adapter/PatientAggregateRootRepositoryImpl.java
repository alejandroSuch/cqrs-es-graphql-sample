package com.grupoasv.patientmanagement.repository.adapter;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.aggregate.root.PatientAggregateRoot;
import com.grupoasv.patientmanagement.model.dto.Sip;
import com.grupoasv.patientmanagement.repository.PatientAggregateRootRepository;
import com.grupoasv.patientmanagement.repository.application.SpringDataPatientAggregateRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PatientAggregateRootRepositoryImpl implements PatientAggregateRootRepository {
  private SpringDataPatientAggregateRootRepository repository;
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public Optional<PatientAggregateRoot> get(UUID id) {
    return repository.findById(id);
  }

  @Override
  public void save(PatientAggregateRoot patientAggregateRoot) {
    repository.save(patientAggregateRoot);
  }

  @Override
  public void delete(PatientAggregateRoot patientAggregateRoot) {
    repository.delete(patientAggregateRoot);
    patientAggregateRoot.getEvents()
      .forEach(
        domainEvent ->
          applicationEventPublisher.publishEvent(new PayloadApplicationEvent<DomainEvent>(this, domainEvent))
      );
  }

  @Override
  public Long countActivePatientsBy(Sip sip) {
    return repository.countAllBySipAndDeletedIsFalse(sip.getValue());
  }
}
