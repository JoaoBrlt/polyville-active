import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ParkingService } from '../../../services/parking.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Parking } from '../../../models/parking.model';

@Component({
  selector: 'app-parking-owner-access',
  templateUrl: './parking-owner-access.component.html',
  styleUrls: ['./parking-owner-access.component.scss']
})
export class ParkingOwnerAccessComponent implements OnInit {
  parkingId!: number;
  parking!: Parking;
  parkingForm!: FormGroup;

  private debugOn = true;
  private lastSub!: Subscription;

  constructor(
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private parkingService: ParkingService
  ) {
  }

  ngOnInit(): void {
    this.parkingId = this.parkingService.getConnectedParkingId();
    if (!this.parkingId) {
      return;
    }
    this.getParking();
  }

  updateParking(): void {
    this.parking = {...this.parking, ...this.parkingForm.getRawValue()} as Parking;
    this.parkingService
      .updateParking(this.parking)
      .subscribe();

    // @ts-ignore
    document.getElementById('msg-parking-form').innerHTML = 'Enregistré !';
  }

  private getParking(): void {
    if (this.lastSub) {
      this.lastSub.unsubscribe();
    }

    this.debug('request parking', this.parkingId);

    this.lastSub = this.parkingService.getParking(this.parkingId).subscribe((parking) => {
      if (!parking) {
        return;
      }
      this.parking = parking;

      this.debug('parking got', this.parking);

      this.initFormBuilder();
    });
  }

  private initFormBuilder(): void {
    this.parkingForm = this.formBuilder.group({
      name: [this.parking.name],
      capacity: [this.parking.capacity]
    });
  }

  private debug(...data: any[]): void {
    if (!this.debugOn) {
      return;
    }
    console.log(data);
  }
}
