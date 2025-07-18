
package eft_s9_fernando_fuentes.drivequest.modelos;

public class VehiculoPasajeros extends Vehiculo {
    private int maximoPasajeros;
    
    public VehiculoPasajeros(){
        super();
    }
    
    public VehiculoPasajeros(String patente, String marca, String modelo, int a�o, double tarifaDiaria, int diasArriendo, int maximoPasajeros){
        super(patente, marca, modelo, a�o, tarifaDiaria, diasArriendo);
        this.maximoPasajeros = maximoPasajeros;
    }
    
    @Override
    public void mostrarDatosVehiculo(){
        System.out.println("Vehiculo de Pasajeros - " + super.toString() + ", M�ximo de pasajeros: " + maximoPasajeros);
    }
    
    public int getMaximoPasajeros(){
        return maximoPasajeros;
    }
    
    public void setMaximoPasajeros(int maximoPasajeros){
        this.maximoPasajeros = maximoPasajeros;
    }
    
}
