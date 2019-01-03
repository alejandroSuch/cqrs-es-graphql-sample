package com.grupoasv.patientmanagement.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

import static java.lang.Boolean.FALSE;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Aggregate {
  @Id
  @Type(type = "uuid-char")
  UUID id;

  Boolean deleted = FALSE;
}
