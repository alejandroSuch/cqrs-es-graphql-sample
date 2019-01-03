package com.grupoasv.patientmanagement.listener;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.repository.application.SpringDataDomainEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DomainEventsListener implements ApplicationListener<PayloadApplicationEvent> {
  SpringDataDomainEventRepository domainEventRepository;

  @Override
  public void onApplicationEvent(PayloadApplicationEvent event) {
    if (event.getPayload() instanceof DomainEvent) {
      domainEventRepository.save((DomainEvent) event.getPayload());
    }
  }
}
