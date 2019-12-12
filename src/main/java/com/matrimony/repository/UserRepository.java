package com.matrimony.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByPhoneNumberAndPassword(String phoneNumber, String password);

	Optional<User> findByPhoneNumber(String phoneNumber);

	Optional<User> findByMatrimonyId(Integer matrimonyId);
}
