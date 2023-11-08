# CuencaOscar_pruebatec1
**** SISTEMA DE GESTIÓN DE EMPLEADOS ****

Se trata de una aplicación que permite al usuario registrar nuevos empleados en la BD empresa, en la tabla empleados.
Esta se generará de forma automática, si no está ya creada, al iniciar la aplicación.
La BD tiene por usuario de acceso "root", y este no tiene contraseña.

La aplicación permite al usuario, desde un menú principal, realizar 6 acciones distintas a través del valor numérico que introduzca:

  1: Puede añadir registros de empleados a la tabla empleados de la BD empresa.
  Al acceder a esta opción, se solicitará al usuario que introduzca los datos necesarios para tramitar el registro de dicho empleado en la BD.
  Al finalizar el registro, Main llama a la clase Empleados, la cual comprueba que todos los datos sean correctos, y de ser así, crea el objeto 
  y lo almacena en una lista creada previamente para almacenar todos los empleados que se deseen crear antes de registrarlos en la BD.
  Tras comprobar que los datos son correctos, la aplicación mostrará un mensaje de error, indicando el datos erróneo que se ha detectado, o 
  indicará que se ha creado correctamente y solicitará confirmación sobre si desea añadir más empleados.
  En caso de no querer añadir más, se llamará al método crearEmpleado de la clase ControladoraPersistencia, al que se le pasarán los empleados
  almacenados en la lista, y este los creará como registros en la BD.

  2: Puede mostrar los registros que se encuentran actualmente en la tabla empleados de la BD.
  Al acceder a esta opción, el Main llamará al método traerEmpleados que se encuentra en ControladoraPersistencia, la cual comprueba si existen
  registros en la tabla.
  Si no existen registros actualmente, devolverá un mensaje de error, indicando que la tabla está vacía, pero si no, devolverá los registros de 
  todos los empleados registrados hasta el momento, mostrando, sus datos a través del método toString de la clase Empleados.

  3: Puede actualizar datos del registro empleado que indique.
  Al acceder a esta opción, se verificará si existen registros previamente en la tabla, al igual que se realizaba en la opción anterior. En caso 
  de no existir, mostrará un error indicándolo, pero en caso contrario, mostrará el listado de los empleados registrados hasta el momento, y 
  solicitará que se indique el id del empleado que se desea modificar.
  Tras indicar el id, se comprobará que este exista en la tabla, llamando al método traerEmpleado, que se encuentra en la clase 
  ControladoraPersistencia, en el cual se verifican todos los registros existentes en la tabla empleados, y si no existe ninguno que corresponda
  al id indicado, devolverá un error.
  En caso contrario, devolverá el registro correspondiente a dicho id y se mostrará por pantalla la información del empleado. A continuación, se 
  solicitará al usuario que indique qué campo desea modificar, validandose también que el valor introducido sea correcto. Tras indicar cuál es el 
  campo que desea modificar en dicho registro, se solicitará que introduzca el nuevo valor, y en caso de que sea correcto, se modificará el 
  registro en la BD y se mostrará la información de la tabla empleados actualizada, devolviendo a continuación al usuario al menú principal. Si el 
  valor introducido es erróneo, se mostrará un mensaje deerror y se solicitará de nuevo el id del empleado que desea modificar.

  4: Puede eliminar registros de la tabla empleados de la BD.
  Al acceder a esta opción, se verificará si existen registros previamente en la tabla, al igual que se realizaba en la opción anterior. En caso 
  de no existir, mostrará un error indicándolo, pero en caso contrario, mostrará el listado de los empleados registrados hasta el momento, y 
  solicitará que se indique el id del empleado que se desea eliminar.
  Tras indicar el id, se comprobará que este exista en la tabla, llamando al método traerEmpleado, que se encuentra en la clase 
  ControladoraPersistencia, en el cual se verifican todos los registros existentes en la tabla empleados, y si no existe ninguno que corresponda
  al id indicado, devolverá un error.
  En caso contrario, se eliminará el registro y se mostrará la información de la tabla empleados actualizada, devolviendo a continuación al usuario 
  al menú principal.

  5: Puede buscar empleados en función a su cargo.
  Al acceder a esta opción, se solicitará al usuario que indique el cargo por el que desea filtrar la busqueda en los registros de la tabla 
  empleados. Se recorrerán los registros de la tabla empleados, y los que coincidan en el campo "Cargo" con el introducido por el usaurio, se 
  almacenarán en la lista empladosCargo.
  Al finalizar la búsqueda en la tabla empleados, se comprobará si la lista empleadosCargo está vacía. Si lo está, se mostrará un mensaje de error, 
  en caso contrario, se recorrerá la lista mostrando los datos de todos los registros en los que coincidan con el Cargo indicado.

  6: Si introduce el usuario algún valor distinto a los que se indican en el mensaje que le muestra las opciones disponibles, saldrá de la 
  aplicación de gestión de empleados.

**** Pruebas realizadas ****

Al tratar de acceder al listado de empleados, modificar o eliminar algún registro, si no existen registros en la tabla empleados, devolverá un error
indicándolo.

Al tratar de añadir un empleado en el que falte alguno de los datos introducidos, o el salario sea 0, mostrará un error, indicando cuál es el campo 
erróneo.

Al tratar de modificar o eliminar un empleado con un id que no existe por el momento en la tabla empleados, mostrará un error que indica que dicho 
empleado no existe.

Al tratar de filtrar empleados por un cargo que no existe, devolverá un error que indica que no existen empleados con dicho cargo.
