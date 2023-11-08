package com.empleadosjpa.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleados")
public class Empleados implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private String fechaInicio;
    
    //Contructores

    public Empleados() {
    }

    public Empleados(int id, String nombre, String apellido, String cargo, double salario, String fechaInicio) throws Exception{
        if(nombre.equals("") || nombre.isEmpty() || nombre == null){
            throw new Exception ("Debe introducir el nombre del empleado");
        }else if(apellido.equals("") || apellido.isEmpty() || apellido == null){
            throw new Exception ("Debe introducir el apellido del empleado");
        }else if(cargo.equals("") || cargo.isEmpty() || cargo == null){
            throw new Exception ("Debe introducir el cargo del empleado");
        }else if(salario == 0 || cargo.isEmpty()){
            throw new Exception ("Debe introducir el salario del empleado");
        }else if(fechaInicio.equals("") || fechaInicio.isEmpty() || fechaInicio == null){
            throw new Exception ("Debe introducir la fecha de inicio del empleado");
        }else{
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.cargo = cargo;
            this.salario = salario;
            this.fechaInicio = fechaInicio;
        }
        
    }
    
    //Métodos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Empleados{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cargo=" + cargo + ", salario=" + salario + ", fechaInicio=" + fechaInicio + '}';
    }
}
