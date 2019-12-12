package com.matrimony.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
=======
<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.entity.UserProfile;
<<<<<<< HEAD
=======
/**
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileRepository is used handle all the JPA inbuilt operations */
>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94
>>>>>>> 8f511e1e81669a2aeeb29a6aea8f6491c2335deb

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

<<<<<<< HEAD
=======
	UserProfile findByUserMatrimonyIdMatrimonyId(Integer matrimonyId);
<<<<<<< HEAD
=======
	
	List<UserProfile> findAllByGender(String gender);
>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94
>>>>>>> 8f511e1e81669a2aeeb29a6aea8f6491c2335deb

}
