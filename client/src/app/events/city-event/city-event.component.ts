import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CityEvent} from '../../../models/event.model';
import {EventService} from '../../../services/event.service';

@Component({
  selector: 'app-city-event',
  templateUrl: './city-event.component.html',
  styleUrls: ['./city-event.component.scss']
})
export class CityEventComponent implements OnInit {
  @Input()
  cityEvent!: CityEvent;

  @Input()
  adminAccess = false;

  @Output()
  modifRequest = new EventEmitter<CityEvent>();

  constructor(private eventService: EventService) {
    console.log('cityEvent');
  }

  ngOnInit(): void {
  }

  requestDelete(): void {
    if (this.cityEvent.id) {
      this.eventService.deleteEvent(this.cityEvent.id);
    }
  }

  requestModif(): void {
    this.modifRequest.emit(this.cityEvent);
  }

  translateDate(input: Date): string {
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour:'2-digit', minute:'2-digit' };
    const date = new Date(input);
    return date.toLocaleDateString('fr-FR',options);
  }
}
