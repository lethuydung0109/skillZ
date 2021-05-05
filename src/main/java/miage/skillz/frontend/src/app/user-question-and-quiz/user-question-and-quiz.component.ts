import { Component, OnInit, ViewChild } from '@angular/core';
import { Quiz } from '../models/quiz';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { QuizService } from '../services/quiz.service';
import { QuestionService } from '../services/question.service';
import { Question } from '../models/question';
import Utils from '../utils';

@Component({
  selector: 'app-user-question-and-quiz',
  templateUrl: './user-question-and-quiz.component.html',
  styleUrls: ['./user-question-and-quiz.component.scss']
})
export class UserQuestionAndQuiZComponent implements OnInit {

  myQuiz : Array<Quiz> =[];
  myQuestions: Array<Question>=[];

  displayedQuestionColumns: string[] = ['num','theme','libelle','niveauName','stringCompetence','actions'];
  displayedQuizColumns: string[] = ['num','theme','name', 'niveauName', 'competence','duree','actions'];


  dataSourceQuestion!: MatTableDataSource<Question>;
  dataSourceQuiz!: MatTableDataSource<Quiz>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private quizService : QuizService,private questionService: QuestionService)
  {}

  ngOnInit(): void {

    let listQ : Array<Question> =[];
    this.questionService.getAllQuestionsByUser().subscribe(data => {
      data.forEach(q => {
        q.stringCompetence=Utils.quizCompetenceToString(q.questionCompetences);
        q.niveauName=Utils.toStringNiveau(q.niveau);
        listQ.push(q);
      })
      this.dataSourceQuestion = new MatTableDataSource(listQ);
    });
    this.myQuestions=listQ;
    console.log("questions : ",this.myQuestions);

    let listQuiz : Array<Quiz> =[];
    this.quizService.getAllQuizByUser().subscribe(data => {
      data.forEach(q => {
        q.niveauName=Utils.toStringNiveau(q.niveau);
        listQuiz.push(q);
      })
      this.dataSourceQuiz = new MatTableDataSource(listQuiz);
    });

    this.myQuiz=listQuiz;

    console.log("quiz : ",this.myQuiz);
  }

  applyFilter1(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceQuestion.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceQuestion.paginator) {
      this.dataSourceQuestion.paginator.firstPage();
    }
  }

  applyFilter2(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceQuiz.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceQuiz.paginator) {
      this.dataSourceQuiz.paginator.firstPage();
    }
  }

  deleteQuestion(id: number) {

    this.questionService.getQuestion(id).subscribe(data => {
      this.myQuestions.splice(this.myQuestions.indexOf(data),1);
    })
    this.questionService.deleteQuestion(id).subscribe(data => {});
    
  }

  deleteQuiz(id : number) {
    this.quizService.getQuiz(id).subscribe(data => {
      this.myQuiz.splice(this.myQuiz.indexOf(data),1);
    })
    this.quizService.deleteQuiz(id).subscribe(data => {});
  }

}
