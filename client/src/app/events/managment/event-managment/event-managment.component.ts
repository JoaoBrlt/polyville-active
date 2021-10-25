import { Component, OnInit } from '@angular/core';
import {CityEvent} from '../../../../models/event.model';
import {EventService} from '../../../../services/event.service';

@Component({
  selector: 'app-event-managment',
  templateUrl: './event-managment.component.html',
  styleUrls: ['./event-managment.component.scss']
})
export class EventManagmentComponent implements OnInit {
  cityEventList: CityEvent[] = [];
  eventSelected!: CityEvent;

  constructor(private eventService: EventService) {  }

  ngOnInit(): void {
    this.getCityEvents();
  }

  selectEvent(output: CityEvent): void {
    this.eventSelected = output;
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
