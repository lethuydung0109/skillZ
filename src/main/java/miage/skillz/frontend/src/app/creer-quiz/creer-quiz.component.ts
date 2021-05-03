import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Competence} from "../models/competence";
import {CompetenceService} from "../services/competence.service";
import {Quiz} from "../models/quiz";
import {QuizService} from "../services/quiz.service";
import {QuestionService} from "../services/question.service";
import {Question} from "../models/question";
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";
import {ResponseQuestion} from "../models/response-question";

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
  list_question: Array<Question> =[];
  showNiveau=true;
  list_questionQuiz: Array<Question> = [];

  // Questions à afficher dans le tableau
  list_qstQuiz : Array<Question> = [];


  constructor(private router: Router, private quizService: QuizService,private competenceService : CompetenceService,private questionService : QuestionService) { }

  ngOnInit(): void {
  this.getListCompetence();
  }

  onSubmit(): void {
    this.createQuiz();
  }

  getListCompetence() : void
  {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    this.list_competence=listComptence;
  }

  competenceChange(competence:string)
  {
    console.log(competence);
    if(competence != "") {this.showNiveau =  false} else { this.showNiveau = true}
  }

  onNiveauSelected(value:number)
  {
    this.list_question = [];
    this.questionService.getQuestionByCompetenceNiveau(value).subscribe(data => {data.forEach(p => {this.list_question.push(p)})})
  }

  createQuiz() : void {
    this.quiz.quizQuestions = this.list_questionQuiz;

    this.quiz.quizQuestions.forEach(question =>
    {this.quiz.quizQuestionsId.push(question.idQuestion);} );

    console.log(this.quiz);

    this.quizService.saveQuiz(this.quiz).subscribe(data => {
      console.log(data);
    });
  }

  ajouterQst(question: Question) {
    this.list_questionQuiz.push(question);
    console.log("ceci est la list des questions à afficher : " + this.list_questionQuiz);
   // this.questionService.getLesQuestionsById(this.list_questionQuiz).subscribe(data => {data.forEach(p => {this.list_qstQuiz.push(p)})})
   // this.questionService.getQuestion(idQuestion).subscribe(data => {this.list_qstQuiz.push()})
  }

  deleteQuestion(question: Question) {
    this.list_questionQuiz.splice(this.list_qstQuiz.indexOf(question),1);
  }
}
