import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';
import { HttpClient } from '@angular/common/http';
import { OperacionRequest } from '../models/Operacion.model';
import { Observable } from 'rxjs';
import { TransaccionResponse } from '../models/Transaccion.model';

@Injectable({
  providedIn: 'root'
})
export class OperacionService {

  private readonly apiUrl = environment.apiOperaciones;

  constructor(private http: HttpClient) { }

  procesarOperacion(request: OperacionRequest): Observable<TransaccionResponse> {
    return this.http.post<TransaccionResponse>(this.apiUrl, request);
  }
}
