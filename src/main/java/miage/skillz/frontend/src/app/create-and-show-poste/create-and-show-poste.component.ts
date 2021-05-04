import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Poste } from '../models/poste';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { PosteService } from '../services/poste.service';
import Utils from '../utils';
import { Competence } from '../models/competence';
import { CompetenceService } from '../services/competence.service';

@Component({
  selector: 'app-create-and-show-poste',
  templateUrl: './create-and-show-poste.component.html',
  styleUrls: ['./create-and-show-poste.component.scss']
})
export class CreateAndShowPosteComponent implements OnInit {

  displayedColumns: string[] = ['num','name', 'competence', 'niveauName','scoreMin','actions'];
  niveaux : string[]= ['Debutant','Pre-Intermediaire','Intermediaire','Avancé'];
  dataSource!: MatTableDataSource<Poste>;

  listPoste : Array<Poste> =[];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  isHiden : boolean = true;
  //namePoste: string='';
 // scoreMin: number=0;

  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  competences: Array<Competence> =[];

  poste : Poste = new Poste();

  constructor(private posteService : PosteService,private competenceService : CompetenceService) { }

  ngOnInit(): void {

    //Get all competences
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p => {
        this.competences.push(p);
      })
    });
    console.log("liste competences :", this.competences)

    //Get all postes
    let listPoste : Array<Poste> =[];
    this.posteService.getAllPostes().subscribe(data => {
      data.forEach(p=> {
        console.log("poste : ", p)
        this.competenceService.getCompetenceById(p.competenceId).subscribe(data =>
        {
          p.competenceName=data.nom_competence;
        })
        p.niveauName=Utils.toStringNiveau(p.niveau.niveauId);
        listPoste.push(p);
      })
      this.dataSource = new MatTableDataSource(listPoste);
    });
    this.listPoste=listPoste;
    //console.log("postes : ",this.listPoste);
  }

  onSubmit(): void {
    this.createPoste();
    this.isHiden=true;
  }

  createPoste()
  {
    console.log("poste 1: ", this.poste);
    //this.poste.competenceId=this.poste.competence?.id;
    console.log("poste 2: ", this.poste);
    this.posteService.createPoste(this.poste).subscribe(data => {
      console.log("created poste : ", data);
      this.listPoste.push(this.poste);
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deletePoste(posteId : number)
  {
    this.posteService.deletePoste(posteId).subscribe(data=>{});
  }

  showCreationDiv(){ this.isHiden=false; }

}
