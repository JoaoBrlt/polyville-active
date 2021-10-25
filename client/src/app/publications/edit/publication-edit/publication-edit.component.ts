import { Component, OnInit } from '@angular/core';
import {Publication} from '../../../../models/publication.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {PublicationService} from '../../../../services/publication.service';

@Component({
  selector: 'app-publication-edit',
  templateUrl: './publication-edit.component.html',
  styleUrls: ['./publication-edit.component.scss']
})
export class PublicationEditComponent implements OnInit {
  selectedPublication: Publication | undefined;

  publicationForm!: FormGroup;

  constructor(
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private publicationService: PublicationService
  ) {
  }

  ngOnInit(): void {
    this.initFormBuilder();
    this.route.queryParams.subscribe(params => {

    });
  }

  createPromotion(): void{
    const publicationToCreate: Publication = this.publicationForm.getRawValue() as Publication;
  }

  private initFormBuilder(): void {
    this.publicationForm = this.formBuilder.group({
      title: [this.selectedPublication?.title],
      content: [this.selectedPublication?.content]
    });
  }

}
