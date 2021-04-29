import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';
import { Competence } from './competence';

export class Question {

    idQuestion : number;
    libelle : string;
    theme : string;
    niveau :  string;
    poids :  number ;
    qst: string;
    reponsesQuestions : Array<ResponseQuestion> = [];
    questionCompetences : Array<Competence>;

    listQuizz : Array<Quiz>;

    stringCompetence : string;



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

    }
}
