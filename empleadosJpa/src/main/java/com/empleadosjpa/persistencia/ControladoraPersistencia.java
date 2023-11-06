package com.empleadosjpa.persistencia;

import com.empleadosjpa.logica.Empleados;
import com.empleadosjpa.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    EmpleadosJpaController empleadosJPA = new EmpleadosJpaController();
    
    public void crearEmpleado (Empleados empleado){
        empleadosJPA.create(empleado);
    }
    
    public List<Empleados> traerEmpleados (){
        return empleadosJPA.findEmpleadosEntities();
    }
    
    public Empleados traerEmpleado (int id){
        return empleadosJPA.findEmpleados(id);
    }
    
    public void actualizarEmpleado (Empleados empleado){
        try {
            empleadosJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado (int id){
        try {
            empleadosJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
