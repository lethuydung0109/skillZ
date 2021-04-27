import { Component, OnInit } from '@angular/core';
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
  constructor(private userService: UserTestService, 
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {

    this.content = "Public content";
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      if(user != null){
        this.roles = user.roles;
        if(this.roles != null){
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

    // this.userService.getPublicContent().subscribe(
    //   data => {
    //     this.content = data;
    //   },
    //   err => {
    //     this.content = JSON.parse(err.error).message;
    //   }
    // );
  }

  savePublicContent(): void{
    this.content = this.editContent;
    this.submitted = true;
  }
}
