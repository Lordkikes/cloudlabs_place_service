package org.cloudlabs_api.cloudlabs_place_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.CityDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.City;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.State;
import org.cloudlabs_api.cloudlabs_place_service.domain.mappers.CityMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.repositories.CityRepository;
import org.cloudlabs_api.cloudlabs_place_service.domain.repositories.CountryRepository;
import org.cloudlabs_api.cloudlabs_place_service.domain.repositories.StateRepository;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.JsonProcessingException;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.NotFoundException;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private LanguageService languageService;

    public Page<CityDTO> getCities(String languageCode, List<Long> ids) {
        List<City> cities = new ArrayList<>();
        List<CityDTO> cityDTOS;

       cities = cityRepository.findByIdIn(ids);

        String language = languageService.getCode(languageCode);

        if (language.equals("en")) {
            cityDTOS = cities.stream().map(city -> cityMapper.cityToCityDTO(city)).collect(Collectors.toList());
        } else {
            cityDTOS = cities
                    .stream()
                    .map(city ->
                            {
                                Map<String, String> result;
                                try {
                                    result = new ObjectMapper().readValue(countryRepository.findById(city.getCountry().getId()).get().getTranslations(), Map.class);
                                } catch (Exception e) {
                                    throw new JsonProcessingException(Problems.JSON_ERROR);
                                }
                                if (result.containsKey(language))
                                    return cityMapper.cityToCityDTO(city, result.get(language));
                                return cityMapper.cityToCityDTO(city);
                            }
                    ).collect(Collectors.toList());
        }

        return new PageImpl<>(cityDTOS, Pageable.unpaged(), cityDTOS.size());
    }

    public Page<?> getByState(HttpServletRequest request, Long stateId)  {
        List<CityDTO> cityResponseDTOList;

        State state = stateRepository.findById(stateId).
                orElseThrow(() -> new NotFoundException(Problems.STATE_NOT_FOUND));

        String language = languageService.getLocale(request);

        if (language.equals("en")) {
            cityResponseDTOList = cityMapper.citiesToCityDTOs(state.getCities());
        } else {
            Map<String, String> result;
            try {
                result = new ObjectMapper().readValue(state.getCountry().getTranslations(), Map.class);
            } catch (Exception e) {
                throw new JsonProcessingException(Problems.JSON_ERROR);
            }
            if (result.containsKey(language))
                cityResponseDTOList = state.getCities().stream()
                        .map(city -> cityMapper.cityToCityDTO(city, result.get(language))).collect(Collectors.toList());
            else
                cityResponseDTOList = cityMapper.citiesToCityDTOs(state.getCities());
        }

        return new PageImpl<>(cityResponseDTOList);
    }
}
