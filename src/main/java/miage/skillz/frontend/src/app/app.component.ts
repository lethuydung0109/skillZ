import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/auth/service/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  isAdmin = false;
  isParticipant = false;
  isConcepteur = false;
  username?: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      console.log(user)

      this.isAdmin = this.roles.includes('ROLE_ADMIN');
      this.isParticipant = this.roles.includes('ROLE_PARTICIPANT');
      this.isConcepteur = this.roles.includes('ROLE_CONCEPTEUR');

      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
