package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepo extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findByUserUserId(Long userId);
}
