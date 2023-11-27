import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NoteService } from '../../../services/note.service';

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

  public listnote: any[] =[];
  @Output() noteEmitted: EventEmitter<any> = new EventEmitter();

constructor( private NoteService:  NoteService){}
  ngOnInit(): void {

this.loadListnote();

//this.getnotePaging();
  }

  loadListnote(): void {
    this.NoteService.getListnote().subscribe(data => {

      this.listnote.push(...data);
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

getnotePaging(): void {
  const params = this.getRequestParams(this.PAGE, this.PAGESIZE);
  this.NoteService.getListnoteByPagination(params)
    .subscribe(response =>{
    const{data,totalItems}=response;

    this.listnote=response.data;
    this.count=totalItems;

    console.log(response.data);

  },error => console.log(error)
    );

}
hundlePageChange(event: number){
  this.PAGE=event;
  this.getnotePaging();

}
hundleSizePageChange(event: any){
  this.PAGESIZE=event.taget.value;
  this.PAGE=1;
  this.getnotePaging();

}

oneDelete =(id: number) =>{

  if(confirm("Are you sure you want to delete")){
    this.NoteService.deletenote(id).subscribe(data =>{
      console.log("suppression effectue");


     },error=> console.log(error )
    );


   }


}
oneUpdate = (notes: any) =>{

  if(confirm("Are you sure you want to update")){
    this.noteEmitted.emit(notes);

}
}

}
