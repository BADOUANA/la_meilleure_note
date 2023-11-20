import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestion-users',
  templateUrl: './gestion-users.component.html',
  styleUrls: ['./gestion-users.component.css']
})
export class GestionUsersComponent implements OnInit {

  public Usershared:any;
  isUpdating:boolean = false;

  constructor(){

  }
  ngOnInit(): void {

  }
  get =($event: any) => {
    this.isUpdating = true;
    this.Usershared=$event;
  }
  }

