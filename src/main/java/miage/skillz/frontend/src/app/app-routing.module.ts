import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardParticipantComponent } from './board-participant/board-participant.component';
import { BoardConcepteurComponent } from './board-concepteur/board-concepteur.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import {CreerQuestionComponent} from './creer-question/creer-question.component';
import {CreerQuizzComponent} from './creer-quizz/creer-quizz.component';
import {CreerCompetenceComponent} from './creer-competence/creer-competence.component';
import {ListeCompetenceComponent} from './liste-competence/liste-competence.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'participant', component: BoardParticipantComponent },
  { path: 'concepteur', component: BoardConcepteurComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'creer-competence', component: CreerCompetenceComponent },
  { path: 'creer-question', component: CreerQuestionComponent },
  { path: 'creer-quizz', component: CreerQuizzComponent },
  { path: 'liste-competence', component: ListeCompetenceComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
