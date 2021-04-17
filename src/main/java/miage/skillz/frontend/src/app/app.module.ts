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


import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { QuestionsComponent } from './questions/questions.component';
import { CreateQuestionComponent } from './create-question/create-question.component';
import { QuizzComponent } from './quizz/quizz.component';
import { CreateQuizzComponent } from './create-quizz/create-quizz.component';
import { DoQuizzComponent } from './do-quizz/do-quizz.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {ScrollingModule} from '@angular/cdk/scrolling';
import { InfoModalComponent } from './info-modal/info-modal.component';
import { CountdownModule } from 'ngx-countdown';
import {MatButtonModule} from '@angular/material/button';




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
    QuestionsComponent,
    CreateQuestionComponent,
    QuizzComponent,
    CreateQuizzComponent,
    DoQuizzComponent,
    InfoModalComponent
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
imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    CountdownModule,
    ScrollingModule,
    MatButtonModule
],
providers: [authInterceptorProviders],
bootstrap: [AppComponent],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
