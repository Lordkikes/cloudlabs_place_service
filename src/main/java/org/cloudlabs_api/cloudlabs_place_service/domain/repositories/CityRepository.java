package org.cloudlabs_api.cloudlabs_place_service.domain.repositories;

import org.cloudlabs_api.cloudlabs_place_service.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByIdIn(List<Long> ids);
}
