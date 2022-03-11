import {Component, Inject, OnInit, ViewChild} from "@angular/core";
import { Track } from '../services/tracks.service';
import {TracksService} from '../services/tracks.service'
import {MatTable} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {FormControl} from "@angular/forms";


@Component({
  selector:'app-tracks-table',
  templateUrl: './tracks-table.component.html',
  styleUrls:['tracks-table.component.css'],
  providers: [TracksService]

})

export  class TracksTableComponent implements  OnInit{
  tracks: Track[] = [];
  foundTracks: Track[] = [];

  dataSource: Track[] = [];
  displayedColumns: string[] = ['name','singer','album','recordLength','genre', 'actions'];

  substring: string = '';
  addingResult: string = '';
  constructor(private tracksService: TracksService, public dialog: MatDialog) {
  }

  @ViewChild('tracksTable') tracksTable: MatTable<any> | undefined;

  ngOnInit(): void {

    this.tracksService.getTracks().subscribe((data:any) => {
      this.tracks=data;
      this.dataSource = this.tracks;
    });
  }

  refreshTable(): void{
    this.tracksService.getTracks().subscribe((data:any)=>{
      this.tracks=data;
      this.dataSource = this.tracks;
      this.tracksTable?.renderRows();
    });
  }

  deleteTrack(trackName: string): void{
    const dialogRef = this.dialog.open(TrackDeleteDialog, {
      data: trackName
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.tracksService.deleteTrack(trackName).subscribe(() => {
          this.refreshTable();
        });
      }
    });
  }

  addTrack(): void { //
    const dialogRef = this.dialog.open(TrackAddingDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){//тут некий набор инструкций для даты (резалт) сделать строку
        this.addingResult = result.data.name
                    + ',' + result.data.singer
                    + ',' + result.data.album
                    + ',' + result.data.recordLength
                    + ',' + result.data.genre;
        this.tracksService.addTrack(this.addingResult).subscribe(() => {
          this.refreshTable();
        });
      }
    });
  }

  searchBySubstring(){
    this.foundTracks = [];
    if(this.substring != ''){
      this.tracks.forEach((item) => {
        if(item.name.toLowerCase().search(this.substring.toLowerCase()) != -1){
          this.foundTracks.push(item);
        }
      })
      this.dataSource = this.foundTracks;
    }
    else{
      this.dataSource = this.tracks;
    }
    this.tracksTable?.renderRows();
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

