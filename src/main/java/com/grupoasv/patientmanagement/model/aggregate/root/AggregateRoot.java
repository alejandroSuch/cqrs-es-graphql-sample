package com.grupoasv.patientmanagement.model.aggregate.root;

import com.grupoasv.patientmanagement.event.DomainEvent;
import com.grupoasv.patientmanagement.model.aggregate.Aggregate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

@MappedSuperclass
public class AggregateRoot extends Aggregate {
  @Transient
  List<DomainEvent> events = new ArrayList();

  public void register(DomainEvent event) {
    requireNonNull(event);
    this.events.add(event);
  }

  @AfterDomainEventPublication
  public void clearEvents() {
    this.events.clear();
  }

  @DomainEvents
  public List<DomainEvent> getEvents() {
    return unmodifiableList(this.events);
  }

  public void updatedBy(String user, Instant when) {
    events.forEach(event -> {
      event.setUser(user);
      event.setWhen(when);
    });
  }
}
