import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoQuizzComponent } from './do-quizz/do-quizz.component';
import { HomeComponent } from './home/home.component';
import { CreateQuizzComponent } from './create-quizz/create-quizz.component';
import { QuizzComponent } from './quizz/quizz.component';
import { QuestionsComponent } from './questions/questions.component';
import { CreateQuestionComponent } from './create-question/create-question.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'createQuiz', component: CreateQuizzComponent },
  { path: 'quizz', component: QuizzComponent },
  { path: 'doquizz', component: DoQuizzComponent },
  { path: 'createQuestion', component: CreateQuestionComponent },
  { path: 'questions', component: QuestionsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
