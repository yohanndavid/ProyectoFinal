# Sistema de Control y Gestión Clínica Veterinaria

**Caso de negocio:** Sistema de Control y Gestión Clínica Veterinaria.

## Integrantes

- Yohann David Zambrano Calderon — 1152731
- Bram Hernando Coronado Roa — 1152718

## Manual de usuario

Esta aplicación permite registrar propietarios, personal y mascotas, organizar las citas, llevar el historial clínico y realizar los cobros de cada atención. A continuación se explica el recorrido de uso, desde el inicio de la jornada hasta el comprobante.

**Importante:** los datos se guardan únicamente en memoria mientras el programa está abierto. Al salir del programa completo se pierde la información de la jornada. Cerrar una ventana secundaria o volver al menú no elimina los registros.

### Antes de comenzar

Para ejecutar el proyecto se necesita Java 17 o una versión posterior. Abra la carpeta del proyecto en NetBeans y ejecute la clase **Main** del paquete **Aplicacion**. Aparecerá el menú principal.

## 1. Iniciar la jornada

1. Escriba el nombre de la clínica.
2. Revise la tarifa base por consulta y el costo adicional de sedación. Los valores iniciales son ejemplos y pueden cambiarse antes de iniciar.
3. Pulse **Iniciar jornada**. Se habilitarán los módulos de trabajo.

Escriba los valores sin separadores de miles: por ejemplo, **100000**, no **100.000**. Las tarifas quedan fijas durante esa ejecución.

