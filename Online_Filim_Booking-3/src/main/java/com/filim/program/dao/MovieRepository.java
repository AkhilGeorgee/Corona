package com.filim.program.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filim.program.dto.MovieDetails;

public interface MovieRepository extends JpaRepository<MovieDetails,Long> 
{

}
