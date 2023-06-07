package com.filim.program.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filim.program.dto.DateOperation;
@Repository
public interface DateOperationRepository extends JpaRepository<DateOperation,Long>
{

}
