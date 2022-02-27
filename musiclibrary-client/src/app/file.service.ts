import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FileService{
  constructor(private http: HttpClient) {
  }
  downloadFile(){
    this.http.get('/file/download',{responseType: 'blob'}).subscribe(data =>{
      let blob = new Blob([data]);

      let downloadURL = window.URL.createObjectURL(data);
      let link = document.createElement('a');
      link.href = downloadURL;
      link.download = "mucislibrary.txt";
      link.click();
    });
  }
}
