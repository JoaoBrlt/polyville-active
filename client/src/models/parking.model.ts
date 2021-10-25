export interface Parking {
  id: number;
  capacity: number;
  availablePlaces: number;
  name: string;
  address: string;
  latitude: number;
  longitude: number;
  owner: string;
  price: Map<number, number>;
}
