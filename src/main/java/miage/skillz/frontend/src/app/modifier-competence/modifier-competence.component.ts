import { Component, OnInit } from '@angular/core';
import {Competence} from '../models/competence';
import {CompetenceService } from '../services/competence.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-modifier-competence',
  templateUrl: './modifier-competence.component.html',
  styleUrls: ['./modifier-competence.component.scss']
})
export class ModifierCompetenceComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  competence = new Competence();

  list_competence_pere: Array<Competence> =[];


  constructor(private route: ActivatedRoute,private router: Router, private competenceService: CompetenceService) { }

  ngOnInit(): void {
   console.log(this.route.snapshot.params.id);
    this.competenceService.getCompetenceById(this.route.snapshot.params.id).subscribe(data => this.competence = data);
    this.getListCompetencesPere();
  }


  getListCompetencesPere() : void
  {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    this.list_competence_pere=listComptence;
  }

  onSubmit(): void {
    this.modifyCompetence(this.competence);
  }

  modifyCompetence(competence : Competence): void {
    this.competenceService.modifyCompetence(competence);
    this.router.navigate(['/liste-competence']);
  }

}
