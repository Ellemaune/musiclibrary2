import { Component, OnInit } from '@angular/core';
import { genres } from '../genres';
import { Genre } from "../genres";

@Component({
  selector: 'app-genres-table',
  templateUrl: './genres-table.component.html',
  styleUrls: ['./genres-table.component.css']
})
export class GenresTableComponent implements OnInit {

  displayedColumns: string[] = ['name'];
  dataSource = genres;

  constructor() { }

  ngOnInit(): void {
  }

}
