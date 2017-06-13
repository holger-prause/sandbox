import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { HighlightDirective } from './federal/directive/highllight.directive';
import { FederalStateListComponent } from './federal/federal-state-list/federal-state-list.component';
import { FederalStateDetailComponent } from './federal/federa-state-detail/federal-state-detail.component';
import { PersonListComponent } from './federal/person-list/person-list.component';
import { FederalStateService } from './federal/service/federal-state.service';

@NgModule({
  declarations: [
    AppComponent,
    FederalStateListComponent,
    FederalStateDetailComponent,
    PersonListComponent,
    HighlightDirective
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        redirectTo: '/list',
        pathMatch: 'full'
      },
      {
        path: 'list',
        component: FederalStateListComponent
      },
      {
        path: 'Bundesland/:urlIdentifier',
        component: FederalStateDetailComponent
      }
    ])
  ],
  providers: [FederalStateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
