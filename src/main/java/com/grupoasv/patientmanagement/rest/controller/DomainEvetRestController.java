package com.grupoasv.patientmanagement.rest.controller;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.repository.DomainEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.by;

@RestController
@AllArgsConstructor
@RequestMapping("domain-events")
public class DomainEvetRestController {
  DomainEventRepository domainEventRepository;

  @GetMapping
  public List<DomainEvent> all() {
    return domainEventRepository.findAll(by(asc("id")));
  }
}
