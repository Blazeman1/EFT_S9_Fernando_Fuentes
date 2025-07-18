
package eft_s9_fernando_fuentes.drivequest.modelos;

import eft_s9_fernando_fuentes.drivequest.interfaces.CalculadorImpuestos;
import java.io.Serializable;

public abstract class Vehiculo implements CalculadorImpuestos, Serializable {
    private String patente;
    private String marca;
    private String modelo;
    private int a�o;
    private double tarifaDiaria;
    private int diasArriendo;
    
    // Contructores
    public Vehiculo(){
        
    }
    
    public Vehiculo(String patente, String marca, String modelo, int a�o, double tarifaDiaria, int diasArriendo){
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.a�o = a�o;
        this.tarifaDiaria = tarifaDiaria;
        this.diasArriendo = diasArriendo;
    }
    
    // M�todo abstracto para mostrar datos espec�ficos
    public abstract void mostrarDatosVehiculo();
    
    // Getters y Setters
    public String getPatente(){
        return patente;
    }
    
    public void setPatente(String patente){
        this.patente = patente;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public int getA�o(){
        return a�o;
    }
    
    public void setA�o(int a�o){
        this.a�o = a�o;
    }
    
    public double getTarifaDiaria(){
        return tarifaDiaria;
    }
    
    public void setTarifaDiaria(double tarifaDiaria){
        this.tarifaDiaria = tarifaDiaria;
    }
    
    public int getDiasArriendo(){
        return diasArriendo;
    }
    
    public void setDiasArriendo(int diasArriendo){
        this.diasArriendo = diasArriendo;
    }
    
    @Override
    public String toString(){
        return "Patente: " + patente + ", Marca: " + marca + ", Modelo: " + modelo + ", A�o: " + a�o + ", Tarifa diaria: " + tarifaDiaria + ", D�as de arriendo: " + diasArriendo;
    }
    
}
