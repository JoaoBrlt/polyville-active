import { Component, OnInit } from '@angular/core';
import { CityEvent } from '../../../models/event.model';
import { EventService } from '../../../services/event.service';

@Component({
  selector: 'app-city-event-list',
  templateUrl: './city-event-list.component.html',
  styleUrls: ['./city-event-list.component.scss']
})
export class CityEventListComponent implements OnInit {
  cityEvents: CityEvent[] = [];

  constructor(private eventService: EventService) {
    this.getEvents();
  }

  ngOnInit(): void {
  }

  private getEvents(): void {
    this.eventService
      .getEvents()
      .subscribe((cityEvents: CityEvent[]) => {
        this.cityEvents = cityEvents;
      });
  }
}
