import { ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

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

 
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe(
        users  => {
          // users.forEach(function(user){
          //   const parsedUser = JSON.parse(user);
          // })
          this.users = users;
          console.log(users);
          console.log("role = " + users[0].role);
        },
        error => {
          console.log(error);
        });
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
