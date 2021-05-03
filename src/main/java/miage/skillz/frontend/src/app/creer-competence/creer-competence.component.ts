import { Component, OnInit } from '@angular/core';
import {Competence} from '../models/competence';
import {CompetenceService } from '../services/competence.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-creer-competence',
  templateUrl: './creer-competence.component.html',
  styleUrls: ['./creer-competence.component.scss']
})
export class CreerCompetenceComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  competence = new Competence();
  list_competence_pere: Array<Competence> =[];


  constructor(private router: Router, private competenceService: CompetenceService) { }

  ngOnInit(): void {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    this.list_competence_pere=listComptence;
  }

  onSubmit(): void {
    this.createCompetence();
  }

  createCompetence(): void {
    this.competenceService.saveCompetence(this.competence).subscribe(data =>
    {
      console.log(data);
  });
    this.router.navigate(['/liste-competence']);
  }


}
