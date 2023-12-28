package com.pijush.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pijush.springbatch.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
