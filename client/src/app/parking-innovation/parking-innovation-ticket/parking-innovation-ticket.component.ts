import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ticket } from '../../../models/ticket.model';
import { TicketService } from '../../../services/ticket.service';
import { CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-parking-innovation-ticket',
  templateUrl: './parking-innovation-ticket.component.html',
  styleUrls: ['./parking-innovation-ticket.component.scss']
})
export class ParkingInnovationTicketComponent implements OnInit {
  @Input()
  ticket!: Ticket;

  ticketId!: string | null;

  warning!: string;
  addPay = false;

  constructor(private route: ActivatedRoute,
              private ticketService: TicketService,
              private cookieService: CookieService
  ) { }

  ngOnInit(): void {
    this.ticketId = this.route.snapshot.paramMap.get('id');
    console.log('path id :', this.ticketId);
    const cookieContent = this.cookieService.get('TicketId');
    console.log('cookie id :', cookieContent);

    if (this.ticketId === null && cookieContent){
      // if not in the path but saved
      console.log('not in the path but saved');
      this.ticketId = cookieContent;
      this.getTicket();
    } else
    if (this.ticketId !== null && (!cookieContent || this.ticketId === cookieContent)) {
      // if in the path and not saved (or same)
      console.log('in the path and not saved (or same)');
      this.setTicketInCookie();
      this.getTicket();
    } else
    if (this.ticketId !== null && cookieContent) {
      // if in the path and already saved
      console.log('in the path and saved');
      this.checkForNotPaidTicket(this.ticketId, cookieContent);
    }
  }

  translateDate(input: Date): string {
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour:'2-digit', minute:'2-digit' };
    const date = new Date(input);
    return date.toLocaleDateString('fr-FR',options);
  }

  private getTicket(): void {
    if ( this.ticketId) {
      this.ticketService.getTicket(this.ticketId).subscribe((ticket) => {
        if (!ticket) {
          return;
        }
        this.ticket = ticket;

        //if not validated
        if (ticket.state==='FREE'){
          this.ticketService.validateTicket(ticket.id.toString()).subscribe((validatedTicket) => {
            if (!validatedTicket) {
              return;
            }
            this.ticket = validatedTicket;
          });
        }
      });
    }
  }

  private checkForNotPaidTicket(newId: string, beforeId: string) {
    this.ticketService.getTicket(beforeId).subscribe((ticket)=>{
      if(!ticket){
        this.ticketId = newId;
        this.setTicketInCookie();
        this.getTicket();
        return;
      }
      if(ticket?.state !== 'DONE' && ticket?.state !== 'PAID'){
        // si l'ancien n'a pas encore été payé
        console.log('not paid !');
        this.ticket = ticket;
        this.warning='Vous ne pouvez pas prendre de nouveaux tickets tant que votre ancien ticket n\'a pas été payé !';
      } else {
        // si l'ancien a bien été payé
        this.ticketId = newId;
        this.setTicketInCookie();
        this.getTicket();
      }
    });
  }


  private setTicketInCookie() {
    if (this.ticketId != null) {
      this.cookieService.set('TicketId', this.ticketId, { path: '/' });
    } else {
      this.cookieService.delete('TicketId');
    }
  }
}
