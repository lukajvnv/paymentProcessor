import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TestService } from './services/test.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, InjectionToken } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewMagazineComponent } from './view-magazine/view-magazine.component';
import { PayMagazineFormComponent } from './pay-magazine-form/pay-magazine-form.component';
import { SuccesPageComponent } from './succes-page/succes-page.component';
import { FailedPageComponent } from './failed-page/failed-page.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { RouterModule, ActivatedRouteSnapshot } from '@angular/router';
import { PayService } from './service/pay.service';
import { FormsModule } from '@angular/forms';
import { NewMagazineComponent } from './new-magazine/new-magazine.component';
import { ViewAllMagazinesComponent } from './view-all-magazines/view-all-magazines.component';
import { ViewOneMagazineComponent } from './view-one-magazine/view-one-magazine.component';
import { UserTxComponent } from './user-tx/user-tx.component';
import { CartComponent } from './cart/cart.component';
import { ViewOneMagazineEditionComponent } from './view-one-magazine-edition/view-one-magazine-edition.component';

const externalUrlProvider = new InjectionToken('externalUrlRedirectResolver');


const routes = [
  {
    path: '', redirectTo: '/view-magazine', pathMatch: 'full'
  },
  {
    path: 'view-magazine', component: ViewMagazineComponent
  },
  {
    path: 'view-all-magazines', component: ViewAllMagazinesComponent
  },
  {
    path: 'magazine/:id', component: ViewOneMagazineComponent
  },
  {
    path: 'magazine-edition/:id', component: ViewOneMagazineEditionComponent
  },
  {
    path: 'cart', component: CartComponent

  },
  {
    path: 'user-tx', component: UserTxComponent

  },
  {
    path: 'buy-magazine', component: PayMagazineFormComponent
  },
  {
    path: 'new-magazine', component: NewMagazineComponent
  },
  {
    path: 'success', component: SuccesPageComponent
  },
  {
    path: 'error', component: ErrorPageComponent
  },
  {
    path: 'failed', component: FailedPageComponent
  },
  {
    path: 'externalRedirect',
    resolve: {
        url: externalUrlProvider,
    },
    // We need a component here because we cannot define the route otherwise
    component: PayMagazineFormComponent,
  },
  {
    path: '**', component: ErrorPageComponent
  }
  
]

@NgModule({
  declarations: [
    AppComponent,
    ViewMagazineComponent,
    PayMagazineFormComponent,
    SuccesPageComponent,
    FailedPageComponent,
    ErrorPageComponent,
    NewMagazineComponent,
    ViewAllMagazinesComponent,
    ViewOneMagazineComponent,
    UserTxComponent,
    CartComponent,
    ViewOneMagazineEditionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {enableTracing: true}) // <-- debugging purposes only

  ],
  providers: [PayService,
    {
      provide: externalUrlProvider,
      useValue: (route: ActivatedRouteSnapshot) => {
          const externalUrl = route.paramMap.get('externalUrl');
          window.open(externalUrl, '_self');
      }
    }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
