import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Competence} from "../models/competence";
import {CompetenceService} from "../services/competence.service";
import {Quiz} from "../models/quiz";
import {QuizService} from "../services/quiz.service";
import {QuestionService} from "../services/question.service";
import {Question} from "../models/question";
import {FormControl} from "@angular/forms";

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
  showNiveau=false;
  list_questionQuizz: number[] = [];
//  question = new Question();


  constructor(private router: Router, private quizService: QuizService,private competenceService : CompetenceService,private questionService : QuestionService) { }

  ngOnInit(): void {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        listComptence.push(p);
      })
    });
    this.list_competence=listComptence;


  }

  onSubmit(): void {
    this.createQuizz();
  }

  competenceChange(competence:string)
  {
    if(competence != null) {this.showNiveau = !(this.showNiveau )}

  }

  onNiveauSelected(value:string)
  {
    this.list_question = [];
    this.questionService.getQuestionByCompetenceNiveau(value).subscribe(data => {data.forEach(p => {this.list_question.push(p)})})
  }

  createQuiz() : void {
    this.quizService.saveQuiz(this.quiz).subscribe(data => {
      console.log(data);
    });

  }

  ajouterQst(value: number) {
    this.list_questionQuizz.push(value);
    console.log(this.list_questionQuizz);


    //this.quiz.questionsQuizz = this.list_questionQuizz;
  }
}
