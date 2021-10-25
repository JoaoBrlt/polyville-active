import { Component, Input, OnInit } from '@angular/core';
import {Publication} from '../../../../models/publication.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {PublicationService} from '../../../../services/publication.service';

@Component({
  selector: 'app-publication-form',
  templateUrl: './publication-form.component.html',
  styleUrls: ['./publication-form.component.scss']
})
export class PublicationFormComponent implements OnInit {
  @Input()
  owner!: any;
  @Input()
  ownerAttributeName ='owner';

  @Input()
  postRoute = '/publications';

  publication: Publication = {
    title: '',
    content: '',
    type: '',
    date: new Date(),
  };
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

  createPublication(): void{
    const publicationToCreate: Publication = {... this.publicationForm.getRawValue(), [this.ownerAttributeName]:this.owner } as Publication;
    this.publicationService.createPublication(publicationToCreate, this.postRoute).subscribe();
  }

  private initFormBuilder(): void {
    this.publicationForm = this.formBuilder.group({
      title: [this.publication.title],
      content: [this.publication.content]
    });
  }

}
