package com.dbmicrotech.johnlewisstore.repository;

import com.dbmicrotech.johnlewisstore.model.Customer;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}

