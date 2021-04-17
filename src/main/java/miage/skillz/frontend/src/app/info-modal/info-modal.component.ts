import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-info-modal',
  templateUrl: './info-modal.component.html',
  styleUrls: ['./info-modal.component.scss']
})
export class InfoModalComponent implements OnInit {

  @Input() message: string='';
  @Input() url : string ='';
  constructor(private router :Router,public activeModal: NgbActiveModal) { 
  }

  ngOnInit(): void {
  }

  goToAnotherPage()
  {
    this.router.navigate([this.url]);
    this.activeModal.close('Close click')
  }

}
