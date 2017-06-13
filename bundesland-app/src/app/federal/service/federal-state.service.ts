import { Injectable } from '@angular/core';
import { FederalState } from '../federal-state';
import { FederalStateResponse } from '../federal-state-response';
import { PersonIndex } from '../person-index';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class FederalStateService {
  federalStates: FederalState[] = [
    new FederalState()
  ];

  constructor(private http: Http) { }

  getFederalStates(): Promise<FederalState[]> {
    return this.fetch()
      .then(reponse => (reponse.json() as FederalStateResponse).stateList)
      .catch(this.handleError);
  }

  getFederalState(urlIdentifier: String): Promise<FederalState> {
    return this.getFederalStates().then(federalStates => federalStates.find(
      federalState => this.getFederalStateUrlIdentifier(federalState) === urlIdentifier)
    );
  }

  getFederalStateUrlIdentifier(federalState: FederalState): string {
    const index = federalState.url.lastIndexOf('/');
    return federalState.url.substring(index + 1, federalState.url.length);
  }

  getPersonIndex(): Promise<PersonIndex> {
    return this.fetch()
      .then(reponse => (reponse.json() as FederalStateResponse).personIndexCounts)
  }

  private fetch(): Promise<Response> {
    return this.http.get('assets/bundesland.json')
      .toPromise()
  }

  private handleError(error: any): Promise<any> {
    console.error('Could not load json', error);
    return Promise.reject(error.message || error);
  }
}
