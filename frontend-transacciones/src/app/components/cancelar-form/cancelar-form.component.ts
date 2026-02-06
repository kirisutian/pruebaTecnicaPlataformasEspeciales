import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransaccionService } from '../../services/transaccion.service';
import { CancelarTransaccionRequest } from '../../models/Transaccion.model';

@Component({
  selector: 'app-cancelar-form',
  standalone: false,
  templateUrl: './cancelar-form.component.html',
  styleUrl: './cancelar-form.component.css'
})
export class CancelarFormComponent {

  form: FormGroup;
  mensajeExito: string | null = null;
  mensajeError: string | null = null;
  cargando = false;

  constructor(
    private fb: FormBuilder,
    private transaccionService: TransaccionService
  ) {
    this.form = this.fb.group({
      id: [null, [Validators.required]],
      referencia: ['', [Validators.required]]
    });
  }

  cancelar(): void {
    this.mensajeExito = null;
    this.mensajeError = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: CancelarTransaccionRequest = {
      id: this.form.value.id,
      referencia: this.form.value.referencia,
      estatus: 'cancelar'
    };

    this.cargando = true;

    this.transaccionService.cancelar(request).subscribe({
      next: () => {
        this.mensajeExito = 'Transacción cancelada correctamente';
        this.form.reset();
        this.cargando = false;
      },
      error: (err) => {
        this.mensajeError =
          err?.error?.mensaje ?? 'No fue posible cancelar la transacción';
        this.cargando = false;
      }
    });
  }

}
