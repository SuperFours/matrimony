package com.matrimony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.UserProfileInterest;

@Repository
public interface UserProfileInterestRepository extends JpaRepository<UserProfileInterest, Integer> {

	List<UserProfileInterest> findAllByInterestMatrimonyIdMatrimonyId(Integer userMatrimonyId);

	Optional<UserProfileInterest> findByInterestMatrimonyIdMatrimonyIdAndLoginMatrimonyIdMatrimonyId(
			Integer matrimonyId, Integer loginId);

}
