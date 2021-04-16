import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardParticipantComponent } from './board-participant/board-participant.component';
import { BoardConcepteurComponent } from './board-concepteur/board-concepteur.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { CreateQuizzComponent } from './create-quizz/create-quizz.component';
import { QuizzComponent } from './quizz/quizz.component';
import { DoQuizzComponent } from './do-quizz/do-quizz.component';
import { CreateQuestionComponent } from './create-question/create-question.component';
import { QuestionsComponent } from './questions/questions.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'participant', component: BoardParticipantComponent },
  { path: 'concepteur', component: BoardConcepteurComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'createQuiz', component: CreateQuizzComponent },
  { path: 'quizz', component: QuizzComponent },
  { path: 'doquizz/:id', component: DoQuizzComponent },
  { path: 'createQuestion', component: CreateQuestionComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
