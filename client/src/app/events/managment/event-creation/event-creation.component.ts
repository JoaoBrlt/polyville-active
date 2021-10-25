import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {CityEvent} from '../../../../models/event.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../../../services/event.service';

@Component({
  selector: 'app-event-creation',
  templateUrl: './event-creation.component.html',
  styleUrls: ['./event-creation.component.scss']
})
export class EventCreationComponent implements OnChanges, OnInit {
  @Input()
  cityEvent!: CityEvent;

  default: CityEvent = {
    title: '',
    content: '',
    type: '',
    description: '',
    date: new Date(),
    startDate: new Date(Date.now()),
    endDate: new Date(Date.now()),
    location: undefined
  };

  eventForm!: FormGroup;
  creation = true;

  constructor(
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private eventService: EventService
  ) {
  }

  ngOnInit(): void {
    this.initFormBuilder({... this.default});
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.cityEvent) {
      this.initFormBuilder(this.cityEvent);
    } else {
      this.initFormBuilder(this.default);
    }
  }

  createEvent(): void{
    const eventToCreate: CityEvent = {...this.default, ... this.eventForm.getRawValue() as CityEvent};
    this.eventService.createEvent(eventToCreate).subscribe();
    this.initFormBuilder(this.default);
  }

  modifEvent(): void {
    const eventToCreate: CityEvent = {...this.cityEvent, ... this.eventForm.getRawValue() as CityEvent};
    this.eventService
      .updateEvent(eventToCreate)
      .subscribe();
    this.initFormBuilder(this.default);
  }

  private initFormBuilder(cityEvent: CityEvent): void {
    this.creation = !cityEvent.id;
    this.eventForm = this.formBuilder.group({
      title: [cityEvent.title],
      description: [cityEvent.description],
      endDate: [cityEvent.endDate],
      startDate: [cityEvent.startDate]
    });
  }
}
