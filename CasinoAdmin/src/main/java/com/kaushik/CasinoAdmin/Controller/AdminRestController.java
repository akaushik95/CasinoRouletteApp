package com.kaushik.CasinoAdmin.Controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaushik.CasinoAdmin.Model.Customer;
import com.kaushik.CasinoAdmin.Model.GameResult;
import com.kaushik.CasinoAdmin.Service.CasinoService;

@CrossOrigin(origins = "*")
@RestController
public class AdminRestController {

	@Autowired
	private CasinoService service;
	
	@CrossOrigin
	@GetMapping("/allcustomers")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
	    return service.getAllCustomers();
	}
	
	@CrossOrigin
	@GetMapping("/uniquecustomer/{uuid}")
	public @ResponseBody Customer findByUuid(@PathVariable String uuid) {
	    return service.findByUuid(uuid);
	}
	
	@CrossOrigin
	@PostMapping("/blockamount/{uuid}/{amount}")
	public void blockAmount(@PathVariable String uuid, @PathVariable String amount) {
	    service.blockAmount(uuid,amount);
	}
	
	@CrossOrigin
	@PostMapping("/gameplay/{uuid}")
	public GameResult gameplay(@PathVariable String uuid, @RequestBody String request){
		return service.gameplay(uuid, request);
		
	}
}
