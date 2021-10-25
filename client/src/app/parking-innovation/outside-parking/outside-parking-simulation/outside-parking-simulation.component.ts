import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-outside-parking-simulation',
  templateUrl: './outside-parking-simulation.component.html',
  styleUrls: ['./outside-parking-simulation.component.scss']
})
export class OutsideParkingSimulationComponent implements OnInit {
  onlineTerminalLink='http://localhost:4200/innovation/outside/online-terminal';

  constructor() { }

  ngOnInit(): void {
  }

  goToLink(url: string) {
    window.open(url, '_blank');
  }
}
