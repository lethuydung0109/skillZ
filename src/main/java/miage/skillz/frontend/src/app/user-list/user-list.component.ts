import { ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { PosteService } from '../services/poste.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  users?: User[];
  currentUser?: User;
  currentIndex = -1;
  username = '';

  posteId:number;
  isCandidateView : boolean=false;

  candidatesUser : User []=[];
  listUser : User[]=[];

  constructor(private userService: UserService,private actRoute: ActivatedRoute, private posteService : PosteService) {
    this.posteId=this.actRoute.snapshot.params.posteId;
  }

  ngOnInit(): void {
    
    if(this.posteId!=undefined) {
      console.log("posteId", this.posteId)
      this.changeIsCandidateView() 
      this.candidatesForThePoste();
    }
    else{
      this.retrieveUsers();
    }
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe(
        users  => {
          this.users = users;
        },
        error => {
          console.log(error);
        });
  }

  candidatesForThePoste()
  {    
    this.posteService.getCandidates(this.posteId).subscribe(data => {
      this.users=[];
      this.users=data;
      //this.candidatesUser=data;
      console.log("candidates : ",data);
    })
  }

  changeIsCandidateView()
  {
    if (this.isCandidateView==false) this.isCandidateView=true;
  }

  refreshList(): void {
    this.retrieveUsers();
    this.currentUser = undefined;
    this.currentIndex = -1;
  }

  setActiveUser(user: User, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }

  removeAllUsers(): void {
    this.userService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchUsername(): void {
    if(this.username != ""){
      this.userService.findByUsername(this.username)
      .subscribe(
        data => {
          this.users = data;
          console.log(this.users);
        },
        error => {
          console.log(error);
        });
    }else{
      this.retrieveUsers();
    }
    
  }


}
