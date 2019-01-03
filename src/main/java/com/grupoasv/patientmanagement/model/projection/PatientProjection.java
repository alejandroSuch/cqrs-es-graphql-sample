package com.grupoasv.patientmanagement.model.projection;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PatientProjection {
  UUID getId();

  String getFirstName();

  String getLastName();

  Date getBirthDate();

  String getSip();

  String getNotes();

  List<PatientContactDataProjection> getContactData();
}
