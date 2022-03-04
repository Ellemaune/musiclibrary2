import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FileService{
  constructor(private http: HttpClient) {
  }

  uploadFile(fileList: FileList){
    if(fileList.length > 0) {
      let file: File = fileList[0];
      let formData:FormData = new FormData();
      formData.append('attachment', file);
      this.http.post(`/file/upload`, formData).subscribe(() => {console.log("upload finish")});
    }
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
