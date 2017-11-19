package com.kaushik.CasinoAdmin.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kaushik.CasinoAdmin.Model.Customer;
import com.kaushik.CasinoAdmin.Model.GameResult;
import com.kaushik.CasinoAdmin.Model.Recharge;
import com.kaushik.CasinoAdmin.Repository.CasinoRepository;

@Service
public class CasinoService {
	
	private static final String CHAR_LIST = "abcdefghijk1234567890lmnopqr1234567890stuvwxyz1234567890ABCDEFG1234567890HIJKLMNO1234567890PQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;
	    
	public static String generateRandomString(){
		StringBuffer randStr = new StringBuffer();
	    for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	    	int number = getRandomNumber();
	        char ch = CHAR_LIST.charAt(number);
	        randStr.append(ch);
	    }
	    return randStr.toString();
	 }
	    
	 public static int getRandomNumber() {
	 	 int randomInt = 0;
	     Random randomGenerator = new Random();
	     randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	     if (randomInt - 1 == -1) {
	    	 return randomInt;
	     } else {
	         return randomInt - 1;
	     }
	 }

	@Autowired
	private CasinoRepository repository;
	
	public String error(){
		return "redirect:/Customers";
	}
	
	public String home(){
		return "Customers";
	}
	
	public String registerCustomer(Model model, Customer customer){
		model.addAttribute("customer", customer);
		return "RegisterCustomer";
	}
		
	public String listCustomers(Model model) {
		model.addAttribute("listCustomers", this.repository.findAll());
		return "ListCustomers";
	}
	
	public @ResponseBody Iterable<Customer> getAllCustomers() {
	    return repository.findAll();
	}
	
	public void addCustomer(@ModelAttribute Customer c, Model model, MultipartFile file){
		model.addAttribute("Customer",c);
		c.setBalance("500");
		c.setBlocked("0");
		String uuid = generateRandomString();
		c.setUuid(uuid);
		System.out.println("file being uploaded");
		c.setFilePath(addImage(c, file));
		repository.save(c);
	}
	
	public String addImage(Customer customer, MultipartFile file) {
		String filePath = System.getProperty("user.dir").replaceAll("\\\\" , "/") + "/src/main/IDS/" + customer.getUuid() + ".jpg";
		File saveFile = new File(filePath);
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	public void rechargeCustomer(int id, @RequestBody Recharge recharge){
		Customer c = repository.findOne(id);
		int bl = Integer.parseInt(c.getBalance());
		c.setBalance(bl+recharge.getBalance()+"");
		repository.save(c);
	}
	
	public @ResponseBody Customer findByUuid(String uuid){
		if(repository.findByuuid(uuid)==null){
			System.out.println("ohohohohoho");
			return null;
		}
		return repository.findByuuid(uuid);
	}
	
	public void blockAmount(String uuid, String amount){
		Customer c= repository.findByuuid(uuid);
		c.setBlocked(amount);
		int x=Integer.parseInt(c.getBalance())- Integer.parseInt(c.getBlocked());
		c.setBalance(x+"");
		repository.save(c);
	}
	
	public static int randInt(int min, int max) {
		Random rand =new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	  
	
	public GameResult gameplay(String uuid, String request){
		int random = randInt(0, 36);
		int winningAmount=0;
		int lostAmount=0;
		double arr[]={1.5,1.5,1.5,10,1.25,1.25,1.25,1.25};
		double bets[]=new double[8];
		Customer c= repository.findByuuid(uuid);
		JSONObject object = null;
		try {
			 object = new JSONObject(request);
			System.out.println("josn"+object);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> it = object.keys();
		System.out.println(it.next());
		
		try {
			
			for(int i=1;i<=8;i++){
				if(object.has(i+"")){
					int s = object.getInt(i+"");
					bets[i-1]=s;
				}	
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<8;i++){
			System.out.println(bets[i]);
		}
		if(random>=1 && random<=12){
			winningAmount = winningAmount+(int)(bets[0]*arr[0]);
		}
		if(random>12 && random<=24){
			winningAmount = winningAmount+(int)(bets[1]*arr[1]);
		}
		if(random>24 && random<=36){
			winningAmount = winningAmount+(int)(bets[2]*arr[2]);
		}
		if(random==0){
			winningAmount = winningAmount+(int)(bets[3]*arr[3]);
		}
		if(random>=1 && random<=18){
			winningAmount = winningAmount+(int)(bets[4]*arr[4]);
		}
		if(random>18 && random<=36){
			winningAmount = winningAmount+(int)(bets[5]*arr[5]);
		}
		if(random%2==0){
			winningAmount = winningAmount+(int)(bets[6]*arr[6]);
		}
		if(random%2!=0){
			winningAmount = winningAmount+(int)(bets[7]*arr[7]);
		}
		System.out.println("Random no. is:"+random);
		int x= Integer.parseInt(c.getBalance());
		String result="";
		GameResult gs =new GameResult();
		gs.setRandomNumber(random);
		gs.setUpdateBalance(winningAmount+lostAmount);
//		result = "Random number generated is : "+random+" and the user has won/lost : Rs."+(winningAmount+lostAmount);
		
		c.setBlocked(0+"");
		if(winningAmount+lostAmount>0){
			c.setBalance(x+winningAmount+lostAmount+"");	
		}
		repository.save(c);
		return gs;
	}

}
