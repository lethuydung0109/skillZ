import { Component, OnInit } from '@angular/core';
import { QuizService } from '../services/quiz.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Quiz } from '../models/quiz';
import { Question } from '../models/question';
import { ResponseQuestion } from '../models/response-question';

@Component({
  selector: 'app-do-quizz',
  templateUrl: './do-quizz.component.html',
  styleUrls: ['./do-quizz.component.scss']
})
export class DoQuizzComponent implements OnInit {

  quizId : number;
  currentQuiz :  Quiz;
  questions : Array<Question> =[];
  selectedResponse : Array<ResponseQuestion>=[];
  isOver : boolean;
  
  constructor(private actRoute: ActivatedRoute, private quizService : QuizService, private http : HttpClient, private router : Router) { 
    this.quizId = this.actRoute.snapshot.params.id;
    this.currentQuiz= new Quiz();
    this.isOver=false;
  }

  ngOnInit(): void {

    this.quizService.getQuiz(54).subscribe(data => {
      console.log("data quiz : ", data);
      this.currentQuiz = data;
      this.questions = data.questionsQuizz;
    })

    console.log("curentQuiz : ", this.currentQuiz);
  }

  isResponseChecked()
  {

  }

  validQuizResponse(){}

}
