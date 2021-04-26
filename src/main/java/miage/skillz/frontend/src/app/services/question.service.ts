import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../models/question';
import { ResponseQuestion } from '../models/response-question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  public url =environment.api_url;
  constructor(private http : HttpClient) {
  }

  public createQuestion(question : Question) : Observable<Question>
  {
    const routeQuery=this.url+"/createQuestionz";
    console.log("Question", question)

    return this.http.post<Question>(routeQuery,Question);
  }

  public updateQuestion(question : Question) : Observable<Question>
  {
    const routeQuery=this.url+"/updateQuestion";
    console.log("updateQuestion", question)

    return this.http.put<Question>(routeQuery,Question);
  }

  public getQuestion(questionId : number) :  Observable<Question>
  {
    const routeQuery=this.url+"/getQuestion/"+questionId;
    return this.http.get<Question>(routeQuery);
  }

  public getAllQuestions() : Observable<Array<Question>>
  {
    const routeQuery=this.url+"/allQuestions";
    return this.http.get<Array<Question>>(routeQuery);
  }

  public getQuestionCorrectResponse(qId : number) : Observable<Array<ResponseQuestion>>
  {
    const routeQuery=this.url+"/correctResponse/"+qId;
    return this.http.get<Array<ResponseQuestion>>(routeQuery);
  }

  public deleteQuestion(questionId : number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteQuestion/"+questionId;
    return this.http.delete(routeQuery);

  }

  public deleteAllQuestion() : Observable<any> {
      const routeQuery=this.url+"/deleteAllQuestions";
      return this.http.delete(routeQuery);
  }

  public getQuestionPoids(qId : number) : Observable<number>
  {
    // let score : number = 0;
    // this.getQuestion(qId).subscribe(data => {
    //   console.log("data : ", data)
    //   score= data.poids;
    // });
    const routeQuery=this.url+"/getQuestionPoids/"+qId;
    return this.http.get<number>(routeQuery);
  }
}
