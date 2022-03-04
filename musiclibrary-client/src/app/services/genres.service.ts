import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

export interface Genre {
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class GenresService {

  constructor(private http: HttpClient) {
  }

  getGenres(){
    return this.http.get('/genres');
  }

  addGenre(additionalUrl: string){
    return this.http.post<any>('/genres/addGenre/' + additionalUrl, null);
  }

  deleteGenre(additionalURL: string){
    return this.http.delete('/genres/removeGenres/' + additionalURL);
  }

}
