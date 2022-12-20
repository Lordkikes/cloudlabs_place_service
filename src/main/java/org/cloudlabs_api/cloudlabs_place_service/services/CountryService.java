package org.cloudlabs_api.cloudlabs_place_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.CountryResponseDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.dtos.TimeZoneDTO;
import org.cloudlabs_api.cloudlabs_place_service.domain.entities.Country;
import org.cloudlabs_api.cloudlabs_place_service.domain.mappers.CountryMapper;
import org.cloudlabs_api.cloudlabs_place_service.domain.repositories.CountryRepository;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.JsonProcessingException;
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
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private LanguageService languageService;

    public Page<CountryResponseDTO> getCountries(HttpServletRequest request) {

        Page<Country> countryPage = countryRepository.findAll(Pageable.unpaged());
        Page<CountryResponseDTO> countryResponseDTOS;

        String language = languageService.getLocale(request);

        ObjectMapper objectMapper = new ObjectMapper();
        if (language.equals("en")) {
            countryResponseDTOS = countryPage.map(country -> getCountryResponseDTO(objectMapper, country));
        } else {
            countryResponseDTOS = countryPage.map(country -> getCountryResponseDTOWithTranslation(language, objectMapper, country));
        }

        return countryResponseDTOS;
    }

    public Page<CountryResponseDTO> getCountries(HttpServletRequest request, ArrayList<Long> ids) {

        List<Country> countryList = countryRepository.findAllById(ids);
        Page<CountryResponseDTO> countryResponseDTOS = null;

        String language = languageService.getLocale(request);

        ObjectMapper objectMapper = new ObjectMapper();
        if (language.equals("en")) {
            countryResponseDTOS = new PageImpl<>(countryList.stream().map(country -> getCountryResponseDTO(objectMapper, country)).collect(Collectors.toList()));
        } else {
            countryResponseDTOS = new PageImpl<>(countryList.stream().map(country -> getCountryResponseDTOWithTranslation(language, objectMapper, country)).collect(Collectors.toList()));
        }

        return countryResponseDTOS;
    }

    private CountryResponseDTO getCountryResponseDTO(ObjectMapper objectMapper, Country country) {
        TimeZoneDTO timeZoneDTO;
        try {
            timeZoneDTO = objectMapper.readValue(country.getTimeZones().replace("[", "").replace("]", ""), TimeZoneDTO.class);
        } catch (Exception e) {
            throw new JsonProcessingException(Problems.JSON_ERROR);
        }
        return countryMapper.countryToCountryResponseDTO(country, timeZoneDTO);
    }

    private CountryResponseDTO getCountryResponseDTOWithTranslation(String language, ObjectMapper objectMapper, Country country) {
        TimeZoneDTO timeZoneDTO;
        Map<String, String> result;
        try {
            result = objectMapper.readValue(country.getTranslations(), Map.class);
            timeZoneDTO = objectMapper.readValue(country.getTimeZones().replace("[", "").replace("]", ""), TimeZoneDTO.class);
        } catch (Exception e) {
            throw new JsonProcessingException(Problems.JSON_ERROR);
        }

        if (result.containsKey(language))
            return countryMapper.countryToCountryResponseDTO(country, result.get(language), timeZoneDTO);
        else
            return countryMapper.countryToCountryResponseDTO(country, timeZoneDTO);
    }
}
