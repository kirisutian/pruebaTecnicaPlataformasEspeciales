import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { OperacionFormComponent } from './components/operacion-form/operacion-form.component';
import { CancelarFormComponent } from './components/cancelar-form/cancelar-form.component';

@NgModule({
  declarations: [
    AppComponent,
    OperacionFormComponent,
    CancelarFormComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
