import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { StoreService } from "./store.service";

export interface Genre {
    name: string;
}

@Injectable({
    providedIn: "root"
})
export class GenresService {

    constructor(private http: HttpClient, private store: StoreService) {
    }

    addGenre(additionalUrl: string): void {
        this.http.post<any>("/genres/addGenre/" + additionalUrl, null).subscribe(() => this.store.reloadData());
    }

    deleteGenre(additionalURL: string): void {
        this.http.delete("/genres/removeGenres/" + additionalURL).subscribe(() => this.store.reloadData());
    }

}
