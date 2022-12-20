package org.cloudlabs_api.cloudlabs_place_service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateResponseDTO {
    private Long id;
    private String name;
    private Long countryId;
    private String countryCode;
    private String countryName;
    private String stateCode;
    private Double latitude;
    private Double longitude;
}
