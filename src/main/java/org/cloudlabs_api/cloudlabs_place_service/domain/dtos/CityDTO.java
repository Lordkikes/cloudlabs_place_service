package org.cloudlabs_api.cloudlabs_place_service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;
    private String name;
    private Long stateId;
    private String stateCode;
    private String stateName;
    private Long countryId;
    private String countryCode;
    private String countryName;
    private Double latitude;
    private Double longitude;
    private String wikiDataId;
}
