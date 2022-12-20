package org.cloudlabs_api.cloudlabs_place_service.domain.repositories;

import org.cloudlabs_api.cloudlabs_place_service.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
