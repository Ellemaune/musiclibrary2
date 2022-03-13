import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { Genre } from "./genres.service";
import {Track} from "./tracks.service";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class StoreService {
    private _ganres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);
    private _tracks: BehaviorSubject<Track[]> = new  BehaviorSubject<Track[]>([]);

    constructor(private http: HttpClient) {
        this.reloadData();
    }

    get ganres(): Observable<Genre[]> {
        return this._ganres.asObservable();
    }

    get tracks(): Observable<Track[]>{
      return this._tracks.asObservable();
    }

    reloadData(): void {
        this.http.get<Genre[]>('/genres').subscribe(data => this._ganres.next(data));
        this.http.get<Track[]>('/tracks').subscribe(data => this._tracks.next(data));
    }
}
