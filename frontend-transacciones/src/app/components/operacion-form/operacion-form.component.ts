import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransaccionResponse } from '../../models/Transaccion.model';
import { OperacionService } from '../../services/operacion.service';
import { OperacionRequest } from '../../models/Operacion.model';
import { generarSha256 } from '../../utils/sha.util';

@Component({
  selector: 'app-operacion-form',
  standalone: false,
  templateUrl: './operacion-form.component.html',
  styleUrl: './operacion-form.component.css'
})
export class OperacionFormComponent {

  form: FormGroup;
  respuesta?: TransaccionResponse;
  error?: string;

  constructor(private fb: FormBuilder, private operacionService: OperacionService) {
    this.form = this.fb.group({
      operacion: ['', Validators.required],
      importe: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{2})$/)]],
      cliente: ['', Validators.required]
    });
  }

  async enviar(): Promise<void> {
    this.respuesta = undefined;
    this.error = undefined;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { operacion, importe, cliente } = this.form.value;

    const textoPlano = `${operacion}|${importe}|${cliente}`;
    const firma = await generarSha256(textoPlano);

    const request = {
      operacion,
      importe,
      cliente,
      firma
    };

    this.operacionService.procesarOperacion(request).subscribe({
      next: (res) => {
        this.respuesta = res;
        this.form.reset();
      },
      error: (err) => {
        this.error =
          err?.error?.mensaje ||
          'Error al procesar la operación';
      }
    });
  }

}
