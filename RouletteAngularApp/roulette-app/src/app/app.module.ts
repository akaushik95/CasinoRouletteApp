import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { GamepageComponent } from './gamepage/gamepage.component';

import { LoginService } from "./loginservice/login.service";
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {GameService} from "./gameservice/game.service";


@NgModule({
  declarations: [
    AppComponent,
    LoginpageComponent,
    GamepageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'gamepage',
        component: GamepageComponent
      },
      {
        path: '',
        component: LoginpageComponent
      },
      {
        path:'**',
        component: LoginpageComponent
      }
    ])
  ],
  providers: [LoginService, GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
