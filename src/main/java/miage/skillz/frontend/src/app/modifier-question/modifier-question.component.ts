import { Component, OnInit } from '@angular/core';
import {Question} from "../models/question";
import {ActivatedRoute, Router} from "@angular/router";
import {QuestionService} from "../services/question.service";
import {Competence} from "../models/competence";
import {ResponseQuestion} from "../models/response-question";
import {CompetenceService} from "../services/competence.service";
import {MatCheckbox} from "@angular/material/checkbox";

@Component({
  selector: 'app-modifier-question',
  templateUrl: './modifier-question.component.html',
  styleUrls: ['./modifier-question.component.scss']
})
export class ModifierQuestionComponent implements OnInit {

  question : Question = new Question();
  isShow = false;
  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  list_competence: Array<Competence> =[];
  list_reponses: Array<ResponseQuestion> =[];
  questionCompetences: Array<Competence> = [];

  constructor(
    private questionService: QuestionService,
    private route: ActivatedRoute,
    private router: Router,
    private competenceService : CompetenceService
  ) { }

  ngOnInit(): void {
    this.questionService.getQuestion(this.route.snapshot.params.idQuestion).subscribe(data =>
    {
      this.question.idQuestion = data.idQuestion;
      this.question.libelle = data.libelle;
      this.question.theme = data.theme;
      this.question.idNiveau = data.niveau.niveauId;
      this.question.niveauName = data.niveauName;
      this.question.reponsesQuestions = data.reponsesQuestions;
      this.question.questionCompetences = data.questionCompetences;
    }

    );
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    this.list_competence=listComptence;

    this.questionService.getQuestionReponses(this.route.snapshot.params.idQuestion).subscribe(data =>
      data.forEach(p => {
      this.list_reponses.push(p);
      }));
  }

  onSubmit() {
    this.question.reponsesQuestions = this.list_reponses;
    this.questionService.updateQuestion(this.question).subscribe(data => console.log(data));
    this.router.navigate(['/userQuestionsAndQuiz']);
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



}
