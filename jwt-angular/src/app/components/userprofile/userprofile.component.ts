import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Userprofile } from 'src/app/models/userprofile';
import { JwtService } from 'src/app/service/jwt.service';
@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.scss']
})
export class UserprofileComponent implements OnInit{
  userName: any;
  userId: any;
  userProfile: any;
  correctPercentage!: number;
  userprofile1:Userprofile=null;
  constructor(private router: Router, private jwtService: JwtService) { }
  ngOnInit(): void {
    this.correctPercentage = 50;
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken==null){
      this.router.navigateByUrl("/login");
    }else{
      const user = localStorage.getItem("user");
      this.jwtService.session = {username:user};
      this.userName = localStorage.getItem('user');
      this.userId = localStorage.getItem('userId');
      this.getUserProfileById();
      this.router.navigateByUrl("/profile");
    }
  }
  getUserProfileById(){
    this.jwtService.getUserProfileById().subscribe((res)=>{
      console.log(res);
      this.userProfile = res;
      this.userprofile1=res;
    })
  }

  updateProfile(userId: any) {
    console.log('Userid '+userId);
    this.router.navigateByUrl("/updateprofile");
    }
}


