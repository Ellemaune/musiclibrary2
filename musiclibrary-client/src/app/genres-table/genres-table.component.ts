import { Component, OnInit } from '@angular/core';
import { Genre } from '../genres.service';
import { GenresService } from '../genres.service';

@Component({
  selector: 'app-genres-table',
  templateUrl: './genres-table.component.html',
  styleUrls: ['./genres-table.component.css'],
  providers: [GenresService]
})
export class GenresTableComponent implements OnInit {

  genres: Genre[] = [];
  displayedColumns: string[] = ['name'];

  constructor(private genresService: GenresService) { }

  ngOnInit(): void {
    this.genresService.getGenres().subscribe((data: any) => this.genres=data);
  }

}
