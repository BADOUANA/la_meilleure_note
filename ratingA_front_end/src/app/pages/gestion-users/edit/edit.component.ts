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
        firstName:['',Validators.required],
        lastName:['',Validators.required],
        email:['',Validators.required],
        role:['',Validators.required],
        sex:['',Validators.required],
        password:['',Validators.required]
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
      firstName:this.f['firstName'].value,
      lastName : this.f['lastName'].value,
      email:this.f['email'].value,
      sex : this.f['sex'].value,
      role : this.f['role'].value,
      password : this.f['password'].value

    }
    this.cService.Updateusers(data,this.users.push('id')).subscribe(response => {
      this.usersForm.reset();
     }, err => {
      console.log(err)
    });
  }
}
  setusers=(c:any) => {

    this.f['firstName'].setValue(c.firstName);
    this.f['email'].setValue(c.email);
    this.f['lastName'].setValue(c.lastName);
    this.f['sexe'].setValue(c.sexe);
    this.f['role'].setValue(c.role);
    this.f['password'].setValue(c.password);



  }



  }
