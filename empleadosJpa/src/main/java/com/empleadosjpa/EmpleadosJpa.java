package com.empleadosjpa;

import com.empleadosjpa.logica.Empleados;
import com.empleadosjpa.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class EmpleadosJpa {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in).useLocale(Locale.US);
        
        String nombre = "";
        String apellido;
        String cargo;
        double salario = 0.0;
        String fechaInicio;
        String saltoLinea; //Se crea esta variable para almacenar los saltos de línea que detecta Scanner tras ingresar un valor numérico, ya que si no, no actúa correctamente el método nextLine()
        boolean seguir = true;
        boolean seguirCreando = true;
        
        ArrayList<Empleados> empleados = new ArrayList<>();
        
        //Creamos la BD
        ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        
        //Solicitamos los datos al usuario para crear los objetos empleados
        System.out.println("**** BIENVENIDO AL SISTEMA DE CONTROL DE EMPLEADOS ****");
        while(seguir == true){
            System.out.println("Indique qué desea realizar: (1: Crear empleados, 2: Listar empleados creados, 3: Actualizar datos de empleados, 4: Eliminar empleados, 5: Buscar empleados por cargo, Otro: Salir del sistema)");
            switch(sc.nextInt()){
                //Se solicitan datos al usuario para la creación del registro de un empleado en la BD
                case 1:
                    saltoLinea = sc.nextLine();
                    System.out.println("Añadir usuarios");
                    while(seguirCreando == true){
                        int id = 1;
                        System.out.println("Introduzca el nombre del empleado: ");
                        nombre = sc.nextLine();
                        System.out.println("Introduzca el apellido del empleado: ");
                        apellido = sc.nextLine();
                        System.out.println("Introduzca el cargo del empleado: ");
                        cargo = sc.nextLine();
                        System.out.println("Introduzca el salario del empleado: ");
                        salario = sc.nextDouble();
                        saltoLinea = sc.nextLine();
                        System.out.println("Introduzca la fecha de inicio del empleado: ");
                        fechaInicio = sc.nextLine();
                        try{
                            empleados.add (new Empleados(id, nombre, apellido, cargo, salario, fechaInicio));
                            id++;
                        }catch(Exception e){
                            System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                        }
                        //Se solicita confirmación sobre si desea introducir más usuarios al sistema o se vuelve al menú principal de la aplicación
                        System.out.println("¿Desea añadir más empleados? (1: Sí)");
                        if(sc.nextInt() != 1)
                            seguirCreando = false;
                        saltoLinea = sc.nextLine();
                    }
                    //Se añaden los empleados introducidos por el usuario a la BD, unificando la inserción de datos en una única sentencia y no en varias, de tal modo que el sistema no recibirá tanta carga de trabajo
                    for (Empleados lista : empleados){
                        controlPersis.crearEmpleado(lista);
                    }
                    break;
                case 2:
                    //Se muestra el listado de empleados que se encuentran actualmente en la BD
                    System.out.println("Mostrar usuarios");
                    //Se realiza un control para que, en caso de no existir registros, se muestre un mensaje de error pero se pueda continuar en la aplicación
                    try{
                        List<Empleados> listaEmpleados = controlPersis.traerEmpleados();
                        System.out.println("**** LISTA DE LOS EMPLEADOS ACTUALES ****");
                        for(Empleados datosEmpleados : listaEmpleados){
                            System.out.println(datosEmpleados.toString());
                        }
                    }catch(Exception e){
                        System.out.println("Se produjo una excepción personalizada: " + e.getMessage()); 
                    }
                    break;
                case 3:
                    //Si el usuario desea actualizar información de algún empleado, inicialmente se comprobará que existen registros en la tabla, y de no ser así, se mostrará un mensaje de error y se le devolverá al menú principal
                    saltoLinea = sc.nextLine();
                    System.out.println("Actualizar usuario");
                    //Se muestra la lista actual de los empleados existentes
                    try{
                        List<Empleados> listaEmpleados = controlPersis.traerEmpleados();
                        System.out.println("**** LISTA DE LOS EMPLEADOS ACTUALES ****");
                        for(Empleados datosEmpleados : listaEmpleados){
                            System.out.println(datosEmpleados.toString());
                        }
                        //Se solicita que se indique el id del empleado que se quiere actualizar
                        int contId = 0;
                        while(contId == 0){
                            System.out.println("Introduzca el Id del empleado que desea actualizar");
                            int id = sc.nextInt();
                            try{
                                Empleados actualizarEmpleado = controlPersis.traerEmpleado(id);
                                //Se muestran los datos del empleado
                                Empleados empleadoNuevo = new Empleados(actualizarEmpleado.getId(), actualizarEmpleado.getNombre(), actualizarEmpleado.getApellido(), actualizarEmpleado.getCargo(), actualizarEmpleado.getSalario(), actualizarEmpleado.getFechaInicio());
                                System.out.println(empleadoNuevo.toString());
                                boolean error = true;
                                //Se controla que introduzca una opción válida, y de no ser así, se mostrará un mensaje de error y se solicitará que la vuelva a introducir
                                while(error){
                                    //Se solicita que confirme el dato que desea modificar sobre el empleado
                                    System.out.println("¿Qué dato desea modificar? (1: Nombre, 2: Apellido, 3: Cargo, 4: Salario, 5: Fecha de inicio)");
                                    switch(sc.nextInt()){
                                        case 1:
                                            saltoLinea = sc.nextLine();
                                            System.out.println("Introduzca el nombre del empleado: ");
                                            nombre = sc.nextLine();
                                            try{
                                                empleadoNuevo = new Empleados(actualizarEmpleado.getId(), nombre, actualizarEmpleado.getApellido(), actualizarEmpleado.getCargo(), actualizarEmpleado.getSalario(), actualizarEmpleado.getFechaInicio());
                                                controlPersis.actualizarEmpleado(empleadoNuevo);
                                                contId++;
                                            }catch(Exception e){
                                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                                            }
                                            error = false;
                                            break;
                                        case 2:
                                            saltoLinea = sc.nextLine();
                                            System.out.println("Introduzca el apellido del empleado: ");
                                            apellido = sc.nextLine();
                                            try{
                                                empleadoNuevo = new Empleados(actualizarEmpleado.getId(), actualizarEmpleado.getNombre(), apellido, actualizarEmpleado.getCargo(), actualizarEmpleado.getSalario(), actualizarEmpleado.getFechaInicio());
                                                controlPersis.actualizarEmpleado(empleadoNuevo);
                                                contId++;
                                            }catch(Exception e){
                                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                                            }
                                            error = false;
                                            break;
                                        case 3:
                                            saltoLinea = sc.nextLine();
                                            System.out.println("Introduzca el cargo del empleado: ");
                                            cargo = sc.nextLine();
                                            try{
                                                empleadoNuevo = new Empleados(actualizarEmpleado.getId(), actualizarEmpleado.getNombre(), actualizarEmpleado.getApellido(), cargo, actualizarEmpleado.getSalario(), actualizarEmpleado.getFechaInicio());
                                                controlPersis.actualizarEmpleado(empleadoNuevo);
                                                contId++;
                                            }catch(Exception e){
                                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                                            }
                                            error = false;
                                            break;
                                        case 4:
                                            saltoLinea = sc.nextLine();
                                            System.out.println("Introduzca el salario del empleado: ");
                                            salario = sc.nextDouble();
                                            try{
                                                empleadoNuevo = new Empleados(actualizarEmpleado.getId(), actualizarEmpleado.getNombre(), actualizarEmpleado.getApellido(), actualizarEmpleado.getCargo(), salario, actualizarEmpleado.getFechaInicio());
                                                controlPersis.actualizarEmpleado(empleadoNuevo);
                                                contId++;
                                            }catch(Exception e){
                                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                                            }
                                            error = false;
                                            break;
                                        case 5:
                                            saltoLinea = sc.nextLine();
                                            System.out.println("Introduzca la fecha de inicio del empleado: ");
                                            fechaInicio = sc.nextLine();
                                            try{
                                                empleadoNuevo = new Empleados(actualizarEmpleado.getId(), actualizarEmpleado.getNombre(), actualizarEmpleado.getApellido(), actualizarEmpleado.getCargo(), actualizarEmpleado.getSalario(), fechaInicio);
                                                controlPersis.actualizarEmpleado(empleadoNuevo);
                                                contId++;
                                            }catch(Exception e){
                                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                                            }
                                            error = false;
                                            break;
                                        default:
                                            System.out.println("ERROR: La opción introducida no es válida");
                                    }
                                }
                            }catch(Exception e){
                                System.out.println("Se produjo una excepción personalizada: " + e.getMessage());
                            }
                        }
                        //Se muestra la lista actualizada de los empleados existentes
                        List<Empleados> listaEmpleadosNew = controlPersis.traerEmpleados();
                        System.out.println("**** LISTA DE LOS EMPLEADOS ACTUALIZADA ****");
                        for(Empleados datosEmpleados : listaEmpleadosNew){
                            System.out.println(datosEmpleados.toString());
                        }
                    }catch(Exception e){
                        System.out.println("Se produjo una excepción personalizada: " + e.getMessage()); 
                    }
                    break;
                case 4:
                    //Si el usuario desea eliminar a algún empleado, inicialmente se comprobará que existen registros en la tabla, y de no ser así, se mostrará un mensaje de error y se le devolverá al menú principal
                    saltoLinea = sc.nextLine();
                    System.out.println("Eliminar usuario");
                    try{
                        //Se muestra la lista actual de los empleados existentes
                        List<Empleados> listaEmpleados = controlPersis.traerEmpleados();
                        System.out.println("**** LISTA DE LOS EMPLEADOS ACTUALES ****");
                        for(Empleados datosEmpleados : listaEmpleados){
                            System.out.println(datosEmpleados.toString());
                        }
                        //Se solicita que se indique el empleado que se quiere eliminar
                        System.out.println("Indique el Id del empleado que desea eliminar");
                        try{
                            controlPersis.eliminarEmpleado(sc.nextInt());
                            //Se muestra la lista actualizada de los empleados existentes
                            List<Empleados> listaEmpleadosNew = controlPersis.traerEmpleados();
                            System.out.println("**** LISTA DE LOS EMPLEADOS ACTUALIZADA ****");
                            for(Empleados datosEmpleados : listaEmpleadosNew){
                                System.out.println(datosEmpleados.toString());
                            }
                        }catch(Exception e){
                            System.out.println("Se produjo una excepción personalizada: " + e.getMessage()); 
                        }
                    }catch(Exception e){
                        System.out.println("Se produjo una excepción personalizada: " + e.getMessage()); 
                    }
                    break;
                case 5:
                    //Se solicita al usuario que introduzca el cargo por el que desea filtrar los empleados existentes en la BD
                    saltoLinea = sc.nextLine();
                    System.out.println("Introduzca el cargo que desea buscar");
                    String filtroCargo = sc.nextLine();
                    while(filtroCargo.isEmpty() || filtroCargo == "" || filtroCargo == null){
                        System.out.println("Debe introducir el cargo que desea filtrar.");
                        filtroCargo = sc.nextLine();
                    }
                    try{
                        List<Empleados> listaEmpleados = controlPersis.traerEmpleados();
                        ArrayList<Empleados> empleadosCargo = new ArrayList<>();
                        for(Empleados filtroEmpleados : listaEmpleados){
                            if(filtroEmpleados.getCargo().equals(filtroCargo))
                                //Se almacenan los registros que coincidan con el cargo introducido en una ArrayList
                                empleadosCargo.add(filtroEmpleados);
                        }
                        //Se controla que, en caso de no existir registros que coincidan con el cargo introducido, muestre un error y devuelva al usuario al menú principal
                        if(empleadosCargo.isEmpty())
                            System.out.println("No se han encontrado empleados con este cargo.");
                        else
                            System.out.println("Empleados con el cargo " + filtroCargo);
                        for(Empleados resultadoFiltro : empleadosCargo){
                            System.out.println(resultadoFiltro.toString());
                        }
                    }catch(Exception e){
                        System.out.println("Se produjo una excepción personalizada: " + e.getMessage()); 
                    }
                    break;
                default:
                    seguir = false;
            }
        }
        System.out.println("Saliendo del sistema");
    }
}