import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  filmForm: FormGroup;
  constructor(private fb: FormBuilder , private cService: FilmService){
  }
  ngOnInit(): void {
   this.filmForm = this.fb.group({
    titre:['',Validators.required],
    autheur:['',Validators.required],
    date_sortie:['',Validators.required],
    categorie:['',Validators.required],
    image:['',Validators.required]

  })

}
get f(){return this.filmForm.controls}

submit() : void {
  if(this.filmForm.invalid){
    return;
  }else{
    console.log(this.filmForm.value)


    this.cService.Postfilm(this.filmForm.value).subscribe(response =>{
      console.log("Success")


    },error=>console.log(error))



  }

}
}
