import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Question} from "../classes/question";

const API_URL = 'http://localhost:8081/api/';
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


  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  public saveQuestion(question: Question): Observable<Question> {

    return this.http.post<Question>( API_URL + 'createQuestion/', {
      descirption: question.description,
      niveau: question.niveau,
    }, httpOptions);

  }

  /*
  public getAllCompetence() : Observable<Competence[]> {
    return this.http.get<Competence[]>(API_URL  + 'allCompetence',httpOptions);
  }*/
}
