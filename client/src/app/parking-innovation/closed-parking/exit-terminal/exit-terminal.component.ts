import { Component, Input, OnInit } from '@angular/core';
import { Ticket } from '../../../../models/ticket.model';
import { TicketService } from '../../../../services/ticket.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-exit-terminal',
  templateUrl: './exit-terminal.component.html',
  styleUrls: ['./exit-terminal.component.scss']
})
export class ExitTerminalComponent implements OnInit {

  idSelected = '';
  ticket: Ticket | undefined;
  open = false;

  constructor(
    private ticketService: TicketService,
    private cookieService: CookieService
  ) { }

  ngOnInit(): void {
    this.idSelected = this.cookieService.get('TicketId');
    if (this.idSelected === 'null'){
      this.idSelected = '';
    }
  }

  checkTicket(): void {
    this.ticketService
      .getTicket(this.idSelected)
      .subscribe((t) => {
        this.ticket = t;
        this.checkPaiement(this.ticket);
      });

  }

  checkPaiement(t: Ticket | undefined): void {
    if (t !== undefined && t.state === 'PAID') {
      this.open = true;
    }
  }
}
