import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User, UserService } from '../user.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent{
  show:boolean=false;

  user:User = new User('','',0,'');
  users:User[] = [];

  constructor(private userService:UserService){ }

  // After constructor call, ngOnInit() is called immediately, hence we should write
  // extra initialization task in this task
  ngOnInit(): void {
    this.getAllUsers();
  }
  saveUser(){
    this.userService.saveUser(this.user).subscribe(); //subscribe() is must
    console.log("Data with username:"+this.user.username+" is inserted!");
  }

  updateUser(){
    this.userService.updateUser(this.user).subscribe(); 
  }

  deleteUser(){
    this.userService.deleteUser(this.user.username).subscribe(); 
  }

  // (arguments) => function definition ===>> 
  // Arrow function (passing function as an argument - Functional programming)
  getUser(){
    // reference user should point to new  user class object from response 
    this.userService.getUser(this.user.username).subscribe(userobject=>this.user=userobject);
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe(array=>this.users=array);
    this.show=true;
  }
}

