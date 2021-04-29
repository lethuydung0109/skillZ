import { Component, OnInit } from '@angular/core';
import {QuestionService} from '../services/question.service';
import {Router} from "@angular/router";
import {CompetenceService} from "../services/competence.service";
import {Competence} from "../models/competence";
import {Question} from "../models/question";
import {ResponseQuestion} from "../models/response-question";
import {NgModel} from "@angular/forms";
import {MatCheckbox} from "@angular/material/checkbox";

@Component({
  selector: 'app-creer-question',
  templateUrl: './creer-question.component.html',
  styleUrls: ['./creer-question.component.scss']
})
export class CreerQuestionComponent implements OnInit {

  isShow = false;
  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  question = new Question();
  list_competence: Array<Competence> =[];
  list_reponses: Array<ResponseQuestion> =[];
  rep = new ResponseQuestion();
  questionCompetences: Array<Competence> = [];


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
    this.createQuestion();
    //Renvoi vers la liste des questions
    //this.router.navigate(['/liste-question']);
  }


  createQuestion(): void {
    this.question.reponsesQuestions = this.list_reponses;
    this.questionService.saveQuestion(this.question).subscribe(data => {
      console.log(data);
    });

  }

  // Affichage du div : ajouter une réponse
  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  ajouterRepQst(reponse: string, correct: MatCheckbox) {
    this.rep.libelle = reponse;
    this.rep.isCorrect = correct.checked;
    this.list_reponses.push(this.rep);
    this.rep = new ResponseQuestion();
  }


  deleteReponse(reponse: ResponseQuestion) {
    this.list_reponses.splice(this.list_reponses.indexOf(reponse),1);
  }
}
