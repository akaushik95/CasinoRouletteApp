package com.kaushik.CasinoAdmin.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kaushik.CasinoAdmin.Model.Customer;
import com.kaushik.CasinoAdmin.Model.Recharge;
import com.kaushik.CasinoAdmin.Service.CasinoService;


@CrossOrigin(origins = "*")
@Controller
public class CasinoController implements ErrorController {

	@Autowired
	private CasinoService service;
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/home";
	}
	
	@CrossOrigin
	@GetMapping({"/error"})
	public String error(){
		return service.error();
	}
	
	@CrossOrigin
	@GetMapping({"/","/home","/Customers"})
	public String home(){
		return service.home();
	}

	@CrossOrigin
	@GetMapping("/RegisterCustomer")
	public String registerCustomer(Model model, Customer customer){
		return service.registerCustomer(model, customer);
	}
	
	@CrossOrigin
	@GetMapping(value = "/ListCustomers")
	public String listCustomers(Model model) {
		return service.listCustomers(model);
	}
	
//	@GetMapping("/yoyo")
//	public @ResponseBody Iterable<Customer> getAllCustomers() {
//	    return service.getAllCustomers();
//	}
	
	@CrossOrigin
	@PostMapping(value= "/customer/add")
	public String addCustomer(@ModelAttribute Customer c, Model model){
		service.addCustomer(c, model);
		return "redirect:/RegisterCustomer";
		
	}
	
	@CrossOrigin
	@PostMapping(value= "/recharge/{id}")
	public String rechargeCustomer(@PathVariable("id") int id, @ModelAttribute("recharge") Recharge recharge){	
		
		service.rechargeCustomer(id, recharge);
		return "redirect:/ListCustomers";
		
	}

	
	
}
