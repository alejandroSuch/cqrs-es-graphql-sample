package com.grupoasv.patientmanagement.command.patient;

import com.grupoasv.patientmanagement.command.Command;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeletePatient implements Command {
  private UUID id = null;

  public static DeletePatientBuilder builder() {
    return new DeletePatientBuilder();
  }

  @Getter
  @NoArgsConstructor
  public static class DeletePatientBuilder {
    private UUID id;

    public DeletePatient build() {
      validateProperties();
      return new DeletePatient(this.id);
    }

    private void validateProperties() {
      requireNonNull(id, "Id cannot be null");
    }

    public DeletePatientBuilder withId(UUID id) {
      failIf(isNull(id), "Id cannot be null");

      this.id = id;

      return this;
    }

    private void failIf(boolean condition, String thrownMessage) {
      if (condition) {
        throw new IllegalArgumentException(thrownMessage);
      }
    }
  }
}
