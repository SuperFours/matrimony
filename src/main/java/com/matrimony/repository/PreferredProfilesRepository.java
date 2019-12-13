package com.matrimony.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.matrimony.entity.UserProfile;

public interface PreferredProfilesRepository extends CrudRepository<UserProfile,Integer>,JpaSpecificationExecutor<UserProfile>{

}
