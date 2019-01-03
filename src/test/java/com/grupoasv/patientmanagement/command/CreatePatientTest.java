package com.grupoasv.patientmanagement.command;

import com.grupoasv.patientmanagement.command.patient.CreatePatient;
import com.grupoasv.patientmanagement.model.dto.BirthDate;
import com.grupoasv.patientmanagement.model.dto.FullName;
import com.grupoasv.patientmanagement.model.dto.Notes;
import com.grupoasv.patientmanagement.model.dto.Sip;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.rules.ExpectedException.none;

public class CreatePatientTest {
  private static final UUID A_VALID_ID = UUID.randomUUID();
  private static final String A_VALID_SIP = "00000000";
  private static final String A_VALID_NAME = "A name";
  private static final String A_VALID_LAST_NAME = "A last name";
  private static final Date A_VALID_BIRTHDATE = DateTime.now().minusYears(1).withTimeAtStartOfDay().toDate();
  private static final Date AN_INVALID_BIRTHDATE = DateTime.now().plusYears(1).withTimeAtStartOfDay().toDate();
  private static final String SOME_VALID_NOTES = "Some notes";
  private static final String STRING_WITH_BLANKS = "    ";
  private static final String EMPTY_STRING = "";

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_throw_NullPointerException_on_empty_build() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Id cannot be null");

    CreatePatient.builder().build();
  }

  @Test
  public void should_throw_NullPointerException_on_valid_id() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Patient SIP cannot be null");

    CreatePatient.builder().withId(A_VALID_ID).build();
  }

  @Test
  public void should_throw_NullPointerException_when_passing_a_valid_sip() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Patient name cannot be null");

    CreatePatient.builder()
      .withId(A_VALID_ID)
      .withSip(A_VALID_SIP)
      .build();
  }

  @Test
  public void should_throw_NullPointerException_when_passing_a_valid_sip_and_a_valid_name() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Patient birth date cannot be null");

    CreatePatient.builder()
      .withId(A_VALID_ID)
      .withSip(A_VALID_SIP)
      .withName(A_VALID_NAME, A_VALID_LAST_NAME)
      .build();
  }

  @Test
  public void should_go_ok_when_passing_a_valid_sip_and_a_valid_name_and_a_valid_birthdate() {
    CreatePatient createPatient = CreatePatient.builder()
      .withId(A_VALID_ID)
      .withSip(A_VALID_SIP)
      .withName(A_VALID_NAME, A_VALID_LAST_NAME)
      .withBirthdate(A_VALID_BIRTHDATE)
      .build();

    assertEquals(createPatient.getFullName(), new FullName(A_VALID_NAME, A_VALID_LAST_NAME));
    assertEquals(createPatient.getSip(), new Sip(A_VALID_SIP));
    assertEquals(createPatient.getBirthDate(), new BirthDate(A_VALID_BIRTHDATE));
    assertNull(createPatient.getNotes());
  }

  @Test
  public void should_go_ok_when_passing_a_valid_sip_and_a_valid_name_and_a_valid_birthdate_and_valid_notes() {
    CreatePatient createPatient = CreatePatient.builder()
      .withId(A_VALID_ID)
      .withSip(A_VALID_SIP)
      .withName(A_VALID_NAME, A_VALID_LAST_NAME)
      .withBirthdate(A_VALID_BIRTHDATE)
      .withNotes(SOME_VALID_NOTES)
      .build();

    assertEquals(createPatient.getFullName(), new FullName(A_VALID_NAME, A_VALID_LAST_NAME));
    assertEquals(createPatient.getSip(), new Sip(A_VALID_SIP));
    assertEquals(createPatient.getBirthDate(), new BirthDate(A_VALID_BIRTHDATE));
    assertEquals(createPatient.getNotes(), new Notes(SOME_VALID_NOTES));
  }

  @Test
  public void should_fail_on_null_first_name() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a first name");

    CreatePatient.builder().withName(null, A_VALID_LAST_NAME);
  }

  @Test
  public void should_fail_on_empty_first_name() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a first name");

    CreatePatient.builder().withName(EMPTY_STRING, A_VALID_LAST_NAME);
  }

  @Test
  public void should_fail_on_first_name_full_of_blanks() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a first name");

    CreatePatient.builder().withName(STRING_WITH_BLANKS, A_VALID_LAST_NAME);
  }

  @Test
  public void should_fail_on_null_last_name() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a last name");

    CreatePatient.builder().withName(A_VALID_NAME, null);
  }

  @Test
  public void should_fail_on_empty_last_name() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a last name");

    CreatePatient.builder().withName(A_VALID_NAME, EMPTY_STRING);
  }

  @Test
  public void should_fail_on_last_name_full_of_blanks() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("You must provide a last name");

    CreatePatient.builder().withName(A_VALID_NAME, STRING_WITH_BLANKS);
  }

  @Test
  public void ok_on_valid_full_name() {
    CreatePatient.CreatePatientBuilder builder = CreatePatient.builder().withName(A_VALID_NAME, A_VALID_LAST_NAME);

    assertEquals(new FullName(A_VALID_NAME, A_VALID_LAST_NAME), builder.getFullName());
  }

  @Test
  public void fail_on_null_birth_date() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Birth date cannot be null");

    CreatePatient.builder().withBirthdate(null);
  }

  @Test
  public void fail_on_future_birth_date() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Birth date cannot be placed in the future");

    CreatePatient.builder().withBirthdate(AN_INVALID_BIRTHDATE);
  }

  @Test
  public void ok_on_valid_birthdate() {
    CreatePatient.CreatePatientBuilder builder = CreatePatient.builder().withBirthdate(A_VALID_BIRTHDATE);

    assertEquals(builder.getBirthDate(), new BirthDate(A_VALID_BIRTHDATE));
  }

  @Test
  public void fail_on_null_sip() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Sip cannot be null");

    CreatePatient.builder().withSip(null);
  }

  @Test
  public void fail_on_invalid_sip() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Invalid sip");

    CreatePatient.builder().withSip(EMPTY_STRING);
  }

  @Test
  public void ok_on_valid_sip() {
    CreatePatient.CreatePatientBuilder builder = CreatePatient.builder().withSip(A_VALID_SIP);

    assertEquals(new Sip(A_VALID_SIP), builder.getSip());
  }

  @Test
  public void ok_on_valid_notes() {
    CreatePatient.CreatePatientBuilder builder = CreatePatient.builder().withNotes(SOME_VALID_NOTES);

    assertEquals(new Notes(SOME_VALID_NOTES), builder.getNotes());
  }


}