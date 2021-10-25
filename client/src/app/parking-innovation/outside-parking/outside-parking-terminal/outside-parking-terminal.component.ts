import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../../../services/ticket.service';
import { Payment, Ticket } from '../../../../models/ticket.model';
import { CookieService } from 'ngx-cookie-service';
import { MatOptionSelectionChange } from '@angular/material/core';



@Component({
  selector: 'app-outside-parking-terminal',
  templateUrl: './outside-parking-terminal.component.html',
  styleUrls: ['./outside-parking-terminal.component.scss']
})
export class OutsideParkingTerminalComponent implements OnInit {
  hours!: number[];
  minutes!: number[];

  carNumber= '';
  time= {hour: 1, minute: 0};

  ticket!: Ticket;

  message='';

  constructor(
    private ticketService: TicketService,
    private cookieService: CookieService
  ) {
    this.constructLists();
  }

  ngOnInit(): void {
  }

  create() {
    const timeLimit = new Date();
    timeLimit.setTime(timeLimit.getTime() + (this.time.hour*60*60*1000));
    timeLimit.setTime(timeLimit.getTime() + (this.time.minute*60*1000));

    this.ticketService.registerOutsideTicket(this.carNumber, {validityLimit: timeLimit})
      .subscribe((ticket)=>{
        if (!ticket){
          this.message = 'Erreur lors de la création de ticket !';
          return;
        }
        this.ticket = ticket;
        this.cookieService.set('TicketId', String(ticket.id), { path: '/' });
        this.message = 'Votre ticket a été créée et enregistré !';
      });
  }

  constructLists(): void {
    this.hours = Array(24).fill(1).map((x,i)=>i);
    this.minutes = Array(4).fill(1).map((x,i)=>i*15);
  }
}
