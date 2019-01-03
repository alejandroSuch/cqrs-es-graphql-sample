package com.grupoasv.patientmanagement.model.projection;

import java.util.UUID;

public interface PatientContactDataProjection {
  UUID getId();
  String getDescription();
  String getValue();
}
