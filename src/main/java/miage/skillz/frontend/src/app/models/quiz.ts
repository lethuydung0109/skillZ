import { Question } from './question';

export class Quiz {

    idQuizz : number;
    name : string;
    niveau: string;
    theme : string;
    pourcentageValidation :  number;
    duree : string;
    questionsQuizz : Array<Question>;
    competences : Array<string>;
    stringCompetence : string;

    constructor()
    {
        this.idQuizz= 0;
        this.name='';
        this.niveau='';
        this.theme='';
        this.pourcentageValidation=0;
        this.duree='';
        this.questionsQuizz = [];
        this.competences = [];
        this.stringCompetence='';
    }
}
