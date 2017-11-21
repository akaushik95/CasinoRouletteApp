import { Injectable } from '@angular/core';
import { Http, Headers } from "@angular/http";
import 'rxjs/add/operator/map';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

const BASE_URL = "http://localhost:8081/gameplay/";
const BASE_URL1 = "http://localhost:8081/blockamount/";
const BASE_URL2 = "http://localhost:8081/uniquecustomer/";

const header = {headers: new Headers({'Content-Type': 'application/json'})};

@Injectable()
export class GameService {

  private customer:object;

  constructor(private http: Http) { }

  postData(uuid, data){
    console.log(BASE_URL+uuid);
    return this.http.post(BASE_URL+uuid, data, header).map(res => res.text());
  }

  postblockData(uuid, data){
    console.log(BASE_URL1+uuid+"/"+data);
    return this.http.post(BASE_URL1+uuid+"/"+data, data,header).map(res => res.text());
  }

  loadData(customer){
    console.log(BASE_URL+customer);
    return this.http.get(BASE_URL2+customer).map(res => res.text());
  }
}
