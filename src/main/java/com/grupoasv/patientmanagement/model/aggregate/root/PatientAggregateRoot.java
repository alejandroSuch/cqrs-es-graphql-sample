package com.grupoasv.patientmanagement.model.aggregate.root;

import com.grupoasv.patientmanagement.event.patient.PatientBirthDateChanged;
import com.grupoasv.patientmanagement.event.patient.PatientCreated;
import com.grupoasv.patientmanagement.event.patient.PatientDeleted;
import com.grupoasv.patientmanagement.event.patient.PatientNameChanged;
import com.grupoasv.patientmanagement.event.patient.PatientNotesChanged;
import com.grupoasv.patientmanagement.event.patient.PatientSipChanged;
import com.grupoasv.patientmanagement.event.patient.contactdata.PatientContactDataAdded;
import com.grupoasv.patientmanagement.event.patient.contactdata.PatientContactDataDeleted;
import com.grupoasv.patientmanagement.event.patient.contactdata.PatientContactDataUpdated;
import com.grupoasv.patientmanagement.exception.patient.contactdata.ContactDataAlreadyExistsException;
import com.grupoasv.patientmanagement.exception.patient.contactdata.PatientContactDataNotFoundException;
import com.grupoasv.patientmanagement.model.aggregate.PatientAddressAggregate;
import com.grupoasv.patientmanagement.model.aggregate.PatientContactData;
import com.grupoasv.patientmanagement.model.dto.BirthDate;
import com.grupoasv.patientmanagement.model.dto.ContactData;
import com.grupoasv.patientmanagement.model.dto.FullName;
import com.grupoasv.patientmanagement.model.dto.Notes;
import com.grupoasv.patientmanagement.model.dto.Patient;
import com.grupoasv.patientmanagement.model.dto.Sip;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PATIENT")
public class PatientAggregateRoot extends AggregateRoot {
  @Embedded
  Patient patient = new Patient();

  @OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true)
  @JoinColumn(name = "patientId", referencedColumnName = "id")
  List<PatientContactData> contactData = new ArrayList<>();

  @OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true)
  @JoinColumn(name = "patientId", referencedColumnName = "id")
  List<PatientAddressAggregate> addresses = new ArrayList<>();

  public PatientAggregateRoot(UUID id) {
    this.apply(new PatientCreated(id));
  }

  private void apply(PatientCreated event) {
    this.clearEvents();
    this.register(event);

    this.setId(event.getAggregateId());
    this.setDeleted(FALSE);
  }

  public String getSip() {
    return this.patient.getSip().getValue();
  }

  public void setSip(Sip sip) {
    this.apply(new PatientSipChanged(this.getId(), sip));
  }


  private void apply(PatientSipChanged event) {
    if (event.getSip().equals(this.patient.getSip())) {
      return;
    }

    this.register(event);
    this.patient.setSip(event.getSip());
  }

  public FullName getFullName() {
    return this.patient.getFullName();
  }

  public String getFirstName() {
    return this.patient.getFullName().getFirstName();
  }

  public String getLastName() {
    return this.patient.getFullName().getLastName();
  }

  public void setFullName(FullName fullName) {
    this.apply(new PatientNameChanged(this.getId(), fullName));
  }

  private void apply(PatientNameChanged event) {
    if (event.getFullName().equals(this.patient.getFullName())) {
      return;
    }

    this.register(event);
    this.patient.setFullName(event.getFullName());
  }

  public Date getBirthDate() {
    return this.patient.getBirthDate().getValue();
  }

  public void setBirthDate(BirthDate birthDate) {
    this.apply(new PatientBirthDateChanged(this.getId(), birthDate));
  }

  private void apply(PatientBirthDateChanged event) {
    if (event.getBirthDate().equals(this.patient.getBirthDate())) {
      return;
    }

    this.register(event);
    this.patient.setBirthDate(event.getBirthDate());
  }

  public String getNotes() {
    return Optional.ofNullable(this.patient.getNotes())
      .orElseGet(Notes::new)
      .getValue();
  }

  public void setNotes(Notes notes) {
    this.apply(new PatientNotesChanged(this.getId(), notes));
  }

  private void apply(PatientNotesChanged event) {
    if (event.getNotes().equals(this.patient.getNotes())) {
      return;
    }

    this.register(event);
    this.patient.setNotes(event.getNotes());
  }

  public void delete() {
    this.apply(new PatientDeleted(this.getId()));
  }

  private void apply(PatientDeleted event) {
    this.setDeleted(TRUE);
    this.register(event);
  }

  public void addContactData(ContactData contactData, UUID id) {
    this.apply(new PatientContactDataAdded(id, this.getId(), contactData));
  }

  private void apply(PatientContactDataAdded event) {
    assertThatContactDataValueIsNotDuplicated(event);

    this.register(event);
    this.contactData.add(new PatientContactData(event.getAggregateId(), event.getContactData()));
  }

  private void assertThatContactDataValueIsNotDuplicated(PatientContactDataAdded event) {
    final String value = event.getContactData().getValue();

    if (getContactData().stream().filter(contactData -> contactData.getValue().equals(value)).count() > 0) {
      throw new ContactDataAlreadyExistsException(this.getId(), value);
    }
  }

  public void deleteContactData(UUID id) {
    this.apply(new PatientContactDataDeleted(id, this.getId()));
  }

  private void apply(PatientContactDataDeleted event) {
    final Predicate<PatientContactData> byIdNotMatching = it -> !it.getId().equals(event.getAggregateId());

    final List<PatientContactData> filteredList = this.contactData.stream().filter(byIdNotMatching).collect(toList());


    if (this.getContactData().size() != filteredList.size()) {
      this.getContactData().clear();
      this.getContactData().addAll(filteredList);
      this.register(event);
    }
  }

  public void updateContactData(ContactData contactData, UUID id) {
    this.apply(new PatientContactDataUpdated(id, this.getId(), contactData));
  }

  private void apply(PatientContactDataUpdated event) {
    final PatientContactData patientContactData = this.contactData.stream()
      .filter(it -> it.getId().equals(event.getAggregateId()))
      .findFirst()
      .orElseThrow(() -> new PatientContactDataNotFoundException(event.getAggregateId()));

    if (!patientContactData.getContactData().equals(event.getContactData())) {
      patientContactData.setContactData(event.getContactData());
    }
  }
}
