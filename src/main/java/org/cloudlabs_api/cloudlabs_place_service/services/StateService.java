package org.cloudlabs_api.cloudlabs_place_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.StateResponseDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.Country;
import org.cloudlabs_api.cloudlabs_place_service.domain.mappers.StateMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.repositories.CountryRepository;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.JsonProcessingException;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.NotFoundException;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StateService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateMapper stateMapper;
    @Autowired
    private LanguageService languageService;

    public Page<?> getByCountry(HttpServletRequest request, Long countryId) {

        List<StateResponseDTO> stateResponseDTOList;

        Country country = countryRepository.findById(countryId).
                orElseThrow(() -> new NotFoundException(Problems.COUNTRY_NOT_FOUND));

        String language = languageService.getLocale(request);

        if (language.equals("en")) {
            stateResponseDTOList = stateMapper.statesToStateResponseDTOs(country.getStates());
        } else {
            Map<String, String> result;
            try {
                result = new ObjectMapper().readValue(country.getTranslations(), Map.class);
            } catch (Exception e) {
                throw new JsonProcessingException(Problems.JSON_ERROR);
            }
            if (result.containsKey(language))
                stateResponseDTOList = country.getStates().stream()
                        .map(state -> stateMapper.stateToStateResponseDTO(state, result.get(language))).collect(Collectors.toList());
            else
                stateResponseDTOList = stateMapper.statesToStateResponseDTOs(country.getStates());
        }

        return new PageImpl<>(stateResponseDTOList);
    }
}
