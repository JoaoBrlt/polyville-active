import { Component, OnDestroy, OnInit } from '@angular/core';
import * as Leaflet from 'leaflet';
import { Map, latLng, tileLayer, Layer, marker, icon} from 'leaflet';
import { ShopService } from '../../services/shop.service';
import { Shop } from '../../models/shop.model';
import { Parking } from '../../models/parking.model';
import { ParkingService } from '../../services/parking.service';
import { PlaceService } from '../../services/place.service';
import { Place } from '../../models/place.model';



@Component({
  selector: 'app-leaftlet-map',
  templateUrl: './leaftlet-map.component.html',
  styleUrls: ['./leaftlet-map.component.scss']
})
export class LeaftletMapComponent implements OnInit, OnDestroy {
  title = 'leafletApps';
  // @ts-ignore
  map: Leaflet.Map;

  shopList: Shop[] = [];
  parkingList: Parking[] = [];
  placeList: Place[] = [];

  constructor(private shopService: ShopService,
              private parkingService: ParkingService,
              private placeService: PlaceService) {
  }

  ngOnInit(): void {
    this.map = Leaflet.map('map').setView([43.6155549, 7.0718582], 5);
    Leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'edupala.com © Angular LeafLet',
    }).addTo(this.map);

    Leaflet.marker([43.6155549, 7.0718582]).addTo(this.map).bindPopup('Polytech Nice Sophia').openPopup();
    //this.getShops();
    //this.getParkings();
    this.getPlaces();
  }


  ngOnDestroy(): void {
    this.map.remove();
  }

  private getShops(): void {
    this.shopService
      .getShops()
      .subscribe((shops: Shop[]) => {
        this.shopList = shops;
      });
    setTimeout(() => {
      this.addShopMarkers();
    }, 1000);
  }

  private addShopMarkers(): void {
    console.log('addShopMarkers');
    for (const shop of this.shopList) {
      if (shop.latitude !== null && shop.longitude !== null) {
        // console.log('latitute and longitude not null');
        // console.log(shop.latitude);
        // console.log(shop.longitude);
        Leaflet.marker([shop.latitude, shop.longitude]).addTo(this.map).bindPopup(shop.name).openPopup();
      }
    }
  }

  private getParkings(): void {
    this.parkingService
      .getParkings()
      .subscribe((parkings: Parking[]) => {
        this.parkingList = parkings;
      });
    setTimeout(() => {
      this.addParkingMarkers();
    }, 1000);
  }

  private addParkingMarkers(): void {
    console.log('addParkingMarkers');
    for (const parking of this.parkingList) {
      if (parking.latitude !== null && parking.longitude !== null) {
        // console.log('latitute and longitude not null');
        // console.log(shop.latitude);
        // console.log(shop.longitude);
        Leaflet.marker([parking.latitude, parking.longitude]).addTo(this.map).bindPopup(parking.name).openPopup();
      }
    }
  }

  private getPlaces(): void {
    this.placeService
      .getPlaces()
      .subscribe((places: Place[]) => {
        this.placeList = places;
      });
    setTimeout(() => {
      this.addPlacesMarkers();
    }, 1000);
  }

  private addPlacesMarkers(): void {
    console.log('addPlacesMarkers');
    for (const place of this.placeList) {
      if (place.latitude !== null && place.longitude !== null) {
        // console.log('latitute and longitude not null');
        // console.log(shop.latitude);
        // console.log(shop.longitude);
        Leaflet.marker([place.latitude, place.longitude]).addTo(this.map).bindPopup(place.name).openPopup();
      }
    }
  }
}
