export interface TransaccionResponse {
  id: number;
  estatus: string;
  referencia: string;
  operacion: string;
}

export interface CancelarTransaccionRequest {
  id: number;
  referencia: string;
  estatus: 'cancelar';
}