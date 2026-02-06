import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CancelarTransaccionRequest } from '../models/Transaccion.model';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {

  private readonly apiUrl = environment.apiTransacciones;

  constructor(private http: HttpClient) { }

  cancelar(request: CancelarTransaccionRequest): Observable<void> {
    const params = new HttpParams()
      .set('id', request.id)
      .set('referencia', request.referencia)
      .set('estatus', request.estatus);

    return this.http.patch<void>(
      `${this.apiUrl}/cancelar`,
      null,
      { params }
    );
  }
}
