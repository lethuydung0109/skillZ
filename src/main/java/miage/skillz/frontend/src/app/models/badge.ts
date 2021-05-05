import { Competence } from './competence';

export class Badge {
    competenceId!:number;
    competence ?: Competence;
    competenceName?:string;
    niveauName !:string;
    niveau!:any;
    niveauId!:number;
    dateValidation !:string;
    quizScore!:number;
}
