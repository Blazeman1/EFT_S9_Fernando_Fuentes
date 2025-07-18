
package eft_s9_fernando_fuentes.drivequest.modelos;


public class VehiculoCarga extends Vehiculo {
    private double capacidadCarga; // en toneladas
    
    public VehiculoCarga(){
        super();
    }

    public VehiculoCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public VehiculoCarga(String patente,String marca, String modelo, int año, double tarifaDiaria, int diasArriendo, double capacidadCarga){
        super(patente, marca, modelo, año, tarifaDiaria, diasArriendo);
        this.capacidadCarga = capacidadCarga;
    }
    
    @Override
    public void mostrarDatosVehiculo(){
        System.out.println("Vehículo de carga - " + super.toString() + ", Capacidad de carga: " + capacidadCarga + " toneladas");
    }
    
    public double getCapacidadCarga(){
        return capacidadCarga;
    }
    
    public void setCapacidadCarga(double capacidadCarga){
        this.capacidadCarga = capacidadCarga;
    }
}
