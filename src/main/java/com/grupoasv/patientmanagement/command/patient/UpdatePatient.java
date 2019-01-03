package com.grupoasv.patientmanagement.command.patient;

import com.grupoasv.patientmanagement.command.Command;
import com.grupoasv.patientmanagement.model.dto.BirthDate;
import com.grupoasv.patientmanagement.model.dto.FullName;
import com.grupoasv.patientmanagement.model.dto.Notes;
import com.grupoasv.patientmanagement.model.dto.Sip;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePatient implements Command {
  private UUID id = null;
  private Sip sip = null;
  private FullName fullName = null;
  private BirthDate birthDate = null;
  private Notes notes = null;

  public static UpdatePatientBuilder builder() {
    return new UpdatePatientBuilder();
  }

  @Getter
  @NoArgsConstructor
  public static class UpdatePatientBuilder {
    private static final String SIP_REGEX = "^\\d{8}$";

    private UUID id;
    private Sip sip;
    private FullName fullName;
    private BirthDate birthDate;
    private Notes notes;

    public UpdatePatient build() {
      validateProperties();
      return new UpdatePatient(this.id, this.sip, this.fullName, this.birthDate, this.notes);
    }

    public UpdatePatientBuilder withId(UUID id) {
      failIf(isNull(id), "Id cannot be null");

      this.id = id;

      return this;
    }

    public UpdatePatientBuilder withName(String firstName, String lastName) {
      failIf(isBlank(firstName), "You must provide a first name");
      failIf(isBlank(lastName), "You must provide a last name");

      this.fullName = new FullName(firstName, lastName);

      return this;
    }

    public UpdatePatientBuilder withBirthdate(Date birthdate) {
      failIf(isNull(birthdate), "Birth date cannot be null");

      DateTime sanitizedBirthDate = new DateTime(birthdate).withTimeAtStartOfDay();
      failIf(DateTime.now().isBefore(sanitizedBirthDate), "Birth date cannot be placed in the future");

      this.birthDate = new BirthDate(sanitizedBirthDate.toDate());

      return this;
    }

    public UpdatePatientBuilder withSip(String sip) {
      failIf(isNull(sip), "Sip cannot be null");
      failIf(!sip.matches(SIP_REGEX), "Invalid sip");

      this.sip = new Sip(sip);

      return this;
    }

    public UpdatePatientBuilder withNotes(String notes) {
      this.notes = new Notes(notes);

      return this;
    }

    private void failIf(boolean condition, String thrownMessage) {
      if (condition) {
        throw new IllegalArgumentException(thrownMessage);
      }
    }

    private void validateProperties() {
      requireNonNull(this.id, "Id cannot be null");
      requireNonNull(this.sip, "Patient SIP cannot be null");
      requireNonNull(this.fullName, "Patient name cannot be null");
      requireNonNull(this.birthDate, "Patient birth date cannot be null");
    }
  }
}
