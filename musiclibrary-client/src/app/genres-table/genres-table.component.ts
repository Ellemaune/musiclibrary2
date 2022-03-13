import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { Genre } from '../services/genres.service';
import { GenresService } from '../services/genres.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import { MatTable, MatTableDataSource } from "@angular/material/table";
import { StoreService } from "../services/store.service";

@Component({
  selector: 'app-genres-table',
  templateUrl: './genres-table.component.html',
  styleUrls: ['./genres-table.component.css'],
  providers: [GenresService]
})
export class GenresTableComponent implements OnInit {

  displayedColumns: string[] = ['name', 'actions'];
  dataSource = new MatTableDataSource<Genre>([]);

  substring: string = '';

  @ViewChild('genresTable') genresTable: MatTable<any> | undefined;

  constructor(private genresService: GenresService, private store: StoreService, public dialog: MatDialog) { }

  ngOnInit(): void {
    // заглушка
    // this.genres = [{"name" : "Рок"}, {"name" : "Поп"}, {"name" : "Шансон"}];

    this.store.ganres.subscribe((data: Genre[]) => {
      this.dataSource.data = data;
    });
  }

  addGenre(): void {
    const dialogRef = this.dialog.open(AddingDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.genresService.addGenre(result);
      }
    });
  }

  deleteGenre(genreName: string): void{
    const dialogRef = this.dialog.open(DeleteDialog, {
      data: genreName
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.genresService.deleteGenre(genreName);
      }
    });

  }

    applyFilter(event: Event) {
        const filterValue = (event.target as HTMLInputElement).value;
        this.dataSource.filter = filterValue.trim().toLowerCase();
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
