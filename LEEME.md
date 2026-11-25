# Clínica veterinaria MVC — versión modular

Abra esta carpeta en NetBeans con Archivo > Abrir proyecto. Requiere JDK 17 o posterior. Ejecute Aplicacion.Main.

## Organización

- Modelo: entidades, relaciones, historial y cálculos. No utiliza Swing ni muestra avisos.
- Vista: nueve JFrame Forms, cada uno con su archivo .java y .form, componentes privados y métodos públicos para leer o mostrar valores.
- Controlador: un controlador para cada ventana. Valida entradas, escucha botones y solicita operaciones al modelo.
- Aplicacion: Main inicia la ventana del menú y su controlador.

ControladorMenu crea una única Clinica y comparte ese objeto con los demás controladores. Cada módulo conserva su propia ventana. Volver o cerrar una ventana secundaria solo la oculta; al abrirla se actualizan las opciones y listados. Salir solicita confirmación y cierra todas las ventanas.

Los campos, botones, tablas y áreas de resultados están definidos en initComponents y en el .form correspondiente. No se agregan pantallas ocultas después del diseño. Los controladores se conectan por métodos como alRegistrar y getNombre; no acceden directamente a los componentes privados.

## Demostración sugerida

1. Inicie la jornada con tarifa base 100000 y sedación 30000; son valores de ejemplo configurables.
2. Registre un propietario, una recepcionista y un veterinario.
3. Registre tres mascotas del propietario; una exótica con sedación. Especie es texto libre.
4. Agende una cita y abra la atención. También se puede atender sin cita.
5. Registre diagnóstico, tratamientos, notas, vacunas y medicamentos.
6. Consulte historial. Revise la vista previa en Caja; no emite ni bloquea la consulta.
7. Emita el cobro. Ahora quedan bloqueados los datos clínicos e insumos.
8. Registre el pago una sola vez y vea el comprobante en pantalla.

La cuenta suma tarifa base, recargo exótico del 25%, sedación si corresponde e insumos. Si el propietario tiene más de dos mascotas registradas, descuenta 10% del subtotal. Los valores se fijan al emitir el cobro y se redondean a dos decimales.

Los avisos y validaciones de entrada están en controlador/ventana. Las entidades conservan las restricciones de su estado (por ejemplo, una consulta con cobro no puede editarse). Buscar dentro de las colecciones de una entidad sigue siendo responsabilidad de esa entidad.

No incluye base de datos, autenticación, servicios, pruebas dentro del proyecto, exportación TXT ni una interfaz VistaClinica adicional. Los registros son de la jornada en memoria. Cerrar todo el programa los elimina. Nuevo limpia el formulario, no borra los datos registrados.

En Design de NetBeans puede modificar cada ventana por separado. No modifique manualmente los bloques Generated Code desde el editor de NetBeans: use Design para esos componentes.

Esta revisión corrige los grupos raíz de los nueve archivos .form para que el diseñador no superponga los paneles. Cierre el proyecto anterior y abra esta copia descomprimida en otra carpeta; así evita seguir viendo formularios antiguos abiertos en el editor. La lógica de los modelos y controladores no cambió.
