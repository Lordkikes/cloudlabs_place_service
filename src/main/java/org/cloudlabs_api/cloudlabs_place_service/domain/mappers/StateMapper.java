package org.cloudlabs_api.cloudlabs_place_service.domain.mappers;

import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.StateResponseDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapper {

    List<StateResponseDTO> statesToStateResponseDTOs(List<State> states);


    @Mappings({
            @Mapping(source = "state.country.id", target = "countryId"),
            @Mapping(source = "state.country.name", target = "countryName"),
            @Mapping(source = "state.iso2", target = "stateCode")
    })
    StateResponseDTO stateToStateResponseDTO(State state);


    @Mappings({
            @Mapping(source = "state.country.id", target = "countryId"),
            @Mapping(source = "state.iso2", target = "stateCode")
    })
    StateResponseDTO stateToStateResponseDTO(State state, String countryName);
}
