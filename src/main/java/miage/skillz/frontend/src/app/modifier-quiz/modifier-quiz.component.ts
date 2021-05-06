import { Component, OnInit } from '@angular/core';
import {Quiz} from "../models/quiz";
import {Competence} from "../models/competence";
import {Question} from "../models/question";
import {QuestionService} from "../services/question.service";
import {QuizService} from "../services/quiz.service";
import {ActivatedRoute} from "@angular/router";
import {CompetenceService} from "../services/competence.service";

@Component({
  selector: 'app-modifier-quiz',
  templateUrl: './modifier-quiz.component.html',
  styleUrls: ['./modifier-quiz.component.scss']
})
export class ModifierQuizComponent implements OnInit {

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

  constructor(private quizService: QuizService, private route: ActivatedRoute,private competenceService : CompetenceService,private questionService : QuestionService) { }

  ngOnInit(): void {
    this.quizService.getQuiz(this.route.snapshot.params.idQuiz).subscribe(
      data =>
      {
        this.quiz.idQuiz = data.idQuiz;
        this.quiz.theme = data.theme;
        this.quiz.quizQuestions = data.quizQuestions;
        this.quiz.quizQuestionsId = [];
        this.quiz.quizCompetence = data.quizCompetence;
        this.quiz.idCompetence = data.idCompetence;
        this.quiz.idNiveau = data.idNiveau;
        this.quiz.niveau = data.niveau;
        this.quiz.duree = data.duree;
        this.quiz.name = data.name;
        this.quiz.niveauName = data.niveauName;
        this.quiz.score = data.score;
        this.quiz.seuilValidation = data.seuilValidation;
      }
    );

    console.log("quiz init :" ,this.quiz);
    this.getListCompetence();

    this.quizService.getQuizQuestions(this.route.snapshot.params.idQuiz).subscribe(data => {
      data.forEach(p => {
        this.list_questionQuiz.push(p);
      })
    });
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

  ajouterQst(question: Question) {
    this.list_questionQuiz.push(question);
  }

  deleteQuestion(question: Question) {
    this.list_questionQuiz.splice(this.list_qstQuiz.indexOf(question),1);
  }


  onSubmit() {
    this.quiz.quizQuestions = this.list_questionQuiz;
    this.quiz.quizQuestions.forEach(
      question => {
          this.quiz.quizQuestionsId.push(question.idQuestion);
      } );

    this.quizService.updateQuiz(this.quiz).subscribe(data => console.log(data));
  }
}
