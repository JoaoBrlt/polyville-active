import { Component, OnInit } from '@angular/core';
import {Publication} from '../../../models/publication.model';
import {PublicationService} from '../../../services/publication.service';

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.scss']
})
export class PublicationListComponent implements OnInit {
  publicationList: Publication[] = [];

  constructor(private publicationService: PublicationService) {
    this.getPublications();
  }

  ngOnInit(): void {
  }

  private getPublications(): void{
    this.publicationService
      .getPublications()
      .subscribe((publications: Publication[]) => {
        this.publicationList = publications;
      });
  }

}
