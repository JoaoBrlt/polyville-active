import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParkingService } from '../../../services/parking.service';
import { Parking } from '../../../models/parking.model';

@Component({
  selector: 'app-log',
  templateUrl: './log-parking.component.html',
  styleUrls: ['./log-parking.component.scss']
})
export class LogParkingComponent implements OnInit {
  private parkings: Parking[];
  private idSelected!: number;

  constructor(
    private parkingService: ParkingService,
    private router: Router
  ) {
    this.parkings = [];
    this.parkingService
      .getParkings()
      .subscribe((parkings: Parking[]) => {
        this.parkings = parkings;
      });
  }

  ngOnInit(): void {
  }

  idExists(): boolean {
    return this.parkings.find((parking) => this.idSelected === parking.id) !== undefined;
  }

  selectParking(e: KeyboardEvent): void {
    if (!e.target) {
      return;
    }
    this.idSelected = parseInt((e.target as HTMLTextAreaElement).value, 10);
  }

  connectParking(): void {
    if (this.parkingService.connectParking(this.idSelected)) {
      this.router.navigate(['/parking']);
    }
  }

  createParking(): void {
    this.parkingService
      .createParking({
        id: this.idSelected,
        capacity: 150,
        availablePlaces: 150,
        name: 'Parking',
        address: 'Address',
        latitude: 43.123456,
        longitude: 7.123456,
        owner: 'Owner',
        price: new Map()
          .set(60,1.0)
          .set(120,2.0)
          .set(180,3.0)
          .set(240,4.0)
          .set(300,5.0)
      })
      .subscribe((p) => {
        this.idSelected = p.id;
        this.connectParking();
      });
  }
}
