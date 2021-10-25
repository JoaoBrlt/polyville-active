export interface Ticket {
  id: number;
  took: boolean;
  placeInfo: string;
  parkingId: number;
  date: Date;
  payment: Payment;
  state: string;
  carNumberPlate: string;
}

export interface Payment {
  value: number;
  date: Date;
  validityLimit: Date;
  id: number;
}
