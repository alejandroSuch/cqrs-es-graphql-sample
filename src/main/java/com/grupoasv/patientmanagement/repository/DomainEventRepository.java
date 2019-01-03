package com.grupoasv.patientmanagement.repository;

import com.grupoasv.patientmanagement.event.DomainEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventRepository extends JpaRepository<DomainEvent, Long> {
}
