import { Component, Input, OnInit } from '@angular/core';
import {Publication} from '../../../models/publication.model';

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  styleUrls: ['./publication.component.scss']
})
export class PublicationComponent implements OnInit {
  @Input()
  publication!: Publication;

  constructor() { }

  ngOnInit(): void {
  }

}
