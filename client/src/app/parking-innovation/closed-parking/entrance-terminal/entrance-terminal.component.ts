import { Component, Input, OnInit } from '@angular/core';
import { TicketService } from '../../../../services/ticket.service';
import { Ticket } from '../../../../models/ticket.model';

@Component({
  selector: 'app-entrance-terminal',
  templateUrl: './entrance-terminal.component.html',
  styleUrls: ['./entrance-terminal.component.scss']
})
export class EntranceTerminalComponent implements OnInit {
  ticket!: Ticket;

  constructor(private ticketService: TicketService) {
  }

  ngOnInit(): void {
  }

  generateTicket(): void {
    this.ticketService
      .generateTicket()
      .subscribe((ticket: Ticket) => {
        this.ticket = ticket;
      });
  }

  getAbsoluteLink(): string {
    return 'http://localhost:4200/innovation/ticket/' + this.ticket.id;
  }

  getRelativeLink(): string {
    return '/innovation/ticket/' + this.ticket.id;
  }

  goToLink(url: string) {
    window.open(url, '_blank');
  }
}
