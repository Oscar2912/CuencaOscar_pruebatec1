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
    
    public List<Empleados> traerEmpleados () throws Exception{
        if(empleadosJPA.findEmpleadosEntities().isEmpty()){
            throw new Exception ("No existen actualmente empleados en la BD.");
        }else{
            return empleadosJPA.findEmpleadosEntities();
        }  
    }
    
    public Empleados traerEmpleado (int id) throws Exception{
        int empleado = 0;
        for(Empleados buscarEmpleado : traerEmpleados()){
            if(buscarEmpleado.getId() == id){
                empleado++;
            }
        }
        if(empleado > 0){
            return empleadosJPA.findEmpleados(id);
        }else{
            throw new Exception ("No existe este empleado en la BD.");
        }  
    }
    
    public void actualizarEmpleado (Empleados empleado){
        try {
            empleadosJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado (int id) throws Exception{
        int empleado = 0;
        for(Empleados buscarEmpleado : traerEmpleados()){
            if(buscarEmpleado.getId() == id){
                empleado++;
            }
        }
        if(empleado > 0){
            empleadosJPA.destroy(id);
        }else{
            throw new Exception ("No existe este empleado en la BD.");
        }
    }
}
