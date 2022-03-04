import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { Genre } from '../services/genres.service';
import { GenresService } from '../services/genres.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatTable} from "@angular/material/table";

@Component({
  selector: 'app-genres-table',
  templateUrl: './genres-table.component.html',
  styleUrls: ['./genres-table.component.css'],
  providers: [GenresService]
})
export class GenresTableComponent implements OnInit {

  genres: Genre[] = [];
  foundGenres: Genre[] = [];

  dataSource: Genre[] = [];
  displayedColumns: string[] = ['name', 'actions'];

  substring: string = '';

  @ViewChild('genresTable') genresTable: MatTable<any> | undefined;

  constructor(private genresService: GenresService, public dialog: MatDialog) { }

  ngOnInit(): void {
    // заглушка
    // this.genres = [{"name" : "Рок"}, {"name" : "Поп"}, {"name" : "Шансон"}];

    this.genresService.getGenres().subscribe((data: any) => {
      this.genres=data;
      this.dataSource = this.genres;
    });
  }

  addGenre(): void {
    const dialogRef = this.dialog.open(AddingDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.genresService.addGenre(result).subscribe(() => {
          this.refreshTable();
        });
      }
    });
  }

  deleteGenre(genreName: string): void{
    const dialogRef = this.dialog.open(DeleteDialog, {
      data: genreName
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.genresService.deleteGenre(genreName).subscribe(() => {
          this.refreshTable();
        });
      }
    });

  }

  refreshTable(): void{
    this.genresService.getGenres().subscribe((data: any) => {
      this.genres=data;
      this.dataSource = this.genres;
      this.genresTable?.renderRows();
    });
  }

  searchBySubstring(){
    this.foundGenres = [];
    if(this.substring != ''){
      this.genres.forEach((item) => {
        if(item.name.toLowerCase().search(this.substring.toLowerCase()) != -1){
          this.foundGenres.push(item);
        }
      })
      this.dataSource = this.foundGenres;
    }
    else{
      this.dataSource = this.genres;
    }
    this.genresTable?.renderRows();
  }

}

@Component({
  selector: 'app-adding-dialog',
  templateUrl: 'adding-dialog.html'
})
export class AddingDialog {
  constructor(
    public dialogRef: MatDialogRef<AddingDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {}
}

@Component({
  selector: 'app-delete-dialog',
  templateUrl: 'delete-dialog.html',
})
export class DeleteDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: string){
  }
}
