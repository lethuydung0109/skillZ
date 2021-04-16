import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { QuizService } from '../services/quiz.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Quiz } from '../models/quiz';
import { Question } from '../models/question';
import { ResponseQuestion } from '../models/response-question';
import { QuestionService } from '../services/question.service';
import { InfoModalComponent } from '../info-modal/info-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CountdownComponent, CountdownEvent } from 'ngx-countdown';


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
  isStart : boolean;
  userResponse: Map<number,Array<ResponseQuestion>> =new Map();
  quizScore : number;
  quizTime !: number;

  @ViewChild('chrono', { static: false }) chrono!: CountdownComponent;

  
  constructor(private actRoute: ActivatedRoute, private quizService : QuizService, private modalService: NgbModal,
              private http : HttpClient, private router : Router, private questionService : QuestionService) { 
    this.quizId = this.actRoute.snapshot.params.id;
    this.currentQuiz= new Quiz();
    this.isStart=false;
    this.quizScore =0;
  }

  ngOnInit(): void {

    this.quizTime=10;

    this.quizService.getQuiz(26).subscribe(data => {
      console.log("quiz data : ", data)
      this.currentQuiz=data;
      this.quizTime=data.duree;
    })

    this.quizService.getQuizQuestions(26).subscribe(data => {
        console.log(" data questionsQuiz ", data);
        this.questions=data;
    })
  }

  startQuiz() { 
    this.isStart=true;
    this.chrono.resume(); 
  }

  isResponseChecked(response : ResponseQuestion, qId : number)
  {
    //console.log("response ",response," qId : ", qId);
    if(response.isSelected)
    {
      if(this.userResponse.has(qId)) this.userResponse.get(qId)?.push(response)    
      else  this.userResponse.set(qId, new Array(response));
    }
    
    console.log("Map : ", this.userResponse);
  }

  validQuizResponse(){
    
    //Pour chaque question du quiz
    for (let entry of this.userResponse.entries()) {
      //console.log(" row : ",entry[0], entry[1]);   
    
      //Vérifier que les questions sélectionnées sont les bonnes réponses
      this.questionService.getQuestionCorrectResponse(entry[0]).subscribe(data =>
      {       
        entry[1].forEach(selectedResponse  => {
          data.forEach( correctResponse =>
          {
            if(selectedResponse.idReponse == correctResponse.idReponse) 
            {
              this.selectedResponse.push(selectedResponse); 
            }
          })
        });
        
        //console.log("valid reponses",this.selectedResponse)
        // //Calculer le score du quiz
        if(this.selectedResponse.length !=0) {
          let poidsTotal=0;
          this.questionService.getQuestionPoids(entry[0]).subscribe(poids => {            
            poidsTotal=poidsTotal+poids;     
            console.log("poids : ", poidsTotal)          
          });   
          this.saveScore(poidsTotal);                  
        }
      });      
    }

    if(this.quizScore>=this.currentQuiz.pourcentageValidation) 
      this.openValidationModal("Félicitaions !Vous avez réussi le test avec un score de : "+this.quizScore);
    else this.openValidationModal("Dommage ! Vous avez raté le test. Votre score est de "+this.quizScore);
  }

  endQuiz(e: CountdownEvent) {
    if (e.action == 'done') {
      this.isStart=false;
      this.validQuizResponse();
    }
  }
  
  saveScore(score : number)  {
    this.quizScore=score;
    console.log(" score", this.quizScore);  
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(InfoModalComponent);
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.url = "/quizz";
  }

}
