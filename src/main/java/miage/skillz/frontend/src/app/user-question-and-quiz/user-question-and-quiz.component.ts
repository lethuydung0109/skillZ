import { Component, OnInit, ViewChild } from '@angular/core';
import { Quiz } from '../models/quiz';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { QuizService } from '../services/quiz.service';
import { Competence } from '../models/competence';
import { QuestionService } from '../services/question.service';
import { Question } from '../models/question';

@Component({
  selector: 'app-user-question-and-quiz',
  templateUrl: './user-question-and-quiz.component.html',
  styleUrls: ['./user-question-and-quiz.component.scss']
})
export class UserQuestionAndQuiZComponent implements OnInit {

  myQuiz : Array<Quiz> =[];
  myQuestions: Array<Question>=[];

  displayedQuestionColumns: string[] = ['num','theme','libelle','niveauName','stringCompetence'];
  displayedQuizColumns: string[] = ['num','theme','name', 'niveauName', 'competence','duree'];
 

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
        q.stringCompetence=this.quizCompetenceToString(q.questionCompetences);
        q.niveauName=this.toStringNiveau(q.niveau);
        listQ.push(q);
      })
      this.dataSourceQuestion = new MatTableDataSource(listQ);
    });
    this.myQuestions=listQ;
    console.log("questions : ",this.myQuestions);

    let listQuiz : Array<Quiz> =[];
    this.quizService.getAllQuizByUser().subscribe(data => {
      data.forEach(q => {  
        q.niveauName=this.toStringNiveau(q.niveau);
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


  quizCompetenceToString(competences : Array<Competence>) : string
  {
    let stringArray='';
    competences.forEach(c => {
      stringArray+=c.nom_competence+",";
    })
    return stringArray;
  }

  toStringNiveau(niveau : number) : string
  {
    switch (niveau) {
      case 1:
        return "Debutant"
      case 2:
        return "PreIntermediaire"
      case 3:
        return "Intermediaire"
      case 4:
        return "Avance"
      default:
        return "Debutant"
    }
  }


}
