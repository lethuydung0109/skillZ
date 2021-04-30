import { Question } from './question';
import { Competence } from './competence';

export class Quiz {

    idQuiz : number;
    duree : number;
    name : string;
    seuilValidation :  number;
    theme : string;

    idNiveau!: number;
    niveauName!: string;

    // id competence
    //idCompetence !: number
  quizCompetence !: Competence;

  quizQuestions : Array<Question>;
  quizQuestionsId : Array<number>;

   // stringCompetence : string
    score : number;

    idCompetence !: number;

    constructor()
    {
        this.idQuiz= 0;
        this.name='';
        this.idNiveau=0;
        this.theme='';
        this.seuilValidation=0;
        this.duree=0;
      this.quizQuestions = [];
      this.quizQuestionsId = [];
       // this.stringCompetence='';
      //  this.quizCompetences = [];
      // this.quizCompetences = 0;
      //  this.stringCompetence='';
        this.score=0;

    }
}
