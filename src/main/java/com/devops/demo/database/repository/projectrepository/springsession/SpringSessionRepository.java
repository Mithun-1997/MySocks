package com.devops.demo.database.repository.projectrepository.springsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.project.SpringSessionEntity;



@Repository
public interface SpringSessionRepository extends JpaRepository<SpringSessionEntity, Long> {
}
