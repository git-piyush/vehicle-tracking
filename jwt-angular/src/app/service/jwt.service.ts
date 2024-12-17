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
  msg1:any = null;
  constructor(private http: HttpClient, private router: Router) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest)
  }

  login(loginRequest: any) {
    this.http.post(BASE_URL + 'login', loginRequest).subscribe((res:any)=>{
      if(res.jwt!=null){         
          const jwtToken = res.jwt;
          const user = res.user;
          localStorage.setItem('jwt', res.jwt);
          localStorage.setItem('user', res.user);
          localStorage.setItem('userId', res.userId);
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
    if (jwtToken) {
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
    localStorage.removeItem("useId");
    this.router.navigateByUrl("/login");
  }

  loginForm(){
    this.msg1 = null;
  }

  getUserProfileById(): Observable<any>{
    return this.http.get<[]>(BASE_URL+`api/user/profile/${localStorage.getItem('userId')}`,{
      headers: this.createAuhtorizationHeader()
    })
  }

}
