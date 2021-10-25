import { Component, OnInit } from '@angular/core';
import { MenuItem } from '../../models/menu.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  public menuItems: MenuItem[];

  constructor() {
    this.menuItems = [
      {
        title: 'Visiteur',
        icon: 'person',
        subItems: [
          {
            title: 'Carte',
            link: '/client/map'
          },
          {
            title: 'Magasins',
            link: '/client/shops'
          },
          {
            title: 'Publications',
            link: '/client/publications'
          },
          {
            title: 'Parkings',
            link: '/client/parkings'
          },
          {
            title: 'Evénements',
            link: '/client/cityEvents'
          }
        ]
      },
      {
        title: 'Maire',
        icon: 'location_city',
        subItems: [
          {
            title: 'Gestion des plugins',
            link: '/plugins'
          }
        ]
      },
      {
        title: 'Marchand',
        icon: 'store',
        subItems: [
          {
            title: 'Mon magasin',
            link: '/shop'
          },
          {
            title: 'Partager une promotion',
            link: '/shop/promotion'
          }
        ]
      },
      {
        title: 'Parking',
        icon: 'local_parking',
        subItems: [
          {
            title: 'Mon parking',
            link: '/log-parking'
          }
        ]
      },
      {
        title: 'Organisateur',
        icon: 'event',
        subItems: [
          {
            title: 'Mon événement',
            link: '/cityEvent'
          },
          {
            title: 'Partager une publication',
            link: '/cityEvent/publication'
          }
        ]
      },
      {
        title: 'Innovation',
        icon: 'emoji_objects',
        subItems: [
          {
            title: 'Ticket',
            link: '/innovation/ticket'
          },
          {
            title: 'Simulation de parking fermé',
            link: 'innovation/closed/simulation'
          },
          {
            title: 'Simulation de parking ouvert',
            link: 'innovation/outside/simulation'
          },
          {
            title: 'Simulation scan place',
            link: 'innovation/closed/parkingLotScan/simulation'
          }
        ]
      }
    ];
  }

  ngOnInit(): void {
  }
}
