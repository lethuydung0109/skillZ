import { Competence } from './competence';

export class Poste {
    posteId!:number;
    name!:string;
    competenceId!:number;
    competence !: Competence;
    competenceName!:string;
    niveauName !:string;
    niveau!:any;
    niveauId!:number;
    scoreMin!:number;
}
