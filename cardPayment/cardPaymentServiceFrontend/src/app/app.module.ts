import { BrowserModule } from '@angular/platform-browser';
import { NgModule, InjectionToken } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PayComponent } from './pay/pay.component';
import { RouterModule, ActivatedRouteSnapshot } from '@angular/router';
import { PaymentService } from './service/payment.service';
import { HttpClientModule } from '@angular/common/http';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TestComponent } from './test/test.component';
import { TestDisplayComponent } from './test-display/test-display.component';

const externalUrlProvider = new InjectionToken('externalUrlRedirectResolver');

const routes = [
  // {
  //   path: '', redirectTo: '/pay', pathMatch: 'full'
  // },
  {
    path: '', component: TestComponent,
  },
  {
    path: 'pay', component: PayComponent
  },
  {
    path: 'pay/:paymentId', component: PayComponent
  },
  {
    path: 'externalRedirect',
    resolve: {
        url: externalUrlProvider,
    },
    // We need a component here because we cannot define the route otherwise
    component: PayComponent,
  },
];
@NgModule({
  declarations: [
    AppComponent,
    PayComponent,
    TestComponent,
    TestDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-top-right',
    }),
    RouterModule.forRoot(routes, {enableTracing: true}) // <-- debugging purposes only
  ],
  providers: [
    PaymentService, ToastrService,
    {
      provide: externalUrlProvider,
      useValue: (route: ActivatedRouteSnapshot) => {
          const externalUrl = route.paramMap.get('externalUrl');
          window.open(externalUrl, '_self');
      },
  },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
