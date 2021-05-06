import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PublicContent } from '../models/public-content';
import { PublicContentService } from '../services/public-content.service';
import { TokenStorageService } from '../_services/auth/service/token-storage.service';
import { UserTestService } from '../_services/auth/service/userTest.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  editContent?: string;
  submitted = false;
  roles?: string[];
  isLoggedIn = false;
  isAdmin = false;
  username?: string;

  publicContent: PublicContent ={
    id: 0,
    content: '',
    date:''
  }

  constructor(private userService: UserTestService,
    private tokenStorageService: TokenStorageService, 
    private publicContentService: PublicContentService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      if (user != null) {
        this.roles = user.roles;
        if (this.roles != null) {
          this.isAdmin = this.roles.includes('ROLE_ADMIN');
          console.log("Home: isAdmin = " + this.isAdmin);
        }
        else {
          console.log("Role is null");
        }
        this.username = user.username;
        console.log("User name  = " + this.username);
      }
    }

    this.publicContentService.getLatestPublicContent().subscribe(
      data => {
        this.content = data;
        console.log(this.content);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  savePublicContent(): void {
    
    this.publicContent.date = this.datePipe.transform(new Date(), 'yyyy-MM-dd')!
    this.publicContent.content = this.editContent;
    this.publicContentService.savePublicContent(this.publicContent)
      .subscribe( 
        response => {
        console.log(response);
        this.submitted = true;
      },
      error => {
        console.log(error);
      });

    this.content = this.editContent;
  }
}
