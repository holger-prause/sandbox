import { FederalState } from './federal-state';
import { PersonIndex } from './person-index';

export class FederalStateResponse {
  stateList: FederalState[];
  personIndexCounts: PersonIndex;
}
