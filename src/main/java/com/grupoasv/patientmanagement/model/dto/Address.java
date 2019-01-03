package com.grupoasv.patientmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  @Embedded
  LatLng latLng;

  String street;
  String streetNumber;
  String postalCode;

  String block;
  String stair;
  String floor;
  String door;

  String others;

  String locality;
  String province;
  String region;
  String country;
}
