import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';
@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.scss']
})
export class UserprofileComponent implements OnInit{
  correctPercentage!: number;
  constructor(private router: Router, private jwtService: JwtService) { }
  ngOnInit(): void {
    this.correctPercentage = 50;
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken==null){
      this.router.navigateByUrl("/login");
    }else{
      const user = localStorage.getItem("user");
      this.jwtService.session = {username:user};
      this.router.navigateByUrl("/profile");
    }
  }
}
