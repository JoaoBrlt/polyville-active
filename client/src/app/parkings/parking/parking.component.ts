import {Component, Input, OnInit} from '@angular/core';
import {Parking} from '../../../models/parking.model';

@Component({
  selector: 'app-parking',
  templateUrl: './parking.component.html',
  styleUrls: ['./parking.component.scss']
})
export class ParkingComponent implements OnInit {
  @Input()
  parking!: Parking;

  constructor() { }

  ngOnInit(): void {
  }

}
