import {Component, Inject, OnInit, ViewChild} from "@angular/core";
import { Track } from '../services/tracks.service';
import {TracksService} from '../services/tracks.service'
import {MatTable} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


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
  constructor(private tracksService: TracksService, public dialog: MatDialog) {
  }

  @ViewChild('tracksTable') tracksTable: MatTable<any> | undefined;

  ngOnInit(): void {
    // заглушка
    // this.tracks = [{"name":"Песня4","singer":"Песня4","album":"Альбом4","recordLength":"400","genre":"Рок" }];
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

  addTrack(): void {
    const dialogRef = this.dialog.open(TrackAddingDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.tracksService.addTrack(result).subscribe(() => {
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

@Component({
  selector: 'app-delete-dialog',
  templateUrl: 'tracks-delete-dialog.html',
})
export class TrackDeleteDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: ConfirmDialogData){
  }
}
export interface ConfirmDialogData {
  name: string,
  singer: string,
  album: string,
  recordLength: string,
  genre: string
}
