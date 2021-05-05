import { Component, OnInit } from '@angular/core';
import { CompetenceService } from '../services/competence.service';
import { QuestionService } from '../services/question.service';
import { QuizService } from '../services/quiz.service';
import { UserService } from '../services/user.service';
import { UserTestService } from '../_services/auth/service/userTest.service';

@Component({
  selector: 'app-board-concepteur',
  templateUrl: './board-concepteur.component.html',
  styleUrls: ['./board-concepteur.component.css']
})
export class BoardConcepteurComponent implements OnInit {
  content?: string;
  nbCompetences = 0;
  nbQuestions = 0;
  nbQuiz = 0;

  constructor(private competenceService: CompetenceService, private questionService: QuestionService, private quizService: QuizService) { }

  ngOnInit(): void {
    this.competenceService.getStatsCompetence().subscribe(
      data => {       
        this.nbCompetences = data;
        console.log(data);
        console.log(this.nbCompetences);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    this.questionService.getNumberOfQuestions().subscribe(
      data => {       
        this.nbQuestions = data;
        console.log(data);
        console.log(this.nbQuestions);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    this.quizService.getNumberOfQuiz().subscribe(
      data => {       
        this.nbQuiz = data;
        console.log(data);
        console.log(this.nbQuiz);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  
  }
}
