import { Injectable } from '@angular/core';
import {Competence} from '../models/competence';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';

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
export class CompetenceService {


  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  public saveCompetence(competence: Competence): Observable<Competence> {

    return this.http.post<Competence>( API_URL + 'createCompetence/', {
      nom_competence: competence.nom_competence,
      id_pere: competence.id_pere,
    }, httpOptions);

  }

  public getAllCompetence() : Observable<Competence[]> {
    return this.http.get<Competence[]>(API_URL  + 'allCompetence/',httpOptions);
  }



  deleteCompetence(id: number) {
    const routeQuery=API_URL+"deleteCompetence/"+id;
    return this.http.delete(routeQuery);
  }


  getCompetenceById(id_pere: number) : Observable<Competence> {
    return this.http.get<Competence>(API_URL  + 'competenceById/'+id_pere,httpOptions);


}

  getStatsCompetence(): Observable<any> {
    return this.http.get(API_URL + 'competenceStats', { responseType: 'text' });
  }
}
