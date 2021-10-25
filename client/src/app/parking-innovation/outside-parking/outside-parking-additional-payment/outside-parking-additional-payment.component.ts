import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Ticket } from '../../../../models/ticket.model';
import { TicketService } from '../../../../services/ticket.service';

@Component({
  selector: 'app-outside-parking-additional-payment',
  templateUrl: './outside-parking-additional-payment.component.html',
  styleUrls: ['./outside-parking-additional-payment.component.scss']
})
export class OutsideParkingAdditionalPaymentComponent implements OnInit {
  @Input()
  ticket!: Ticket;

  @Output()
  newTicket= new EventEmitter<Ticket>();

  hours!: number[];
  minutes!: number[];

  time= {hour: 1, minute: 0};

  message= '';

  constructor(
    private ticketService: TicketService
  ) {
    this.constructLists();
  }

  ngOnInit(): void {
  }

  pay() {
    let timeLimit = new Date();
    timeLimit.setTime(timeLimit.getTime() + (this.time.hour*60*60*1000));
    timeLimit.setTime(timeLimit.getTime() + (this.time.minute*60*1000));

    const timeLimitFromPrevious = new Date(this.ticket.payment.validityLimit);
    timeLimit.setTime(timeLimit.getTime() + (this.time.hour*60*60*1000));
    timeLimit.setTime(timeLimit.getTime() + (this.time.minute*60*1000));

    if (timeLimit.getTime()<timeLimitFromPrevious.getTime()) {
      timeLimit = timeLimitFromPrevious;
    }

    this.ticketService.addtionnalPayment(this.ticket.id.toString(), {validityLimit: timeLimit})
      .subscribe((ticket)=>{
        if (!ticket){
          this.message = 'Erreur lors de l\'ajout de payment !';
          return;
        }
        this.newTicket.emit(ticket);
        this.message = 'Votre payment a bien été enregistré !';
      });
  }

  private constructLists(): void {
    this.hours = Array(24).fill(1).map((x,i)=>i);
    this.minutes = Array(4).fill(1).map((x,i)=>i*15);
  }
}
