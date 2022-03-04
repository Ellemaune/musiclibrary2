import {Component, ElementRef, ViewChild} from "@angular/core";
import {FileService} from "../services/file.service";

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent {
  constructor(
    private fileService: FileService,
  ) {}

  upload(event: any){
    let fileList: FileList = event.target.files;
    this.fileService.uploadFile(fileList);
  }

  download(){
    this.fileService.downloadFile();
  }

}
