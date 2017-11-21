import {Component, OnInit, Input, ViewChild} from '@angular/core';
import {LoginService} from "../loginservice/login.service";
import {Subscription} from "rxjs/Subscription";
import {GameService} from "../gameservice/game.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gamepage',
  templateUrl: './gamepage.component.html',
  styleUrls: ['./gamepage.component.css']
})
export class GamepageComponent implements OnInit {

  customer: any;
  result: any;
  random: number=0;
  updatebalance: number=0;
  name: string;
  balance: string;
  uuid: string;
  first: number=0;
  second: number=0;
  third: number=0;
  fourth:number=0;
  fifth: number=0;
  sixth: number=0;
  seventh: number=0;
  eighth: number=0;
  send:any;
  bets: number=0;
  flag:boolean =true;
  subscription:Subscription;
  temp: string

  constructor(private loginService:LoginService, private request:GameService, private router: Router) { }

  ngOnInit() {

    if(localStorage.getItem("customers")!=null){
      this.temp = JSON.parse(localStorage.getItem("customers"));
      this.customer = JSON.parse(this.temp.toString());
      console.log(this.customer.name);
      this.name=this.customer.name;
      this.balance = this.customer.balance;
      this.uuid = this.customer.uuid;
    }

    // this.subscription = this.loginService.navItem$.subscribe(res=>{
    // // console.log(res);
    // this.customer = JSON.parse(res.toString());
    // // console.log(this.customer.uuid);
    // this.uuid = this.customer.uuid;
    // // console.log(this.customer.uuid);
    // },err=>{});
  }

  resetFields(){
    (<HTMLInputElement>(document.getElementById("1st12"))).value ="0";
    (<HTMLInputElement>(document.getElementById("2nd12"))).value ="0";
    (<HTMLInputElement>(document.getElementById("3rd12"))).value ="0";
    (<HTMLInputElement>(document.getElementById("0"))).value ="0";
    (<HTMLInputElement>(document.getElementById("1to18"))).value ="0";
    (<HTMLInputElement>(document.getElementById("18to36"))).value ="0";
    (<HTMLInputElement>(document.getElementById("Even"))).value ="0";
    (<HTMLInputElement>(document.getElementById("Odd"))).value ="0";
  }

  resetValues(){
    this.first=0;
    this.second=0;
    this.third=0;
    this.fourth=0;
    this.fifth=0;
    this.sixth=0;
    this.seventh=0;
    this.eighth=0;
    this.bets=0;
  }

  logout(){
    localStorage.clear();
    this.router.navigate(["/loginpage"]);
  }

  blockbets() {
    this.send = {
      1: this.first, 2: this.second,
      3: this.third, 4: this.fourth, 5: this.fifth,
      6: this.sixth, 7: this.seventh, 8: this.eighth
    };

    for (var key in this.send) {

      if (this.send.hasOwnProperty(key)) {
        if (this.send[key] != null) {
          // console.log(this.send[key]);
          if (this.send[key] % 500 != 0) {
            this.resetFields();
            this.resetValues();
            this.flag = false;
            (<HTMLInputElement>(document.getElementById("invalidbetbutton"))).click();
            break;
          } else {
            this.bets += this.send[key];
          }
        }
      }
    }
    console.log("BALANCE------"+this.customer.balance);
    console.log("Bets placed ------" + this.bets);
    if (this.customer.balance - this.bets >= 0 && this.flag) {
      this.request.postblockData(this.customer.uuid, this.bets).subscribe(data => {
        console.log("Blocked amount :------" + this.bets);
        console.log("request gai block ki");
        this.resetValues();
        this.resetFields();
      });
      (<HTMLInputElement>(document.getElementById("spin"))).click();
    } else if (this.customer.balance - this.bets < 0) {
      this.bets = 0;
      this.resetFields();
      this.resetValues();
      (<HTMLInputElement>(document.getElementById("modalbutton"))).click();
      console.log("bhai apna balance to dekh le");
    } else {
      this.flag = true;
    }

  }

  showresult(){
    this.request.loadData(this.customer.uuid).subscribe(data =>{
      console.log(data);
      console.log("refresh request bhi gai");
      localStorage.removeItem("customers");
      localStorage.setItem("customers", JSON.stringify(data));
      window.location.reload();
    });
    //
    // console.log("calling ngonit");
    // this.ngOnInit();
  }

  play(){
    console.log("BETS :"+this.bets);
    console.log("customer balance"+this.customer.balance);
    this.request.postData(this.customer.uuid, this.send).subscribe(data => {
      console.log(this.send);
      console.log("request gai game ki");
      console.log(data);
      this.result = JSON.parse(data);
      this.random = this.result.randomNumber;
      this.updatebalance = this.result.updateBalance;
    });
    // this.request.loadData(this.customer.uuid).subscribe(data =>{
    //   console.log("refresh request bhi gai");
    // });
    this.resetValues();
    this.resetFields();

    (<HTMLInputElement>(document.getElementById("result"))).click();
  //   this.result = JSON.parse(.toString());
  //   console.log(this.customer.uuid);
  //   // this.uuid = this.customer.uuid;
  //   // console.log(this.customer.uuid);
  // }
  }

}
