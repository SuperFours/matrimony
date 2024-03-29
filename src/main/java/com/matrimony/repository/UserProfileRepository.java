package com.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.UserProfile;

/**
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileRepository is used handle all the JPA inbuilt
 *              operations
 */

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>,JpaSpecificationExecutor<UserProfile> {

	UserProfile findByUserMatrimonyIdMatrimonyId(Integer matrimonyId);

	List<UserProfile> findAllByGender(String gender);

	List<UserProfile> findAll(Specification<UserProfile> specification);

}
