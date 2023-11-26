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
        title:['',Validators.required],
        author:['',Validators.required],
        outDate:['',Validators.required],
        time:['',Validators.required],
        categories:['',Validators.required]
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
      title:this.f['tit'].value,
      author:this.f['author'].value,
      outDate : this.f['outDate'].value,
      time : this.f['time'].value,
      categories : this.f['categories'].value,


    }
    this.cService.Updatefilm(data,this.film.push('id')).subscribe(response => {
      this.filmForm.reset();
     }, err => {
      console.log(err)
    });
  }
}
  setfilm=(c:any) => {

    this.f['title'].setValue(c.title);
    this.f['author'].setValue(c.author);
    this.f['outDate'].setValue(c.outDate);
    this.f['categories'].setValue(c.categories);
    this.f['time'].setValue(c.time);




  }



  }

