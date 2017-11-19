package com.kaushik.CasinoAdmin.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kaushik.CasinoAdmin.Model.Customer;

@Repository
public interface CasinoRepository extends CrudRepository<Customer, Integer>{
	
	public Customer findByuuid(String uuid);
	
	public Customer findByName(String name);
	
}
