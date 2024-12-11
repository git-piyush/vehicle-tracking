import { Component, OnInit } from '@angular/core';
import { JwtService } from './service/jwt.service';
import { LoginComponent } from './components/login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Vehicle Tracking System';
  isUserLoggedIn = false;
  constructor(public jwtService: JwtService) { }
  ngOnInit(): void {
    const jwtToken = localStorage.getItem("jwtToken");
    const user = localStorage.getItem("user");
    if(jwtToken !=null && user !=null){
      this.isUserLoggedIn = true;
    }
  }
  onLogout(){
    this.jwtService.logout();
  }

}
