import { PayServiceService } from './services/pay-service.service';
import { RouterModule, Routes, ActivatedRouteSnapshot } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, InjectionToken } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PayComponent } from './pay/pay.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NewClientComponent } from './new-client/new-client.component';
import { NewClientHtmlComponent } from './new-client-html/new-client-html.component';

const externalUrlProvider = new InjectionToken('externalUrlRedirectResolver');


const routes: Routes = [
 /* {
    path: '', redirectTo: '/pay/:id', pathMatch: 'full'   //id je sellerId po kojem ce traziti koje sve on nacine placanja nudi
  }, */{
    path:'pay/:sellerId/:id', component: PayComponent
  },
  {
    path:'pay/:id', component: PayComponent
  },
  // {
  //   path: 'new-client/:id', component: NewClientComponent
  // },
  {
    path: 'new-client/:id', component: NewClientHtmlComponent
  },
  {
    path: 'externalRedirect',
    resolve: {
        url: externalUrlProvider,
    },
    // We need a component here because we cannot define the route otherwise
    component: PayComponent,
  },
]

@NgModule({
  declarations: [
    AppComponent,
    PayComponent,
    NewClientComponent,
    NewClientHtmlComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes, {enableTracing: true}) // <-- debugging purposes only
  ],
  providers: [PayServiceService, 
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
