import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { Genre } from "./genres.service";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class StoreService {
    private _ganres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);

    constructor(private http: HttpClient) {
        this.reloadData();
    }

    get ganres(): Observable<Genre[]> {
        return this._ganres.asObservable();
    }

    reloadData(): void {
        this.http.get<Genre[]>('/genres').subscribe(data => this._ganres.next(data));
        // todo reload and update tracks
    }
}
