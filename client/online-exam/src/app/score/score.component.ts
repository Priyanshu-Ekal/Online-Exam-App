import { Component, OnInit } from '@angular/core';
import { Answer, QuestionService, Result } from '../question.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-score',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './score.component.html',
  styleUrl: './score.component.css'
})
export class ScoreComponent implements OnInit {
  score:any=0;
  // show:boolean=false;

  allAnswers:Answer[]=[];
  resultObj:Result=new Result('','',0);
  subject:any;
  username:any;

  constructor(private questionService:QuestionService){
    this.subject=sessionStorage.getItem("subject");
    this.username=sessionStorage.getItem("username");
  }

  ngOnInit(): void {
      this.questionService.allAnswers().subscribe(answerarray=>{
        this.allAnswers=answerarray;
        // if(answerarray.length!=0)
        //   this.show=true;
      });

      this.questionService.calculateScore().subscribe(score=>{
        this.score=score;
        this.resultObj.username=this.username;
        this.resultObj.subject=this.subject;
        this.resultObj.score=score;
        // this.questionService.saveResult(this.resultObj).subscribe();
      });
  }  

  getColor(submittedAnswer:string, originalAnswer:string){
    if(submittedAnswer==originalAnswer)
      return "green";
    else  
      return "red";
  }
}
