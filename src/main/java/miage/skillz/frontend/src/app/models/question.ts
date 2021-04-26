import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';
import { Competence } from './competence';

export class Question {

    idQuestion : number;
    theme : string;
    libelle : string;
    poids :  number ;
    niveau :  string;
    questionCompetences : Array<Competence>;
    listQuizz : Array<Quiz>;
    reponsesQuestions : Array<ResponseQuestion> = [];
    stringCompetence : string;
    qst: string;

   // à modifier
    competence: string;



    constructor()
    {
        this.idQuestion=0;
        this.theme='';
        this.libelle='';
        this.poids=0;
        this.niveau='';
        this.questionCompetences =[];
        this.listQuizz =[];
        this.reponsesQuestions =[];
        this.stringCompetence='';
        this.qst='';

        // à modifier
        this.competence='';
    }
}
