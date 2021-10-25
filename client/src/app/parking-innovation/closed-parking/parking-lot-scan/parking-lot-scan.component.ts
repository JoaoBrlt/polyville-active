import { Component, OnInit } from '@angular/core';
import { Ticket } from '../../../../models/ticket.model';
import { TicketService } from '../../../../services/ticket.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-parking-lot-scan',
  templateUrl: './parking-lot-scan.component.html',
  styleUrls: ['./parking-lot-scan.component.scss']
})
export class ParkingLotScanComponent implements OnInit {
  parkingSpaceSelected = '';
  ticket!: Ticket | undefined;

  constructor(private ticketService: TicketService,
              private cookieService: CookieService) { }

  ngOnInit(): void {
    this.parkingSpaceSelected = this.cookieService.get('TicketId');
    if (this.parkingSpaceSelected === 'null'){
      this.parkingSpaceSelected = '';
    } else {
      this.findTicket();
    }
  }

  findTicket(): void {
    this.ticketService
      .getTicket(this.parkingSpaceSelected)
      .subscribe((t)=>{
        this.ticket = t;
      });
  }

  saveParkingSpace(parkingSpace: string): void {
    if(this.ticket){
      this.ticketService
        .saveParkingSpace(this.ticket.id.toString(),parkingSpace)
        .subscribe((ticket: Ticket) => {
          this.ticket = ticket;
        });
    }
  }

}
