import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { QuestionComponent } from './question/question.component';
import { ScoreComponent } from './score/score.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { ManageresultComponent } from './manageresult/manageresult.component';
import { QuestionmanagementComponent } from './questionmanagement/questionmanagement.component';

export const routes: Routes = [
    {path:"Register", component:RegisterComponent},
    {path:"Login", component:LoginComponent},
    {path:"Question", component:QuestionComponent},
    {path:"Score", component:ScoreComponent},
    {path:"AdminDashboard", component:AdmindashboardComponent},
    {path:"ManageResult", component:ManageresultComponent},
    {path:"QuestionManager", component:QuestionmanagementComponent},
    // {path:'',redirectTo:"Login", pathMatch:'full'}
];
