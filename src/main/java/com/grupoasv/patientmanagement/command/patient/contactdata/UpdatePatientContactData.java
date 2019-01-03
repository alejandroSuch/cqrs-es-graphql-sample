package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class UpdatePatientContactData implements Command {
  private UUID id;
  private UUID patientId;
  private String value;
  private String description;

  public static UpdateContactDataBuilder builder() {
    return new UpdateContactDataBuilder();
  }

  public static class UpdateContactDataBuilder {
    private UUID id;
    private UUID patientId;
    private String value;
    private String description;

    public UpdatePatientContactData build() {
      validateProperties();
      return new UpdatePatientContactData(id, patientId, value, description);
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

    public UpdateContactDataBuilder withValue(String value) {
      failIf(isBlank(value), "Value cannot be blank");

      this.value = value;

      return this;
    }

    public UpdateContactDataBuilder withDescription(String description) {
      failIf(isBlank(description), "Description cannot be blank");

      this.description = description;

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
      requireNonNull(this.value, "Value cannot be null");
      requireNonNull(this.description, "Description cannot be null");
    }


  }
}
