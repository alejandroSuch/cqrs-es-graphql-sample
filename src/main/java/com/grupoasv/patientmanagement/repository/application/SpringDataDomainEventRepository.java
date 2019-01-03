package com.grupoasv.patientmanagement.repository.application;

import com.grupoasv.patientmanagement.event.DomainEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataDomainEventRepository extends JpaRepository<DomainEvent, UUID> {
}
