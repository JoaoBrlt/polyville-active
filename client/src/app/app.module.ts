/* eslint-disable max-len */
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';

// Angular Material.
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

// QRCode and Barcode.
import { QRCodeModule } from 'angularx-qrcode';
import { NgxBarcodeModule } from 'ngx-barcode';

// Components.
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { ShopComponent } from './shops/shop/shop.component';
import { ShopListComponent } from './shops/shop-list/shop-list.component';
import { HttpClientModule } from '@angular/common/http';
import { PublicationListComponent } from './publications/publication-list/publication-list.component';
import { PublicationComponent } from './publications/publication/publication.component';
import { PromotionComponent } from './publications/promotion/promotion.component';
import { PromotionFormComponent } from './publications/creation/promotion-form/promotion-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShopOwnerAccessComponent } from './shops/shop-owner-access/shop-owner-access.component';
import { LogComponent } from './shops/log/log.component';
import { PromotionModifierComponent } from './publications/edit/promotion-modifier/promotion-modifier.component';
import { PublicationFormComponent } from './publications/creation/publication-form/publication-form.component';
import { ParkingComponent } from './parkings/parking/parking.component';
import { ParkingListComponent } from './parkings/parking-list/parking-list.component';
import { PublicationEditComponent } from './publications/edit/publication-edit/publication-edit.component';
import { ParkingOwnerAccessComponent } from './parkings/parking-owner-access/parking-owner-access.component';
import { LogParkingComponent } from './parkings/log-parking/log-parking.component';
import { CityEventComponent } from './events/city-event/city-event.component';
import { CityEventListComponent } from './events/city-event-list/city-event-list.component';
import { EventCreationComponent } from './events/managment/event-creation/event-creation.component';
import { EventManagmentComponent } from './events/managment/event-managment/event-managment.component';
import { LeaftletMapComponent } from './leaftlet-map/leaftlet-map.component';
import { PluginManagmentComponent } from './plugins/plugin-managment/plugin-managment.component';
import { PublicationService } from '../services/publication.service';
import { EventPublicationFormComponent } from './publications/creation/event-publication-form/event-publication-form.component';
import { ShopPublicationFormComponent } from './publications/creation/shop-publication-form/shop-publication-form.component';
import { ParkingInnovationTicketComponent } from './parking-innovation/parking-innovation-ticket/parking-innovation-ticket.component';
import { CookieService } from 'ngx-cookie-service';
import { EntranceTerminalComponent } from './parking-innovation/closed-parking/entrance-terminal/entrance-terminal.component';
import { ClosedParkingSimulationComponent } from './parking-innovation/closed-parking/closed-parking-simulation/closed-parking-simulation.component';
import { PaymentTerminalComponent } from './parking-innovation/closed-parking/payment-terminal/payment-terminal.component';
import { ExitTerminalComponent } from './parking-innovation/closed-parking/exit-terminal/exit-terminal.component';
import { ParkingLotScanComponent } from './parking-innovation/closed-parking/parking-lot-scan/parking-lot-scan.component';
import { OutsideParkingSimulationComponent } from './parking-innovation/outside-parking/outside-parking-simulation/outside-parking-simulation.component';
import { OutsideParkingTerminalComponent } from './parking-innovation/outside-parking/outside-parking-terminal/outside-parking-terminal.component';
import { MatSelectModule } from '@angular/material/select';
import { OutsideParkingAdditionalPaymentComponent } from './parking-innovation/outside-parking/outside-parking-additional-payment/outside-parking-additional-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ShopComponent,
    ShopListComponent,
    ParkingComponent,
    ParkingListComponent,
    ParkingOwnerAccessComponent,
    LogParkingComponent,
    PublicationListComponent,
    PublicationComponent,
    PromotionComponent,
    PromotionFormComponent,
    ShopOwnerAccessComponent,
    LogComponent,
    PromotionModifierComponent,
    PublicationFormComponent,
    PublicationEditComponent,
    CityEventComponent,
    CityEventListComponent,
    EventCreationComponent,
    EventManagmentComponent,
    LeaftletMapComponent,
    PluginManagmentComponent,
    EventPublicationFormComponent,
    ShopPublicationFormComponent,
    ParkingInnovationTicketComponent,
    EntranceTerminalComponent,
    ClosedParkingSimulationComponent,
    PaymentTerminalComponent,
    ParkingLotScanComponent,
    ExitTerminalComponent,
    OutsideParkingSimulationComponent,
    OutsideParkingTerminalComponent,
    OutsideParkingAdditionalPaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    QRCodeModule,
    NgxBarcodeModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatCardModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatSelectModule
  ],
  exports: [RouterModule],
  providers: [PublicationService, CookieService],
  bootstrap: [AppComponent]
})
// @ts-ignore
export class AppModule {}
