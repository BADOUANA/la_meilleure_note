import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  CURRENTINDEX = -1;
  PAGE = 0;
  count=0;
  PAGESIZE = 3;

  public listUsers: any[] =[];
  @Output() UserEmitted: EventEmitter<any> = new EventEmitter();

constructor( private UserService:  UsersService){}
  ngOnInit(): void {

this.loadListusers();

//this.getusersPaging();
  }

  loadListusers(): void {
    this.UserService.getListUsers().subscribe(data => {

      this.listUsers.push(...data);
    }, err => console.log(err)

      )

}

getRequestParams(page:number , pageSize:number):any {
let params: any = {};
if(page){
  params['page'] = page - 1;
}
if(pageSize){
  params['size'] = pageSize;

}
return params;
}

getusersPaging(): void {
  const params = this.getRequestParams(this.PAGE, this.PAGESIZE);
  this.UserService.getListusersByPagination(params)
    .subscribe(response =>{
    const{data,totalItems}=response;

    this.listUsers=response.data;
    this.count=totalItems;

    console.log(response.data);

  },error => console.log(error)
    );

}
hundlePageChange(event: number){
  this.PAGE=event;
  this.getusersPaging();

}
hundleSizePageChange(event: any){
  this.PAGESIZE=event.taget.value;
  this.PAGE=1;
  this.getusersPaging();



}

oneDelete =(id: number) =>{

  if(confirm("Are you sure you want to delete")){
    this.UserService.deleteusers(id).subscribe(data =>{
      console.log("suppression effectue");


     },error=> console.log(error )
    );


   }


}
oneUpdate = (user: any) =>{

  if(confirm("Are you sure you want to update")){
    this.UserEmitted.emit(user);



}
}

}
