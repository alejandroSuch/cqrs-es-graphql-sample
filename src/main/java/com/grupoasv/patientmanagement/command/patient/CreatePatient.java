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

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePatient implements Command {
  private UUID id = null;
  private Sip sip = null;
  private FullName fullName = null;
  private BirthDate birthDate = null;
  private Notes notes = null;

  public static CreatePatientBuilder builder() {
    return new CreatePatientBuilder();
  }

  @Getter
  @NoArgsConstructor
  public static class CreatePatientBuilder {
    private static final String SIP_REGEX = "^\\d{8}$";

    private UUID id;
    private Sip sip;
    private FullName fullName;
    private BirthDate birthDate;
    private Notes notes;

    public CreatePatient build() {
      validateProperties();
      return new CreatePatient(this.id, this.sip, this.fullName, this.birthDate, this.notes);
    }

    public CreatePatientBuilder withId(UUID id) {
      failIf(isNull(id), "Id cannot be null");

      this.id = id;

      return this;
    }

    public CreatePatientBuilder withName(String firstName, String lastName) {
      failIf(isBlank(firstName), "You must provide a first name");
      failIf(isBlank(lastName), "You must provide a last name");

      this.fullName = new FullName(firstName, lastName);

      return this;
    }

    public CreatePatientBuilder withBirthdate(Date birthdate) {
      failIf(isNull(birthdate), "Birth date cannot be null");

      DateTime sanitizedBirthDate = new DateTime(birthdate).withTimeAtStartOfDay();
      failIf(DateTime.now().isBefore(sanitizedBirthDate), "Birth date cannot be placed in the future");

      this.birthDate = new BirthDate(sanitizedBirthDate.toDate());

      return this;
    }

    public CreatePatientBuilder withSip(String sip) {
      failIf(isNull(sip), "Sip cannot be null");

      final String paddedSip = format("%08d", parseLong(sip));
      failIf(!paddedSip.matches(SIP_REGEX), "Invalid sip");

      this.sip = new Sip(paddedSip);

      return this;
    }

    public CreatePatientBuilder withNotes(String notes) {
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
