import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modifier-competence',
  templateUrl: './modifier-competence.component.html',
  styleUrls: ['./modifier-competence.component.scss']
})
export class ModifierCompetenceComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


    modifyCompetence(): void {
    //   this.competenceService.saveCompetence(this.competence).subscribe(data =>
    //   {
    //     console.log(data);
    // });
    //   this.router.navigate(['/liste-competence']);
    }

}
