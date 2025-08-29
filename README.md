Creamos API para la gestion de vuelos, tenemos implantada una clase Vuelo que consta de los siguientes atributos: (id, nombreDeVuelo, empresa, lugarSalida, lugarLlegada,
fechaSalida, fechaLlegada). Pero para mostrar los datos del vuelo añadimos un campo adicional que se calcula automáticamente que es la duración del vuelo.

De entrada el sistema carga una lista de 10 que ya están operativos.
La API tiene las siguiente funcionalidades. 
El endpoint GET /vuelos -> nos muestra la lista de vuelos ordenados por fecha de salida. Este endpoint nos permite filtrar por empresa, lugar de llegada y fecha de salida. 
y nos permite ordenar por fecha de salida (opción por defecto) o por lugar de llegada.
Si indicamos en el @RequestParam el valor /vuelos?empresa=Iberia nos filtra por la empresa y en caso de que el valor sea nulo, no lo tiene en cuenta.
Podemos añadir los filtros de lugar de llegada y fecha de salida y los va concatenando. 
Así mismo también podemo añadir un @RequestParam para indicar que queremos ordenar la lista por lugar de llegada, /vuelos?ordenarPor=lugarLleegada.

El siguiente endpoint GET /vuelos/{id} -> nos permite mostrar solo un vuelo introduciendo el id del vuelo. 

Por otra parte tenemos el endpoint POST /vuelos -> que nos permite crear un vuelo introduciendo los campos del la clase Vuelo, a excepción del id, que se calcula automáticamente. 
Para poder crear un vuelo todos los campos son obligatorios (a excepcion del id), la fecha de salida tiene que ser superior a la fecha actual y la fecha de llegada no puede
ser anterior a la fecha de salida. 
 
En el enpoint PUT /vuelos/{id} nos permite actualizar un vuelo introduciendo el id. 

Por último tenemos el endpoint DELETE / vuelos/{id} -> donde nos permite eliminar un vuelo introduciendo el id del vuelo. 
