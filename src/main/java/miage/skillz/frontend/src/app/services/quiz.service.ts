import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Quiz } from '../models/quiz';
import { Question } from '../models/question';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  public url =environment.api_url;
  constructor(private http : HttpClient) {
  }

  public createQuizz(quiz : Quiz) : Observable<Quiz>
  {
    const routeQuery=this.url+"/createQuizz";
    console.log("Quiz", quiz)

    return this.http.post<Quiz>(routeQuery,quiz);
  }

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

  public addQuestionToQuizz()
  {
    
  }

  public deleteQuestionFromQuizz()
  {
    
  }

  public deleteQuizz(quizId : number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteQuiz/"+quizId;
    return this.http.delete(routeQuery);
    
  }

  public deleteAllQuizz() : Observable<any> {
      const routeQuery=this.url+"/deleteAllQuiz";
      return this.http.delete(routeQuery);
  }
}
