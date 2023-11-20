import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestion-film',
  templateUrl: './gestion-film.component.html',
  styleUrls: ['./gestion-film.component.css']
})
export class GestionFilmComponent implements OnInit {

  public filmshared:any;
  isUpdating:boolean = false;

  constructor(){

  }
  ngOnInit(): void {

  }
  get =($event: any) => {
    this.isUpdating = true;
    this.filmshared=$event;
  }
  }
