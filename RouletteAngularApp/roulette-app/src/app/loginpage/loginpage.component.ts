import { Component, OnInit } from '@angular/core';
import { LoginService } from "../loginservice/login.service";
import {debug, log} from "util";
import {Router} from "@angular/router";

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {

  customers : Object;
  hide: boolean = true;
  loginFlag : boolean= false;
  notFound :boolean = false
  displayBool: boolean=true;
  constructor(private request : LoginService, private router: Router, private loginService: LoginService) { }

  ngOnInit() {

  }

  validateCustomer(customer){
    this.request.loadData(customer).subscribe(data =>{
      this.customers = data;
      localStorage.setItem("customers",JSON.stringify(this.customers));
      this.setPage(this.customers);
    });
    // return this.loginFlag;
  }

  setPage(customer){
    if(!customer){

      this.loginFlag = false;
      console.log("ohoo dhang ki id daal bhai");
      (<HTMLInputElement>(document.getElementById("modalbutton"))).click();
      // this.notFound= true;
      // debugger
      // (<HTMLInputElement>(document.getElementById("valid"))).style.display="block";
      // (<HTMLInputElement>(document.getElementById("valid"))).innerText="Invalid Customer!!";
      // (<HTMLInputElement>(document.getElementById("uuid"))).setCustomValidity("Invalid Customer!!");
      // (<HTMLFormElement>(document.getElementById("form"))).reportValidity();
    } else {
      this.loginFlag = true;
      // console.log(this.loginFlag);
      // console.log(customer);
      this.loginService.changeNav(customer);
      this.router.navigate(["/gamepage"]);
      // (<HTMLInputElement>(document.getElementById("uuid"))).setCustomValidity('');
    }
    console.log(this.loginFlag);
    // return this.loginFlag;
  }


}
