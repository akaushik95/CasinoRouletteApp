import {Injectable, Input} from '@angular/core';
import { Http, Headers } from "@angular/http";
import 'rxjs/add/operator/map';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

const BASE_URL = "http://localhost:8081/uniquecustomer/";

const header = {headers: new Headers({'Content-Type': 'application/json'})};

@Injectable()
export class LoginService {
  // @Input() user: string;

  constructor(private http: Http) { }

  private customer:object;
  private _navItemSource = new BehaviorSubject<object>(this.customer);
  navItem$ = this._navItemSource.asObservable();

  changeNav(data) {
    this._navItemSource.next(data);
  }

  loadData(customer){
    console.log(BASE_URL+customer);
    return this.http.get(BASE_URL+customer).map(res => res.text());
  }



}
