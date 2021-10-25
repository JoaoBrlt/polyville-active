import { Component, OnInit } from '@angular/core';
import {ParkingService} from '../../../services/parking.service';
import {Parking} from '../../../models/parking.model';

@Component({
  selector: 'app-parking-list',
  templateUrl: './parking-list.component.html',
  styleUrls: ['./parking-list.component.scss']
})
export class ParkingListComponent implements OnInit {
  parkingList: Parking[] = [];

  constructor(private parkingService: ParkingService) { this.getParkings(); }

  ngOnInit(): void {
  }

  private getParkings(): void {
    this.parkingService
      .getParkings()
      .subscribe((parkings: Parking[]) => {
        this.parkingList = parkings;
      });
  }
}
