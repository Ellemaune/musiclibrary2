import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import { Genre } from '../genres.service';
import { GenresService } from '../genres.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatTable} from "@angular/material/table";
import {stringify} from "@angular/compiler/src/util";

@Component({
  selector: 'app-genres-table',
  templateUrl: './genres-table.component.html',
  styleUrls: ['./genres-table.component.css'],
  providers: [GenresService]
})
export class GenresTableComponent implements OnInit {

  genres: Genre[] = [];

  displayedColumns: string[] = ['name', 'actions'];

  @ViewChild('genresTable') genresTable: MatTable<any> | undefined;

  constructor(private genresService: GenresService, public dialog: MatDialog) { }

  ngOnInit(): void {
    // this.genres = [{"name" : "Рок"}, {"name" : "Поп"}, {"name" : "Шансон"}];
    this.genresService.getGenres().subscribe((data: any) => this.genres=data);
  }

  openAddingDialog(): void {
    const dialogRef = this.dialog.open(AddingDialogComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.genres.push({"name": result});
      this.genresService.addGenre(result);
      this.refreshTable();
      console.log('The dialog was closed. genres: ' + JSON.stringify(this.genres));
    });

  }

  // deleteGenre(genreName: string): void{
  //   this.genresService.deleteGenre(genreName);
  // }

  refreshTable(): void{
    this.genresService.getGenres().subscribe((data: any) => this.genres=data);
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
