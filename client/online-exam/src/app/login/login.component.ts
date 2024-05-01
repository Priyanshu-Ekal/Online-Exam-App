import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../user.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestionService } from '../question.service';
import { Admin, AdminService } from '../admin.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit
{

  subjects:string[]=[];

  subject:string='';

  user:User=new User('','',0,'');

  
  admin:Admin=new Admin('','');

  message:string="";

  constructor(private userService:UserService,private router:Router,private questionService:QuestionService,private adminService:AdminService)
  {

  }
  ngOnInit(): void
   {
      this.questionService.getAllSubjects().subscribe(array=>this.subjects=array);
   }

  validate()
  {
      this.userService.validate(this.user).subscribe(response=>{
        
        if(response)
          {
                          
            sessionStorage.setItem("message","welcome " + this.user.username);
            
            sessionStorage.setItem("subject",this.subject);
            
            this.router.navigateByUrl("question");

          }

        else
        {
          this.message="invalid credentials";
          this.router.navigateByUrl("login");

        }


        }
      
      );
  }

  validate2()
  {
    this.admin.username=this.user.username;
    this.admin.password=this.user.password;

      this.adminService.validate2(this.admin).subscribe(answer=>{if(answer)
      {
        this.router.navigate(['admindashboard']);
      }
    
      else
      {
        this.router.navigate(['login']);
        this.message="invalid credentials";
      }
    });
  }


}
