package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepo extends JpaRepository<DeliveryAddress,Long> {
    List<DeliveryAddress> findByUserUserId(Long userId);
}