![Menú principal antes de iniciar la jornada](https://github.com/user-attachments/assets/3a382c3b-323b-4a2c-8e0c-6e09db81d49a)

## 2. Registrar propietarios

Entre en **Propietarios** y complete documento, nombre, teléfono, correo y dirección. Pulse **Registrar** y compruebe que la persona aparezca en el listado.

Para consultar a un propietario, seleccione su fila o utilice **Buscar por documento** con el documento ingresado. Si necesita registrar a otra persona, pulse **Nuevo**. El propietario debe existir antes de registrar sus mascotas.

![Registro y consulta de propietarios](https://github.com/user-attachments/assets/af8fc02b-efaf-457d-9fa5-9746f7a5b964)

## 3. Registrar el personal

Abra **Personal** y seleccione el tipo de colaborador:

- **Recepcionista:** complete documento, nombre y teléfono.
- **Veterinario:** complete también la especialidad y la tarjeta profesional.

Pulse **Registrar**. Use **Nuevo** para ingresar al siguiente colaborador. Antes de continuar, registre al menos una recepcionista para las citas y cobros, y un veterinario para las consultas.

![Personal registrado en la clínica](https://github.com/user-attachments/assets/fae55093-232c-45a1-8267-4f1808af1410)

## 4. Registrar mascotas

Abra **Mascotas**, seleccione al propietario y el tipo de mascota. El identificador se genera automáticamente. Complete nombre, especie, edad en años y peso en kilogramos. La edad admite cero; el peso debe ser mayor que cero.

**Mascota convencional:** puede registrar la raza y el esquema de vacunas. Si no conoce esos datos, puede dejarlos en blanco.

![Formulario para registrar una mascota convencional](https://github.com/user-attachments/assets/4403d908-7ffb-46cd-903b-7116f20dbd89)

**Mascota exótica:** debe registrar el permiso ICA/CITES e indicar si requiere sedación. Después de completar los datos, pulse **Registrar**.

![Consulta de una mascota exótica registrada](https://github.com/user-attachments/assets/103c45ed-ccb5-4f05-a6cf-bc300d71156f)

Para consultar una mascota, búsquela en el listado y seleccione la fila. Puede actualizar su peso con **Actualizar peso** y, si es convencional, el esquema con **Actualizar vacunas**.

## 5. Agendar una cita

1. Abra **Agenda de citas** y seleccione mascota y recepcionista.
2. Revise la fecha y hora con el formato **dd/MM/aaaa HH:mm**. Puede usar **Fecha actual** o escribir la fecha de la cita.
3. Escriba el motivo y pulse **Agendar**.

La cita quedará como **PROGRAMADA**. Para cancelarla, selecciónela y pulse **Cancelar cita**; confirme la operación. Las citas canceladas permanecen en el listado. No se permite agendar a la misma mascota dos veces en la misma fecha y hora, salvo que la cita anterior esté cancelada.

![Cita programada y datos del paciente](https://github.com/user-attachments/assets/fa60e057-0c8f-4125-9b8a-6c631ef24469)

## 6. Registrar la atención médica

Entre en **Atención médica** y seleccione la mascota y el veterinario. Si hay una cita programada, selecciónela; si no, deje **Sin cita / atención directa**. Revise la fecha y pulse **Iniciar atención**.

Registre el diagnóstico, los tratamientos y las notas médicas. Para completar o cambiar esos datos en una consulta abierta, selecciónela y pulse **Guardar datos clínicos**. Se puede iniciar con el diagnóstico pendiente, pero debe completarse antes de emitir el cobro. Una cita vinculada pasa a **ATENDIDA**.

![Consulta abierta con datos clínicos registrados](https://github.com/user-attachments/assets/059995be-b5d5-4808-8855-e1592e3200ca)

## 7. Registrar vacunas y medicamentos

Abra **Vacunas / medicamentos** y seleccione una consulta abierta.

1. Elija el tipo de insumo y complete código, nombre, cantidad y valor unitario.
2. Para una **vacuna**, ingrese el lote. La próxima dosis es opcional y usa el formato **dd/MM/aaaa**.
3. Para un **medicamento**, complete la dosis y la vía de administración.
4. Pulse **Aplicar insumo** y revise que aparezca en el listado. Use **Nuevo insumo** para agregar otro.

La cantidad debe ser un entero mayor que cero y cada código debe ser diferente dentro de la consulta. El sistema calcula el valor según cantidad y precio unitario. Los insumos se incluyen en el historial y en la cuenta. No se pueden agregar después de emitir el cobro.

## 8. Consultar el historial clínico

Abra **Historial clínico**, seleccione la mascota y pulse **Consultar historial**. Allí podrá revisar sus datos y las consultas registradas, con diagnóstico, tratamientos, notas e insumos aplicados. Use la barra de desplazamiento para leer el contenido completo.

Si no aparece una mascota recién registrada, pulse **Actualizar mascotas**.

![Selección de mascota para consultar el historial](https://github.com/user-attachments/assets/09eebc9b-a4c7-4cd8-91be-05700d08772d)

## 9. Realizar el cobro y ver el comprobante

### Revisar el valor

En **Caja y comprobantes**, seleccione la consulta y la recepcionista. Pulse **Calcular / vista previa** para revisar subtotal, descuento y total. Esta opción no emite el cobro ni bloquea la consulta.

La cuenta incluye la tarifa base y los insumos aplicados. En mascotas exóticas se suma un recargo del **25% sobre la tarifa base** y el costo de sedación cuando corresponda. Si el propietario tiene **más de dos mascotas registradas**, se aplica un descuento del **10% sobre el subtotal**.

![Vista previa del cobro antes de emitirlo](https://github.com/user-attachments/assets/427a1d6e-d77e-48af-a451-873ea6a8f1d0)

### Emitir el cobro

Revise los datos y pulse **Emitir cobro**. Confirme solo si la atención está completa: desde ese momento no podrá modificar los datos clínicos ni agregar insumos a esa consulta. El cobro queda pendiente de pago.

![Confirmación antes de emitir el cobro](https://github.com/user-attachments/assets/cb9893e2-eec2-492f-a18e-bdbb250299a3)

### Registrar el pago

Cuando reciba el dinero, seleccione el método de pago y pulse **Registrar pago**. Confirme la operación. El estado cambiará a **PAGADO** y no permitirá registrar otro pago sobre el mismo cobro.

Para revisar el resumen, seleccione el cobro y pulse **Ver comprobante**. Se muestra dentro de la ventana; no se guarda como archivo TXT.

![Cobro pagado y comprobante en pantalla](https://github.com/user-attachments/assets/da569134-e8b2-42a5-92c6-5e7a18ef4701)

## Botones de uso frecuente

| Botón o campo | Uso |
| --- | --- |
| Nuevo / Nuevo insumo | Prepara el formulario para otro registro; no elimina lo guardado. |
| Buscar en el listado | Filtra las filas por nombre, documento o código. Borre el texto para volver a ver todas. |
| Ver seleccionado | Muestra los datos del registro elegido en la tabla. |
| Actualizar listado | Vuelve a cargar los registros disponibles. |
| Volver al menú | Cierra la ventana del módulo sin terminar la jornada. |
| Salir | Cierra el programa completo después de confirmar. Se pierden los datos de esa ejecución. |

## Si aparece un aviso

Lea el mensaje, pulse **Aceptar**, corrija el dato indicado y vuelva a intentar. Revise que no haya campos obligatorios vacíos, documentos o códigos repetidos, fechas inválidas o valores con separadores de miles. Los campos o botones deshabilitados indican que esa opción no está disponible para el registro o estado actual.
