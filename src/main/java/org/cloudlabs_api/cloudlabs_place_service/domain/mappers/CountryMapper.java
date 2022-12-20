package org.cloudlabs_api.cloudlabs_place_service.domain.mappers;

import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.CountryResponseDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.TimeZoneDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mappings({
            @Mapping(source = "timeZones", target = "timeZones")
    })
    CountryResponseDTO countryToCountryResponseDTO(Country country, TimeZoneDTO timeZones);

    @Mappings({
            @Mapping(source = "timeZones", target = "timeZones"),
            @Mapping(source = "name", target = "name")
    })
    CountryResponseDTO countryToCountryResponseDTO(Country country, String name, TimeZoneDTO timeZones);

}
