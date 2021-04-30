import { Component, OnInit } from '@angular/core';
import {QuestionService} from '../services/question.service';
import {Router} from "@angular/router";
import {CompetenceService} from "../services/competence.service";
import {Competence} from "../models/competence";
import {Question} from "../models/question";
import {ResponseQuestion} from "../models/response-question";
import {MatCheckbox} from "@angular/material/checkbox";
import {FormGroup} from "@angular/forms";

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
    console.log("liste competences :", listComptence)
    this.list_competence=listComptence;
  }




  onSubmit(): void {
    this.createQuestion();
    //Renvoi vers la liste des questions
    //this.router.navigate(['/liste-question']);
  }


  createQuestion(): void {
    this.question.reponsesQuestions = this.list_reponses;
    console.log(this.list_reponses);
    this.questionService.saveQuestion(this.question).subscribe(data => {
      console.log(data);
    });

  }

  // Affichage du div : ajouter une réponse
  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  ajouterRepQst(reponse: string, correct: MatCheckbox) {
    let rep = new ResponseQuestion();
    rep.libelle = reponse;
    rep.isCorrect = correct.checked;
    this.list_reponses.push(rep);
  }


  deleteReponse(reponse: ResponseQuestion) {
    this.list_reponses.splice(this.list_reponses.indexOf(reponse),1);
  }

  getAllNiveau()
  {

  }
}
