import { Component, OnInit } from '@angular/core';
import { Answer, Question, QuestionService } from '../question.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-question',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './question.component.html',
  styleUrl: './question.component.css'
})
export class QuestionComponent implements OnInit {
  subject:any="";
  username:any="";
  selected=false;
  durationMsg="";
  remainingTime=50;
  submittedAnswer:string="";

  x:any="";
  maxtime:any="";
  questionNumber:number=0;
  flag:boolean=false;

  question:Question = new Question(0,'','','','','','','');
  allqnos:number[] = [];
  answer:Answer = new Answer(0,'','','');
  allAnswers:Answer[]=[];

  constructor(private questionService:QuestionService, private router:Router){
    this.subject = sessionStorage.getItem("subject");
    this.username = sessionStorage.getItem("username");
  }

  ngOnInit(): void {
      this.questionService.getFirstQuestion(this.subject).subscribe(question=>this.question=question);
      this.questionService.getAllQuestions(this.subject).subscribe(allqnos=>this.allqnos=allqnos);

      setInterval(()=>{
        this.remainingTime=this.remainingTime-1;
        let minute = Math.floor(this.remainingTime/60);
        let seconds = this.remainingTime%60;
        this.durationMsg = "Time remaining is: "+minute+":"+seconds;
        if(this.remainingTime==0)
          this.endexam();
  
      },1000);
  }

  nextQuestion(){
    clearInterval(this.x);
    this.selected=false;
    if(this.flag!=true)
      this.questionService.nextQuestion().subscribe(question=>this.question=question);
  }

  decreaseTime(){
    this.maxtime=10;

    this.x=setInterval(()=>{
      this.maxtime--;
      console.log(this.maxtime);
      if(this.maxtime==0)
        this.nextQuestion();
    },1000);
  }

  getQuestion(eventobject:any){
      let questionNumber=eventobject.target.value; // target will give source of event
      console.log("selected question number is " + questionNumber);
      this.questionService.getQuestion(questionNumber).subscribe(question=>this.question=question);
  }

  previousQuestion(){
    this.selected=false;
    this.questionService.allAnswers().subscribe(answerarray=>this.allAnswers=answerarray);
    
    if(this.flag!=true)
      this.questionService.previousQuestion().subscribe(question=>this.question=question);
  }

  getColor(currentOption:string){
      for (let index = 0; index < this.allAnswers.length; index++) 
      {
        let answer = this.allAnswers[index];
        if(answer.qno==this.question.qno && answer.submittedAnswer==currentOption)
          return "green";
      }    
      return "red";
  }
  isChecked(currentOption:string){   
      for (let index = 0; index < this.allAnswers.length; index++) 
      {
        let answer = this.allAnswers[index];
        if(answer.qno==this.question.qno && answer.submittedAnswer==currentOption)
          return true;
      }    
      return false;
  }

  saveAnswer(){
    this.answer.qno=this.question.qno;
    this.answer.qtext=this.question.qtext;
    this.answer.correctAnswer=this.question.answer;

    this.answer.submittedAnswer=this.submittedAnswer;
    this.questionService.saveAnswer(this.answer).subscribe();
  }

  endexam(){
    this.flag=true;
    this.router.navigateByUrl("Score");   
  }


}
