import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-principale',
  templateUrl: './principale.component.html',
  styleUrls: ['./principale.component.css']
})
export class PrincipaleComponent implements OnInit {

  public filmshared:any;
  isNoting:boolean = false;

  constructor(){

  }
  ngOnInit(): void {

  }
  get =($event: any) => {
    this.isNoting = true;
    this.filmshared=$event;
  }
  }
