import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Quiz } from '../models/quiz';
import { Question } from '../models/question';
import { Observable } from 'rxjs';

const API_URL = environment.api_url + '/';
const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  public url =environment.api_url;
  constructor(private http : HttpClient) {
  }

  saveQuiz(quiz: Quiz) : Observable<Quiz>{

    return this.http.post<Quiz>( API_URL + 'createQuiz/', {
      name: quiz.name,
    }, httpOptions);

  }

  // public createQuiz(quiz : Quiz) : Observable<Quiz>
  // {
  //   const routeQuery=this.url+"/createQuiz";
  //   console.log("Quiz", quiz)

  //   return this.http.post<Quiz>(routeQuery,quiz);
  // }

  public updateQuiz(quiz : Quiz) : Observable<Quiz>
  {
    const routeQuery=this.url+"/updateQuiz";
    console.log("updateQuiz", quiz)

    return this.http.put<Quiz>(routeQuery,quiz);
  }

  public getQuiz(quizId : number) :  Observable<Quiz>
  {
    const routeQuery=this.url+"/getQuiz/"+quizId;
    return this.http.get<Quiz>(routeQuery);
  }

  public getAllQuiz() : Observable<Array<Quiz>>
  {
    const routeQuery=this.url+"/allQuiz";
    return this.http.get<Array<Quiz>>(routeQuery);
  }

  public getAllQuizByUser() : Observable<Array<Quiz>>
  {
    const routeQuery=this.url+"/user/quiz";
    return this.http.get<Array<Quiz>>(routeQuery);
  }

  public getQuizQuestions(quizId : number) : Observable<Array<Question>>
  {
    const routeQuery=this.url+"/getQuizQuestions/"+quizId;
    return this.http.get<Array<Question>>(routeQuery);
  }

  public addQuestionToQuiz(quizId :number, questionId : number) : Observable<boolean>
  {
    const routeQuery=this.url+"/addQuestionToQuiz/"+quizId+"/"+questionId;
    return this.http.get<boolean>(routeQuery);
  }

  public deleteQuestionFromQuiz(){}

  public deleteQuiz(quizId : number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteQuiz/"+quizId;
    return this.http.delete(routeQuery);

  }

  public deleteAllQuiz() : Observable<any> {
      const routeQuery=this.url+"/deleteAllQuiz";
      return this.http.delete(routeQuery);
  }
}
