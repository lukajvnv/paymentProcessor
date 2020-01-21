import { PayServiceService } from './services/pay-service.service';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PayComponent } from './pay/pay.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';



const routes: Routes = [
 /* {
    path: '', redirectTo: '/pay/:id', pathMatch: 'full'   //id je sellerId po kojem ce traziti koje sve on nacine placanja nudi
  }, */{
    path:'pay/:id', component: PayComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    PayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes, {enableTracing: true}) // <-- debugging purposes only
  ],
  providers: [PayServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
