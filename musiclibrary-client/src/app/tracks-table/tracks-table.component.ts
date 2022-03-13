import {Component, Inject, OnInit, ViewChild} from "@angular/core";
import { Track } from '../services/tracks.service';
import {TracksService} from '../services/tracks.service'
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import { StoreService } from "../services/store.service";

@Component({
  selector:'app-tracks-table',
  templateUrl: './tracks-table.component.html',
  styleUrls:['tracks-table.component.css'],
  providers: [TracksService]

})

export  class TracksTableComponent implements  OnInit{

  dataSource = new MatTableDataSource<Track>([]);
  displayedColumns: string[] = ['name','singer','album','recordLength','genre', 'actions'];

  substring: string = '';

  addingResult: string = '';
  name: string= '';
  singer: string= '';
  album: string= '';
  recordLength: string= '';
  genre: string= '';

  constructor(private tracksService: TracksService, public dialog: MatDialog, private store: StoreService) {
  }

  @ViewChild('tracksTable') tracksTable: MatTable<any> | undefined;

  ngOnInit(): void {

    this.store.tracks.subscribe((data: Track[]) => {
      this.dataSource.data = data;
    });
  }


  applyFilter(event:Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  deleteTrack(trackName: string): void{
    const dialogRef = this.dialog.open(TrackDeleteDialog, {
      data: trackName
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.tracksService.deleteTrack(trackName);
           }
        });
      }


  addTrack(): void { //
    const dialogRef = this.dialog.open(TrackAddingDialog, {
      width: '300px',
      data: {name: this.name, singer: this.singer, album: this.album, recordLength: this.recordLength, genre: this.genre}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.name = result.name;
        this.singer = result.singer;
        this.album = result.album;
        this.recordLength = result.recordLength;
        this.genre = result.genre;
        this.addingResult = this.name
                    + ',' + this.singer
                    + ',' + this.album
                    + ',' + this.recordLength
                    + ',' + this.genre;
        this.tracksService.addTrack(this.addingResult);
        this.name = '';
        this.singer = '';
        this.album = '';
        this.recordLength = '';
        this.genre = '';
      }
    });
  }


}
@Component({
  selector: 'app-adding-dialog',
  templateUrl: 'tracks-adding-dialog.html'
})
export class TrackAddingDialog {
   constructor(
    public dialogRef: MatDialogRef<TrackAddingDialog>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogData) {}
}
export interface ConfirmDialogData {
  name: string,
  singer: string,
  album: string,
  recordLength: string,
  genre: string

}
@Component({
  selector: 'app-delete-dialog',
  templateUrl: 'tracks-delete-dialog.html',
})
export class TrackDeleteDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: string){
  }
}

