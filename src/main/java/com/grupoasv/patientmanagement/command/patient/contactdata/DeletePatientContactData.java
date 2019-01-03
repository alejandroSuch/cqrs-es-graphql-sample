package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class DeletePatientContactData implements Command {
  private UUID id;
  private UUID patientId;

  public static UpdateContactDataBuilder builder() {
    return new UpdateContactDataBuilder();
  }

  public static class UpdateContactDataBuilder {
    private UUID id;
    private UUID patientId;

    public DeletePatientContactData build() {
      validateProperties();
      return new DeletePatientContactData(id, patientId);
    }

    public UpdateContactDataBuilder withId(UUID id) {
      failIf(isNull(id), "Id cannot be null");

      this.id = id;

      return this;
    }

    public UpdateContactDataBuilder withPatientId(UUID patientId) {
      failIf(isNull(patientId), "Patient id cannot be null");

      this.patientId = patientId;

      return this;
    }

    private void failIf(boolean condition, String thrownMessage) {
      if (condition) {
        throw new IllegalArgumentException(thrownMessage);
      }
    }

    private void validateProperties() {
      requireNonNull(this.id, "Id cannot be null");
      requireNonNull(this.patientId, "Patient id cannot be null");
    }


  }
}
