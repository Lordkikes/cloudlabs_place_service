package org.cloudlabs_api.cloudlabs_place_service.domain.mappers;

import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.CityDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mappings({
            @Mapping(source = "city.state.id", target = "stateId"),
            @Mapping(source = "city.state.name", target = "stateName"),
            @Mapping(source = "city.country.id", target = "countryId"),
            @Mapping(source = "city.state.countryCode", target = "countryCode"),
            @Mapping(source = "city.state.country.name", target = "countryName")
    })
   CityDTO cityToCityDTO(City city);

    @Mappings({
            @Mapping(source = "city.state.id", target = "stateId"),
            @Mapping(source = "city.state.name", target = "stateName"),
            @Mapping(source = "city.country.id", target = "countryId")
    })
    CityDTO cityToCityDTO(City city, String countryName);

    List<CityDTO> citiesToCityDTOs(List<City> states);
}
