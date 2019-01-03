package com.grupoasv.patientmanagement.model.aggregate;

import com.grupoasv.patientmanagement.model.dto.Address;
import com.grupoasv.patientmanagement.model.dto.FleetName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class AddressAggregate extends Aggregate {
  @Type(type = "uuid-char")
  UUID fleetId;

  @Embedded
  Address address;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "FLEET_NAME"))
  })
  FleetName fleetName;

  @Column
  Boolean needsGeolocation;
}
