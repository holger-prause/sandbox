import { FederalState } from '../federal-state';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { FederalStateService } from '../service/federal-state.service';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-federal-state-detail',
  templateUrl: './federal-state-detail.html',
  styleUrls: ['./federal-state-detail.css']
})

export class FederalStateDetailComponent implements OnInit {
  @Input() federalState: FederalState;

  constructor(
    private federalStateService: FederalStateService,
    private route: ActivatedRoute,
    private location: Location
  ) { }
  ngOnInit(): void {    this.route.params
      .switchMap((params: Params) => this.federalStateService.getFederalState(params['urlIdentifier']))
      .subscribe(federalState => this.federalState = federalState);
    return;
  }

  navigateBack(): void {
    this.location.back();
  }
}
