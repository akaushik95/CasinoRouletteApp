package com.kaushik.RouletteBackend.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@CrossOrigin(origins = "*")
@RestController
public class RouletteRestController {

	@GetMapping("/allcustomers")
	private static String getCustomers(){
	    final String uri = "http://localhost:8080/allcustomers";
//	    System.out.println("URI-----------------"+uri);
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    return result;
	}
	
	
	@GetMapping("/uniquecustomer/{uuid}")
	private static String getCustomerbyUUID(@PathVariable String uuid){
	    final String uri = "http://localhost:8080/uniquecustomer/"+uuid;
//	    System.out.println("URI------------------"+ uri);
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    return result;
	}
	
	@PostMapping("/blockamount/{uuid}/{amount}")
	private static void blockAmount(@PathVariable String uuid, @PathVariable String amount){
	    final String uri = "http://localhost:8080/blockamount/"+uuid+"/"+amount;
//	    Map<String, String> params = new HashMap<String, String>();
	    System.out.println("URI------------------"+ uri);
	    RestTemplate restTemplate = new RestTemplate();
//	    restTemplate.put(uri, null, params);
	    String result = restTemplate.postForObject(uri, null,String.class);
	    System.out.println(result);
	}
	
	@PostMapping("/gameplay/{uuid}")
	private static String gamePlay(@PathVariable String uuid, @RequestBody String request){
	    final String uri = "http://localhost:8080/gameplay/"+uuid;
//	    System.out.println("||||||||||||||||||||||||||"+request+"|||||||||||||||||||||||||||");
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String result = restTemplate.postForObject(uri, request,String.class);
	    System.out.println(result);
	    return result;
	}
}
