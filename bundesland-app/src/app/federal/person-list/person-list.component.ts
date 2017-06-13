import { PersonIndex } from '../person-index';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FederalStateService } from '../service/federal-state.service';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})

export class PersonListComponent implements OnInit {
  filteredPersons = new Array<string>();
  constructor(private federalStateService: FederalStateService) {
    this.federalStateService = federalStateService;
  }

  ngOnInit(): void {
    // you could just iterate over all properties of that object - but type script does not allow this without warnings
    const props = 'abcdefghijklmnopqrstuvwxyz'.split('');
    this.federalStateService.getPersonIndex().then(personIndex => {
      for (const prop of props) {
        if (personIndex[prop] > 0) {
          this.filteredPersons.push(prop);
        }
      }
    })
  }
}
