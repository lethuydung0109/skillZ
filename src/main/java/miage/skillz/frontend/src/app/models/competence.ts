export class Competence {

  id: number;
  // tslint:disable-next-line:variable-name
  id_pere: number;
  // tslint:disable-next-line:variable-name
  nom_competence: string;

  nom_competence_pere: string;

  constructor() {
    this.id = 0;
    this.id_pere = 0;
    this.nom_competence = '';
    this.nom_competence_pere = '';

  }

}
