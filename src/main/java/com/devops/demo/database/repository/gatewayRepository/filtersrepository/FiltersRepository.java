package com.devops.demo.database.repository.gatewayRepository.filtersrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.gateway.FiltersEntity;

@Repository
public interface FiltersRepository extends JpaRepository<FiltersEntity, Long> {
    FiltersEntity findByFilterName(String filterName);
}
