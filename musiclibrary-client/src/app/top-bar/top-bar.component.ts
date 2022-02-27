import {Component} from "@angular/core";
import {FileService} from "../file.service";


@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent {

  constructor(
    private fileService: FileService
  ) {}

  download(){

    this.fileService.downloadFile();
  }

}
