package com.matrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.UserProfileInterest;

@Repository
public interface UserProfileInterestRepository extends JpaRepository<UserProfileInterest, Integer> {


}
