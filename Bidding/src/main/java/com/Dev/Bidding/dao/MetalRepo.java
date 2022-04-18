package com.Dev.Bidding.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dev.Bidding.model.Metal;

public interface MetalRepo extends JpaRepository<Metal, Integer> {
	
	List<Metal>  findBymetalname(String cmpny);
	

}
