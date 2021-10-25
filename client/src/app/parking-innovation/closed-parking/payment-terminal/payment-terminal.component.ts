import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../../../services/ticket.service';
import { Ticket } from '../../../../models/ticket.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-payment-terminal',
  templateUrl: './payment-terminal.component.html',
  styleUrls: ['./payment-terminal.component.scss']
})
export class PaymentTerminalComponent implements OnInit {
  idSelected = '';
  ticket: Ticket | undefined;

  constructor(
    private ticketService: TicketService,
    private cookieService: CookieService
  ) { }

  ngOnInit(): void {
    this.idSelected = this.cookieService.get('TicketId');
    if (this.idSelected === 'null'){
      this.idSelected = '';
    } else {
      this.findTicket();
    }
  }

  findTicket(): void {
    this.ticketService
      .getTicket(this.idSelected)
      .subscribe((t)=>{
        this.ticket = t;
      });
  }

  pay(){
    if (this.ticket?.id) {
      this.ticketService
        .pay((this.ticket.id).toString())
        .subscribe((t)=>{
          this.ticket = t;
        });
    }
  }

  scanAvailable(): boolean {
    return this.idSelected===this.ticket?.id.toString();
  }
}
