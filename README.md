
Aplicación Android en Java que convierte entre Dólar y Euro .
El dólar se usa como base (1 USD = 1) y el usuario puede modificar el valor del euro.

Lógica de Conversión
La conversión se realiza en dos pasos:
*Convertir el valor a USD: montoUSD = monto * valorOrigen
*Convertir de USD a moneda destino: resultado = montoUSD / valorDestino

Funcionalidades:
*Conversión de moneda : Dolar a Euro y Euro a Dolar
*Edición del tipo de cambio: El usuario puede modificar el valor del euro respecto al dólar.
*Validaciones: Control de valores numéricos válidos, mensajes de error ante entradas incorrectas, manejo de estado con ViewModel y LiveData.

Arquitectura
La aplicación implementa el patrón MVVM (Model - View - ViewModel):

*Model: Clase Moneda (nombre, valor respecto a USD)
*ViewModel: MainActivityViewModel, la cual maneja la lógica de negocio y estado de la aplicación.
*View:MainActivity: Observa cambios mediante LiveData.

Restricciones del sistema
*El valor del Dólar es fijo y no puede modificarse.
*Solo el valor del Euro es editable por el usuario.
*El campo de modificación de valor se habilita únicamente cuando corresponde.
