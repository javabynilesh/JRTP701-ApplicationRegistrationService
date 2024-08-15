package com.nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.entity.CitizenAppRegistrationEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenAppRegistrationEntity, Integer> {

}
