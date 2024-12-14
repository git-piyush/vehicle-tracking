import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  message: string;
  constructor(
    private jwtService: JwtService, private router: Router
  ) { }

  ngOnInit() {
    this.hello();
  }

  hello() {
    this.jwtService.hello().subscribe(
      (response) => {
        console.log(response);
        const jwtToken = localStorage.getItem('jwt');
        const user = localStorage.getItem('user');
        if(jwtToken==null){
          this.router.navigateByUrl("/login");
        }else{
          this.jwtService.session = {username:user};
        }
        this.message = response.message;
      }
    )
  }
}
