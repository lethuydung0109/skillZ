import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Competence} from "../models/competence";
import {CompetenceService} from "../services/competence.service";
import {Quiz} from "../models/quiz";
import {QuizService} from "../services/quiz.service";

@Component({
  selector: 'app-creer-quizz',
  templateUrl: './creer-quizz.component.html',
  styleUrls: ['./creer-quizz.component.scss']
})
export class CreerQuizzComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  quizz = new Quiz();
  list_competence: Array<Competence> =[];

  constructor(private router: Router, private quizService: QuizService,private competenceService : CompetenceService) { }

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
    this.quizService.saveQuizz(this.quizz).subscribe(data => {
      console.log(data);
    });

  }
}
