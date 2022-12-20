package org.cloudlabs_api.cloudlabs_place_service.domain.repositories;

import org.cloudlabs_api.cloudlabs_place_service.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
