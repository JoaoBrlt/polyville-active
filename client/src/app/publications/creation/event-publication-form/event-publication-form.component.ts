import { Component, OnInit } from '@angular/core';
import { CityEvent } from '../../../../models/event.model';
import { EventService } from '../../../../services/event.service';

@Component({
  selector: 'app-event-publication-form',
  templateUrl: './event-publication-form.component.html',
  styleUrls: ['./event-publication-form.component.scss']
})
export class EventPublicationFormComponent implements OnInit {

  cityEventList: CityEvent[] = [];
  eventSelected!: CityEvent | undefined;

  constructor(private eventService: EventService) {  }

  ngOnInit(): void {
    this.getCityEvents();
  }

  selectEvent(index: number): void {
    this.eventSelected = undefined;
    this.eventSelected = this.cityEventList[index];
    console.log('envent selected',this.eventSelected);

  }

  private getCityEvents(): void {
    this.eventService
      .getEvents()
      .subscribe((cityEvents: CityEvent[]) => {
        if (!cityEvents) { return; }
        console.log('cityEventsList got :', cityEvents);
        this.cityEventList = cityEvents;
      });
  }
}
