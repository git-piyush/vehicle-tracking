import { Component, OnInit } from '@angular/core';
import { JwtService } from './service/jwt.service';
import { LoginComponent } from './components/login/login.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Vehicle Tracking System';
  isUserLoggedIn = false;
  loggedinUser = null;
  constructor(public jwtService: JwtService, private router: Router) { }
  ngOnInit(): void {
    const jwtToken = localStorage.getItem("jwtToken");
    const user = localStorage.getItem("user");
    this.loggedinUser = user;
    if(jwtToken !=null && user !=null){
      this.isUserLoggedIn = true;
    }
  }

  onLogin(){
    this.jwtService.msg1 = null;
    //this.jwtService.loginForm();
    this.router.navigateByUrl("/login");
  }

  onLogout(){
    this.jwtService.logout();
  }

  showProfile(email: any){
      this.router.navigateByUrl("/profile");  
  }

}
