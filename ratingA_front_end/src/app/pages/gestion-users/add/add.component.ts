import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})

export class AddComponent implements OnInit {
  usersForm: FormGroup;

  constructor(private fb: FormBuilder , private cService: UsersService){
  }

  ngOnInit(): void {
   this.usersForm = this.fb.group({
    firstName:['',Validators.required],
    lastName:['',Validators.required],
    email:['',Validators.required],
    role:['',Validators.required],
    sex:['',Validators.required],
    password:['',Validators.required]


  })

}
get f(){return this.usersForm.controls}

submit() : void {
  if(this.usersForm.invalid){
   // console.log(this.usersForm.value)
   return;
  }else{

    console.log(this.usersForm.value)

    this.cService.Postusers(this.usersForm.value).subscribe(response =>{
      console.log("Success")


    },error=>console.log(error))



  }

}
}
