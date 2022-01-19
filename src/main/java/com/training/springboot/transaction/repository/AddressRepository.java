package com.training.springboot.transaction.repository;

import com.training.springboot.transaction.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    //TODO: find primary address
    Address findByPrimaryAddressIs(Boolean primaryFlag);

    //TODO: search by string in addressLine1 and addressLine2
    @Query("SELECT f FROM Address f WHERE LOWER(concat(f.namaPenerima, ' ', f.addressLine1, ' ', f.addressLine2, ' ', f.kodePos)) like LOWER(concat('%', :addressSearch, '%'))")
    Page<Address> searchAddress(Pageable pageable, @Param("addressSearch") String addressSearch);
}
