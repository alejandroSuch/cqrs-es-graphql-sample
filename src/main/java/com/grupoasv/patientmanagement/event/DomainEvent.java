package com.grupoasv.patientmanagement.event;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import java.time.Instant;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.InheritanceType.JOINED;

@Data
@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "TYPE")
public class DomainEvent {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Type(type = "uuid-char")
  private UUID aggregateId;

  private Instant when;
  private String user;

  @Column(name = "TYPE", insertable = false)
  private String type;

  public DomainEvent() {
  }

  public DomainEvent(UUID aggregateId, Instant when) {
    this.aggregateId = aggregateId;
    this.when = when;
  }

  public DomainEvent(UUID aggregateId) {
    this.when = Instant.now();
    this.aggregateId = aggregateId;
  }
}
