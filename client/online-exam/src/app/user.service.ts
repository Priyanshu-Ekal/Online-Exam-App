import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }
  
  saveUser(user:User){
    return this.httpClient.post("http://localhost:8080/saveUser",user);
  }
  updateUser(user:User){
    return this.httpClient.put("http://localhost:8080/updateUser",user);
  }
  deleteUser(username:string){
    return this.httpClient.delete("http://localhost:8080/deleteUser/"+username);
  }
  getUser(username:string){
    return this.httpClient.get<User>("http://localhost:8080/getUser/"+username);
  }
  getAllUsers(){
    return this.httpClient.get<User[]>("http://localhost:8080/getAllUsers");
  }

  validate(user:User){
    return this.httpClient.post<boolean>("http://localhost:8080/validate", user);
  }
}

export class User{
  username:string;
  password:string;
  mobno:number;
  emailid:string;
  
  constructor(username:string,password:string,mobno:number,emailid:string){
    this.username = username;
    this.password = password;
    this.emailid = emailid;
    this.mobno = mobno;
  }
}
