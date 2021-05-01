import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardParticipantComponent } from './board-participant/board-participant.component';
import { BoardConcepteurComponent } from './board-concepteur/board-concepteur.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { CreerQuestionComponent } from './creer-question/creer-question.component';
import { CreerQuizComponent } from './creer-quiz/creer-quiz.component';
import { CreerCompetenceComponent } from './creer-competence/creer-competence.component';
import { ListeCompetenceComponent } from './liste-competence/liste-competence.component';
import { ListQuizComponent } from './list-quiz/list-quiz.component';
import { DoQuizComponent } from './do-quiz/do-quiz.component';
import { QuestionsComponent } from './questions/questions.component';
import { UserListComponent } from './user-list/user-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserQuestionAndQuiZComponent } from './user-question-and-quiz/user-question-and-quiz.component';
import { recommendationListComponent } from './recommendation-list/recommendation-list.component';
import { CreateRecommendationComponent } from './create-recommendation/create-recommendation.component';



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
  { path: 'creer-quiz', component: CreerQuizComponent },
  { path: 'liste-competence', component: ListeCompetenceComponent },
  { path: 'listQuiz', component: ListQuizComponent },
  { path: 'doquiz/:id', component: DoQuizComponent },
  { path: 'listQuestions', component: QuestionsComponent },
  { path: 'users', component: UserListComponent },
  { path: 'createUser', component: CreateUserComponent },
  { path: 'userQuestionsAndQuiz', component: UserQuestionAndQuiZComponent },
  { path: 'users/:id', component: UserDetailsComponent },
  { path: 'participant/:idBagde', component: BoardParticipantComponent },
  { path: 'recommendations', component: recommendationListComponent },
  { path: 'createRecommendation', component: CreateRecommendationComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
