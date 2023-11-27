import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from '../../../services/users.service';
import { FilmService } from '../../../services/film.service';
import { NoteService } from '../../../services/note.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit{
  noteForm: FormGroup;
  listUser:any[]=[];
  listFilm:any[]=[];
  submitted:boolean = false;

  constructor(private fb: FormBuilder, private UsersService: UsersService , private FilmService: FilmService, private NoteService: NoteService){

  }

  ngOnInit(): void {
    this.submitted = true;
    this.noteForm = this.fb.group({
      name:['',Validators.required],
      note:['',Validators.required],
      summary:['',Validators.required],
      detailSummary:['',Validators.required],
      film_id: ['',Validators.required],
      user_id: ['',Validators.required]
  })
  this.getFilm();
  this.getUser();
}
getFilm():void {
  this.FilmService.getListfilm().subscribe(data => {
    this.listFilm.push(...data);
    console.log(data);
  }, err => {
    console.log(err);

  })
}

getUser():void {
  this.UsersService.getListUsers().subscribe(data => {
    this.listUser.push(...data);
    console.log(data);
},err => {
  console.log(err);

})

}

get f(){return this.noteForm.controls}

submit() : void {
  if(this.noteForm.invalid){
   // console.log(this.noteForm.value)
    return;
  }else{
    console.log(this.noteForm.value)

    this.NoteService.Postnote(this.noteForm.value).subscribe(response =>{
      console.log("success")


    },error=>console.log(error))
  }

}
}
