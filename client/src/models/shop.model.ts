import { Place } from './place.model';
import { Publication } from './publication.model';

export interface Shop extends Place {
  id: number;
  name: string;
  type: string;
  timeTable: DayTimeTable[];
}

export interface DayTimeTable {
  startingHour: string;
  endingHour: string;
}

export interface Promotion extends Publication {
  isPercentage: boolean;
  value: number;
  condition: string;
}
