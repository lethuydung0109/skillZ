import { Question } from './question';
import { Competence } from './competence';

export class Quiz {

    idQuiz : number;
    name : string;
    niveau!: any;
    idNiveau!:number;
    niveauName!: string;
    theme : string;
    seuilValidation :  number;
    duree : number;
    quizQuestions : Array<Question>;
    quizCompetence !: Competence;
    // stringCompetence : string;
    quizQuestionsId : Array<number>;

   // stringCompetence : string
    score : number;

    idCompetence !: number;

    constructor()
    {
        this.idQuiz= 0;
        this.name='';
        //this.niveau=;
        this.theme='';
        this.seuilValidation=0;
        this.duree=0;
        this.quizQuestions = [];
      this.quizQuestionsId = [];
       // this.stringCompetence='';
        this.score=0;

    }
}
