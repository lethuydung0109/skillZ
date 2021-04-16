import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardConcepteurComponent } from './board-concepteur/board-concepteur.component';
import { BoardParticipantComponent } from './board-participant/board-participant.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CreerCompetenceComponent } from './creer-competence/creer-competence.component';
import { CreerQuestionComponent } from './creer-question/creer-question.component';
import { CreerQuizzComponent } from './creer-quizz/creer-quizz.component';
import { ListeCompetenceComponent } from './liste-competence/liste-competence.component';
import {MatIconModule} from '@angular/material/icon';
import {MatTreeModule} from "@angular/material/tree";
import {MatTableModule} from "@angular/material/table";



@NgModule({
declarations: [
AppComponent,
LoginComponent,
RegisterComponent,
HomeComponent,
ProfileComponent,
BoardAdminComponent,
BoardConcepteurComponent,
BoardParticipantComponent,
CreerCompetenceComponent,
CreerQuestionComponent,
CreerQuizzComponent,
ListeCompetenceComponent,
],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        MatIconModule,
        MatTreeModule,
        MatTableModule,
    ],
providers: [authInterceptorProviders],
bootstrap: [AppComponent]
})
export class AppModule { }
