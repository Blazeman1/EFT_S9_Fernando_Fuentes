
package eft_s9_fernando_fuentes.drivequest.modelos;

import eft_s9_fernando_fuentes.drivequest.excepciones.VehiculoDuplicadoException;
import eft_s9_fernando_fuentes.drivequest.utilidades.ManejadorArchivos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AdministradorFlota {
    private ConcurrentHashMap<String, Vehiculo> vehiculos;
    private ManejadorArchivos manejadorArchivos;
    
    public AdministradorFlota(){
        vehiculos = new ConcurrentHashMap<>();
        manejadorArchivos = new ManejadorArchivos("vehiculos.dat");
        cargarVehiculos();
    }
    
    public synchronized void agregarVehiculo(Vehiculo vehiculo) throws VehiculoDuplicadoException{
        if(vehiculos.containsKey(vehiculo.getPatente())){
            throw new VehiculoDuplicadoException("Ya existe un vehiculo con la patente " + vehiculo.getPatente());
        }
        vehiculos.put(vehiculo.getPatente(), vehiculo);
        guardarVehiculos();
    }
    
    public List<Vehiculo> listarTodosVehiculos(){
        return new ArrayList<>(vehiculos.values());
    }
    
    public List<Vehiculo> obtenerArriendosLargos(){
        return vehiculos.values().parallelStream().filter(v -> v.getDiasArriendo() >= 7).collect(Collectors.toList());
    }
    
    public int contarArriendosLargos(){
        return obtenerArriendosLargos().size();
    }
    
    private void cargarVehiculos(){
        List<Vehiculo> vehiculosCargados;
        try {
            vehiculosCargados = manejadorArchivos.leerVehiculo();
            vehiculosCargados.forEach(v -> vehiculos.put(v.getPatente(), v));
        } catch (Exception e) {
            System.err.println("Error al cargar vehículos: " + e.getMessage());
        }
    }
    
    private synchronized void guardarVehiculos(){
        try {
            manejadorArchivos.guardarVehiculos(new ArrayList<>(vehiculos.values()));
        } catch (IOException e) {
            System.err.println("Error crítico al guardar vehiculos: " + e.getMessage());
        }
    }
}
