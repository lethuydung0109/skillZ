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
  correctResponsesSelected : Array<ResponseQuestion>=[];
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
    console.log("quizId : ", this.quizId);
    this.quizService.getQuiz(this.quizId).subscribe(data => {
      console.log("quiz data : ", data)
      this.currentQuiz=data;
      this.quizTime=data.duree;
    })

    this.quizService.getQuizQuestions(this.quizId).subscribe(data => {
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
    //création de la liste des réponses sélectionnées par le user pour chaque question
    if(response.isSelected)
    {
      if(this.userResponse.has(qId)) this.userResponse.get(qId)?.push(response)    
      else  this.userResponse.set(qId, new Array(response));
    }
    
    console.log("Map : ", this.userResponse);
  }

  validQuizResponse(){
    
   
    let tab : Array<any> =[];
    let poidsTotal:number=0;
    //Pour chaque question 
    for (let entry of this.userResponse.entries()) {    
      //Vérifier que les questions sélectionnées sont les bonnes réponses
      this.questionService.getQuestionCorrectResponse(entry[0]).subscribe(data => {   // on récupère la liste des réponses correctes de la question           
        entry[1].forEach(selectedResponse  => { // on parcourt la liste des réponses sélectionnées pour la question
          data.forEach( correctResponse => { // on parcourt la liste des réponses correctes de la question
            if(selectedResponse.idReponse == correctResponse.idReponse)  {
              //tab.push(selectedResponse);
              this.correctResponsesSelected.push(selectedResponse); 
            }
          })
        });
        
        //this.correctResponsesSelected=tab; // liste des réponses sélectionnées et correctes pour la question
        //console.log(" this.correctResponsesSelected ", this.correctResponsesSelected)
       
        //Calculer le score du user pour le quiz
        if(this.correctResponsesSelected.length == data.length) {
          this.questionService.getQuestionPoids(entry[0]).subscribe(poids => {            
            poidsTotal+=poids;     
            console.log("qS : ", poidsTotal);           
          });           
          //this.saveScore(poidsTotal);                  
        }   
        this.quizScore=poidsTotal;      
      });      
    }

    if(this.quizScore >= this.currentQuiz.pourcentageValidation) 
      this.openValidationModal("Félicitaions !Vous avez réussi le test avec un score de : "+poidsTotal);
    else this.openValidationModal("Dommage ! Vous avez raté le test. Votre score est de "+poidsTotal);
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
    modalRef.componentInstance.url = "/user";
  }

}
