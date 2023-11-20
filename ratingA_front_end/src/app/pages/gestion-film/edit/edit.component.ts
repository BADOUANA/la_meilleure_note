import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  @Input() film:any [];
  a: any[];
  filmForm: FormGroup;
  submitted : boolean = false;
  listfilm: any[] = [];

  constructor(private fb: FormBuilder ,private cService: FilmService){ }

  ngOnInit(): void {

      this.filmForm = this.fb.group({

        titre:['',Validators.required],
        autheur:['',Validators.required],
        date_sortie:['',Validators.required],
      })
    this.setfilm(this.film);
  }
  get f() {
    return this.filmForm.controls;
    }

submit() : void {
  this.submitted=true;
  if(this.filmForm.invalid){
    return;
  }else{
    const data= {
      titre:this.f['tit'].value,
      autheur:this.f['autheur'].value,
      date_sortie : this.f['date_sortie'].value,


    }
    this.cService.Updatefilm(data,this.film.push('id')).subscribe(response => {
      this.filmForm.reset();
     }, err => {
      console.log(err)
    });
  }
}
  setfilm=(c:any) => {

    this.f['titre'].setValue(c.titre);
    this.f['autheur'].setValue(c.autheur);
    this.f['date_sortie'].setValue(c.date_sortie);
    this.f['categorie'].setValue(c.categorie);




  }



  }

