import { Component, OnInit } from '@angular/core';
import {Quizz} from "../classes/quizz";
import {Router} from "@angular/router";
import {QuizzService} from "../services/quizz.service";
import {Competence} from "../classes/competence";
import {CompetenceService} from "../services/competence.service";

@Component({
  selector: 'app-creer-quizz',
  templateUrl: './creer-quizz.component.html',
  styleUrls: ['./creer-quizz.component.scss']
})
export class CreerQuizzComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  quizz = new Quizz();
  list_competence: Array<Competence> =[];

  constructor(private router: Router, private quizzService: QuizzService,private competenceService : CompetenceService) { }

  ngOnInit(): void {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    console.log("projects :", listComptence)
    this.list_competence=listComptence;

  }

  onSubmit(): void {}


  createQuizz() : void {
    this.quizzService.saveQuizz(this.quizz).subscribe(data => {
      console.log(data);
    });

  }
}
