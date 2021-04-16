import { Component, OnInit } from '@angular/core';
import {Question} from '../classes/question';
import {QuestionService} from '../services/question.service';
import {Router} from "@angular/router";
import {CompetenceService} from "../services/competence.service";
import {Competence} from "../classes/competence";


@Component({
  selector: 'app-creer-question',
  templateUrl: './creer-question.component.html',
  styleUrls: ['./creer-question.component.scss']
})
export class CreerQuestionComponent implements OnInit {

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  question = new Question();
  list_competence: Array<Competence> =[];

  constructor(private router: Router, private questionService: QuestionService, private competenceService : CompetenceService) {
  }

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

  onSubmit(): void {
  }


  createQuestion(): void {
    this.questionService.saveQuestion(this.question).subscribe(data => {
      console.log(data);
    });
    //Renvoi vers la liste des questions
    //this.router.navigate(['/liste-question']);
  }



}
