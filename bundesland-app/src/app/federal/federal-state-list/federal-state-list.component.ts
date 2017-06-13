import { Component } from '@angular/core';
import { FederalState } from '../federal-state';
import { FederalStateService } from '../service/federal-state.service';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-federal-state-list',
  templateUrl: './federal-state-list.component.html',
  styleUrls: ['./federal-state-list.component.css']
})

export class FederalStateListComponent implements OnInit {
  selectedFederalState: FederalState;
  federalStates: FederalState[];

  constructor(private federalStateService: FederalStateService, private router: Router) {
    this.federalStateService = federalStateService;
  }

  ngOnInit(): void {
    this.federalStateService.getFederalStates().then(federalStates => this.federalStates = federalStates);
  }

  selectFederalState(selectedFederalState: FederalState): void {
    this.selectedFederalState = selectedFederalState;
    const urlId = this.federalStateService.getFederalStateUrlIdentifier(selectedFederalState);
    this.router.navigate(['Bundesland', urlId]);
  }
}
