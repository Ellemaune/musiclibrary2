import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { Genre } from '../genres.service';
import { GenresService } from '../genres.service';
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

  openAddingDialog(): void {
    const dialogRef = this.dialog.open(AddingDialogComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.genresService.addGenre(result).subscribe(()=>{this.refreshTable()});

      console.log('The dialog was closed. genres: ' + JSON.stringify(this.genres));
    });
    ;
  }

  // дописать удаление
  // deleteGenre(genreName: string): void{
  //   this.genresService.deleteGenre(genreName);
  // }

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
  templateUrl: 'adding-dialog.component.html'
})
export class AddingDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<AddingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {}

  onCancelClick(): void {
    this.dialogRef.close();
  }


}
