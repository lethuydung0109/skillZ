import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import { CreerQuizComponent } from './creer-quiz/creer-quiz.component';
import { ListeCompetenceComponent } from './liste-competence/liste-competence.component';
import { MatIconModule } from '@angular/material/icon';
import { MatTreeModule } from "@angular/material/tree";
import { MatTableModule } from "@angular/material/table";

import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { QuestionsComponent } from './questions/questions.component';
import { ListQuizComponent } from './list-quiz/list-quiz.component';
import { DoQuizComponent } from './do-quiz/do-quiz.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {ScrollingModule} from '@angular/cdk/scrolling';
import { InfoModalComponent } from './info-modal/info-modal.component';
import { CountdownModule } from 'ngx-countdown';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import { CreateUserComponent } from './create-user/create-user.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserQuestionAndQuiZComponent } from './user-question-and-quiz/user-question-and-quiz.component';
import { MatTabsModule} from '@angular/material/tabs';
import { MatChipsModule } from '@angular/material/chips';
import { recommendationListComponent } from './recommendation-list/recommendation-list.component';
import { CreateRecommendationComponent } from './create-recommendation/create-recommendation.component';
import { DatePipe } from '@angular/common';
import { ModifierCompetenceComponent } from './modifier-competence/modifier-competence.component';
import { ModifierQuestionComponent } from './modifier-question/modifier-question.component';
import { ModifierQuizComponent } from './modifier-quiz/modifier-quiz.component';



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
        ListQuizComponent,
        DoQuizComponent,
        InfoModalComponent,
        CreerCompetenceComponent,
        CreerQuestionComponent,
        CreerQuizComponent,
        ListeCompetenceComponent,
        CreateUserComponent,
        UserListComponent,
        UserDetailsComponent,
        UserQuestionAndQuiZComponent,
        recommendationListComponent,
        CreateRecommendationComponent,
        ModifierCompetenceComponent,
        ModifierQuestionComponent,
        ModifierQuizComponent

    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        MatIconModule,
        MatTreeModule,
        MatTableModule,
        MatSortModule,
        BrowserAnimationsModule,
        MatPaginatorModule,
        MatFormFieldModule,
        MatInputModule,
        MatCheckboxModule,
        CountdownModule,
        ScrollingModule,
        MatButtonModule,
        MatListModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatTabsModule,
        MatChipsModule
],
providers: [authInterceptorProviders, DatePipe],
bootstrap: [AppComponent],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
