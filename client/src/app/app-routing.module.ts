/* eslint-disable max-len */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShopListComponent } from './shops/shop-list/shop-list.component';
import { PublicationListComponent } from './publications/publication-list/publication-list.component';
import { ShopOwnerAccessComponent } from './shops/shop-owner-access/shop-owner-access.component';
import { PromotionFormComponent } from './publications/creation/promotion-form/promotion-form.component';
import { LogComponent } from './shops/log/log.component';
import { ParkingListComponent } from './parkings/parking-list/parking-list.component';
import { ParkingOwnerAccessComponent } from './parkings/parking-owner-access/parking-owner-access.component';
import { LogParkingComponent } from './parkings/log-parking/log-parking.component';
import { CityEventListComponent } from './events/city-event-list/city-event-list.component';
import { LeaftletMapComponent } from './leaftlet-map/leaftlet-map.component';
import { EventManagmentComponent } from './events/managment/event-managment/event-managment.component';
import { PluginManagmentComponent } from './plugins/plugin-managment/plugin-managment.component';
import { ShopPublicationFormComponent } from './publications/creation/shop-publication-form/shop-publication-form.component';
import { EventPublicationFormComponent } from './publications/creation/event-publication-form/event-publication-form.component';
import { ParkingInnovationTicketComponent } from './parking-innovation/parking-innovation-ticket/parking-innovation-ticket.component';
import { ClosedParkingSimulationComponent } from './parking-innovation/closed-parking/closed-parking-simulation/closed-parking-simulation.component';
import { ParkingLotScanComponent } from './parking-innovation/closed-parking/parking-lot-scan/parking-lot-scan.component';
import { OutsideParkingSimulationComponent } from './parking-innovation/outside-parking/outside-parking-simulation/outside-parking-simulation.component';
import { OutsideParkingTerminalComponent } from './parking-innovation/outside-parking/outside-parking-terminal/outside-parking-terminal.component';

const routes: Routes = [
  { path: 'client/map', component: LeaftletMapComponent },
  { path: 'client/shops', component: ShopListComponent },
  { path: 'client/publications', component: PublicationListComponent },
  { path: 'client/parkings', component: ParkingListComponent },
  { path: 'client/cityEvents', component: CityEventListComponent },
  { path: 'log-shop', component: LogComponent },
  { path: 'shop', component: ShopOwnerAccessComponent },
  { path: 'shop/publication', component: ShopPublicationFormComponent },
  { path: 'shop/promotion', component: PromotionFormComponent },
  { path: 'parking', component: ParkingOwnerAccessComponent },
  { path: 'log-parking', component: LogParkingComponent },
  { path: 'cityEvent', component: EventManagmentComponent },
  { path: 'cityEvent/publication', component: EventPublicationFormComponent },
  { path: 'innovation/ticket', component: ParkingInnovationTicketComponent },
  { path: 'innovation/ticket/:id', component: ParkingInnovationTicketComponent },
  { path: 'innovation/closed/simulation', component: ClosedParkingSimulationComponent },
  { path: 'innovation/closed/parkingLotScan/simulation', component: ParkingLotScanComponent },
  { path: 'innovation/outside/simulation', component: OutsideParkingSimulationComponent },
  { path: 'innovation/outside/online-terminal', component: OutsideParkingTerminalComponent },
  { path: 'plugins', component: PluginManagmentComponent },
  { path: '**', redirectTo: '/' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
