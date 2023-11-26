import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FilmService } from 'src/app/services/film.service';


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

  public listfilm: any[] =[];
  @Output() filmEmitted: EventEmitter<any> = new EventEmitter();

constructor( private filmService:  FilmService){}
  ngOnInit(): void {

this.loadListfilm();

//this.getfilmPaging();
  }

  loadListfilm(): void {
    this.filmService.getListfilm().subscribe(data => {

      this.listfilm.push(...data);
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

getfilmPaging(): void {
  const params = this.getRequestParams(this.PAGE, this.PAGESIZE);
  this.filmService.getListfilmByPagination(params)
    .subscribe(response =>{
    const{data,totalItems}=response;

    this.listfilm=response.data;
    this.count=totalItems;

    console.log(response.data);

  },error => console.log(error)
    );

}
hundlePageChange(event: number){
  this.PAGE=event;
  this.getfilmPaging();

}
hundleSizePageChange(event: any){
  this.PAGESIZE=event.taget.value;
  this.PAGE=1;
  this.getfilmPaging();

}

oneDelete =(id: number) =>{

  if(confirm("Are you sure you want to delete")){
    this.filmService.deletefilm(id).subscribe(data =>{
      console.log("suppression effectue");


     },error=> console.log(error )
    );


   }


}
oneUpdate = (films: any) =>{

  if(confirm("Are you sure you want to update")){
    this.filmEmitted.emit(films);

}
}

}
