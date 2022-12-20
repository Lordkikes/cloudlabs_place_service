package org.cloudlabs_api.cloudlabs_place_service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDTO {
    private String zoneName;
    private String gmtOffset;
    private String gmtOffsetName;
    private String abbreviation;
    private String tzName;
}
