import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  @Input() users:any [];
  a: any[];
  usersForm: FormGroup;
  submitted : boolean = false;
  listusers: any[] = [];

  constructor(private fb: FormBuilder ,private cService: UsersService){ }

  ngOnInit(): void {

      this.usersForm = this.fb.group({

        adresse:['',Validators.required],
        email:['',Validators.required],
        nom:['',Validators.required],
        sexe:['',Validators.required],
        prenom:['',Validators.required],
        telephone:['',Validators.required],
        date_naissance:['',Validators.required]
      })
    this.setusers(this.users);
  }
  get f() {
    return this.usersForm.controls;
    }

submit() : void {
  this.submitted=true;
  if(this.usersForm.invalid){
    return;
  }else{
    const data= {
      adresse:this.f['adresse'].value,
      email:this.f['email'].value,
      nom : this.f['nom'].value,
      sexe : this.f['sexe'].value,
      prenom : this.f['prenom'].value,
      telephone : this.f['telephone'].value,
      date_naissance : this.f['date_naissance'].value

    }
    this.cService.Updateusers(data,this.users.push('id')).subscribe(response => {
      this.usersForm.reset();
     }, err => {
      console.log(err)
    });
  }
}
  setusers=(c:any) => {

    this.f['adresse'].setValue(c.adresse);
    this.f['email'].setValue(c.email);
    this.f['nom'].setValue(c.nom);
    this.f['sexe'].setValue(c.sexe);
    this.f['prenom'].setValue(c.prenom);
    this.f['telephone'].setValue(c.telephone);
    this.f['date_naissance'].setValue(c.date_naissance);



  }



  }
