package org.cloudlabs_api.cloudlabs_place_service.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponseDTO {
    private String id;
    private String name;
    private String iso3;
    private String iso2;
    private String numericCode;
    private String phoneCode;
    private String capital;
    private String currency;
    private String currencyName;
    private String currencySymbol;
    private String tld;
    @JsonProperty("native")
    private String native_;
    private String region;
    private String subregion;
    @JsonProperty("timezones")
    private TimeZoneDTO timeZones;
    private Double latitude;
    private Double longitude;
    private String emoji;
    private String emojiU;
}
