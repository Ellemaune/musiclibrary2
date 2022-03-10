import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

export interface Track {
  name: string;
  singer: string;
  album: string;
  recordLength: string;
  genre: string;
}

@Injectable({
  providedIn: 'root'
})
export class TracksService {

  constructor(private http: HttpClient) {
  }

  getTracks(){
    return this.http.get('/tracks');
  }

  addTrack(additionalUrl: string){
    return this.http.post<any>('/tracks/addTracks/' + additionalUrl, null);
  }

  deleteTrack(additionalURL: string){
    return this.http.delete('/tracks/removeTracks/' + additionalURL);
  }

}
