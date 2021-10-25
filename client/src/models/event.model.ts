import {Publication} from './publication.model';
import {Place} from './place.model';

export interface CityEvent extends Publication{
  startDate: Date;
  endDate: Date;
  description?: string;
  location?: Place;
}
