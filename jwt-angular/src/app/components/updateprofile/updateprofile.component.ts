import { Component, OnInit } from '@angular/core';
import {MatSliderModule} from '@angular/material/slider';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.scss']
})
export class UpdateprofileComponent implements OnInit{

  constructor(private router: Router, private jwtService: JwtService) { }
  ngOnInit(): void {

    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken==null){
      this.router.navigateByUrl("/login");
    }else{
      const user = localStorage.getItem("user");
      this.jwtService.session = {username:user};
      this.router.navigateByUrl("/updateprofile");
    }
  }
  formatLabel(value: number): string {
    if (value >= 10) {
      return Math.round(value / 2) + '';
    }
    return `${value}`;
  }

}
