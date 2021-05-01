import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Competence} from "../models/competence";
import {CompetenceService} from "../services/competence.service";
import {Quiz} from "../models/quiz";
import {QuizService} from "../services/quiz.service";

@Component({
  selector: 'app-creer-quiz',
  templateUrl: './creer-quiz.component.html',
  styleUrls: ['./creer-quiz.component.scss']
})
export class CreerQuizComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  quiz = new Quiz();
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


  createQuiz() : void {
    this.quizService.saveQuiz(this.quiz).subscribe(data => {
      console.log(data);
    });

  }
}
