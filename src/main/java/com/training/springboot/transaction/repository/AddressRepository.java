package com.training.springboot.transaction.repository;

import com.training.springboot.transaction.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
