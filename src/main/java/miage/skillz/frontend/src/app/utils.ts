import { Competence } from './models/competence'

export default class Utils {

  static toStringNiveau(niveau : number) : string
  {
    switch (niveau) {
      case 1:
        return "Debutant"
      case 2:
        return "PreIntermediaire"
      case 3:
        return "Intermediaire"
      case 4:
        return "Avance"
      default:
        return "Debutant"
    }
  }

  static quizCompetenceToString(competences : Array<Competence>) : string
  {
    let tabName : Array<string>=[];
    competences.forEach(c => {
      tabName.push(c.nom_competence);
    })
    return tabName.join(",");
  }

}
