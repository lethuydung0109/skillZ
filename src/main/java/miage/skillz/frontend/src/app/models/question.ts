import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';
import {Competence} from "./competence";


export class Question {

    idQuestion : number;
    theme : string;
    libelle : string;
    poids :  number ;
    niveau !:  number;
    niveauName!: string;
    questionCompetences : Array<Competence>;
    listQuiz : Array<Quiz>;
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
        this.questionCompetences =[];
        this.listQuiz =[];
        this.reponsesQuestions =[];
        this.stringCompetence='';
        this.qst='';

        // à modifier
        this.competence='';
    }
}
