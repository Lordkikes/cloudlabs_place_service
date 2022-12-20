package org.cloudlabs_api.cloudlabs_place_service.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String iso3;

    private String iso2;

    @Column(name = "numeric_code")
    private String numericCode;

    @Column(name = "phonecode")
    private String phoneCode;

    private String capital;

    private String currency;

    private String currencyName;

    private String currencySymbol;

    private String tld;

    @Column(name = "native")
    private String native_;

    private String region;

    private String subregion;

    @Column(name = "timezones")
    private String timeZones;

    private String translations;

    private Double latitude;

    private Double longitude;

    private String emoji;

    @Column(name = "emojiU")
    private String emojiU;

    private Boolean flag;

    @Column(name = "wikidataid")
    private String wikiDataId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "country")
    private List<State> states = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<City> city = new ArrayList<>();

}
