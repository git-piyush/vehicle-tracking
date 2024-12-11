import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';

const BASE_URL = ["http://localhost:8080/"]

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  session: any = null;
  constructor(private http: HttpClient, private router: Router) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest)
  }

  login(loginRequest: any) {
    this.http.post(BASE_URL + 'login', loginRequest).subscribe((res:any)=>{
      if(res.jwt!=null){
          const jwtToken = res.jwt;
          const user = res.user;
          localStorage.setItem('jwt', jwtToken);
          localStorage.setItem('user', user);
          this.session = {username:user};
          this.router.navigateByUrl("/dashboard");
      }
    })
    
  }

  hello(): Observable<any> {
    return this.http.get(BASE_URL + 'api/hello', {
      headers: this.createAuhtorizationHeader()
    })
  }

  private createAuhtorizationHeader() {
    const jwtToken = localStorage.getItem('jwt');
    const user = localStorage.getItem('user');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      console.log("user found in local storage", user);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found in local storage");
    }
    return null;
  }

  logout(){
    this.session = null;
    localStorage.removeItem("jwt");
    localStorage.removeItem("user");
    this.router.navigateByUrl("/login");
  }

}
