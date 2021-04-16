import { Injectable } from '@angular/core';
import {Quizz} from "../classes/quizz";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
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
export class QuizzService {

  constructor(private http: HttpClient) { }

  saveQuizz(quizz: Quizz) : Observable<Quizz>{

    return this.http.post<Quizz>( API_URL + 'createQuizz/', {
      name: quizz.name,
    }, httpOptions);

  }
}
