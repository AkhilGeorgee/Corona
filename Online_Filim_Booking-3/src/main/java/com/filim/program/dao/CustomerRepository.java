package com.filim.program.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filim.program.dto.Customers;
@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long>
{
      public Customers findByEmailAndPassword(String email,String password);
}
