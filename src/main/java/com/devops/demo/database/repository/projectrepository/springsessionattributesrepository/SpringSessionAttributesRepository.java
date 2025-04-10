package com.devops.demo.database.repository.projectrepository.springsessionattributesrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devops.demo.database.entity.project.SpringSessionAttributesEntity;
import com.devops.demo.database.entity.project.SpringSessionAttributesEntity.SpringSessionAttributesId;

@Repository
public interface SpringSessionAttributesRepository extends JpaRepository<SpringSessionAttributesEntity, SpringSessionAttributesId> {
}