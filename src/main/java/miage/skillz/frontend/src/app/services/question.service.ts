import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Question } from '../models/question';
import { ResponseQuestion } from '../models/response-question';

const API_URL = environment.api_url +'/';
const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json'
    })
};


@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  public url =environment.api_url;

  constructor(private http: HttpClient) { }


  public saveQuestion(question: Question): Observable<Question> {

    return this.http.post<Question>( API_URL + 'createQuestion/',question , httpOptions);

  }

  public updateQuestion(question : Question) : Observable<Question>
  {
    const routeQuery=this.url+"/updateQuestion";
    console.log("updateQuestion", question);
    return this.http.put<Question>(routeQuery,question);
  }

  public getQuestion(questionId : number) :  Observable<Question>
  {
    const routeQuery=this.url+"/getQuestion/"+questionId;
    return this.http.get<Question>(routeQuery);
  }

  public getLesQuestionsById(listQstId : Array<number>) : Observable<Set<Question>>
  {
    const routeQuery=this.url+"/lesQuestionById/"+listQstId;
    return this.http.get<Set<Question>>(routeQuery);
  }

  public getAllQuestions() : Observable<Array<Question>>
  {
    const routeQuery=this.url+"/allQuestions";
    return this.http.get<Array<Question>>(routeQuery);
  }

  public getNumberOfQuestions() : Observable<any>
  {
    const routeQuery=this.url+"/numberOfQuestions";
    return this.http.get(routeQuery, { responseType: 'text' });
  }

  public getAllQuestionsByUser() : Observable<Array<Question>>
  {
    const routeQuery=this.url+"/user/questions";
    return this.http.get<Array<Question>>(routeQuery);
  }


  public getQuestionCorrectResponse(qId : number) : Observable<Array<ResponseQuestion>>
  {
    const routeQuery=this.url+"/correctResponse/"+qId;
    return this.http.get<Array<ResponseQuestion>>(routeQuery);
  }

  public async getQuestionCorrectResponse2(qId : number)
  {
    const routeQuery=this.url+"/correctResponse/"+qId;
    return await this.http.get<Array<ResponseQuestion>>(routeQuery).toPromise();
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

  public getQuestionByCompetenceNiveau(/*idCompetence : number ,*/ idNiveau:number) : Observable<Question[]>
  {
    const routeQuery=API_URL+"getQuestionCompetenceNiveau/"+idNiveau;
    return this.http.get<Array<Question>>(routeQuery);
  }

  getQuestionReponses(idQuestion : number) {
    const routeQuery=API_URL+"getQuestionReponses/"+idQuestion;
    return this.http.get<Array<ResponseQuestion>>(routeQuery);
  }
}
