import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

export interface Genre {
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class GenresService {

  genres: Genre[] = [];

  constructor(private http: HttpClient) {
  }

  getGenres(){
    return this.http.get('/genres');
  }

}
