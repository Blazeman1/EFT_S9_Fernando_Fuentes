
package eft_s9_fernando_fuentes.drivequest.utilidades;

import eft_s9_fernando_fuentes.drivequest.excepciones.DatosVehiculoIncompletosException;
import eft_s9_fernando_fuentes.drivequest.modelos.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String SEPARADOR = "|";
    private final String rutaArchivo;
    
    public ManejadorArchivos(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }

    public List<Vehiculo> leerVehiculo(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        
        if(!archivo.exists()){
            return vehiculos;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = br.readLine()) != null){
                try {
                    Vehiculo vehiculo = parsearLinea(linea);
                    if(vehiculo != null){
                        vehiculos.add(vehiculo);
                    }
                } catch (DatosVehiculoIncompletosException e) {
                    System.err.println("Error al parsear linea:" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
        }
        return vehiculos;
    }
    
    public void guardarVehiculos(List<Vehiculo> vehiculos) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){
            for(Vehiculo vehiculo: vehiculos){
                String linea = convertirAVehiculoALinea(vehiculo);
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error al guardar vehículos: " + e.getMessage());
        }
    }
    
    private Vehiculo parsearLinea(String linea) throws DatosVehiculoIncompletosException{
        String[] partes = linea.split("\\" + SEPARADOR);
        
        if(partes.length < 8){
            throw new DatosVehiculoIncompletosException("Línea incompleta: " + linea);
        }
        
        try {
            String tipo = partes[0];
            String patente = partes[1];
            String marca = partes[2];
            String modelo = partes[3];
            int año = Integer.parseInt(partes[4]);
            double tarifaDiaria = Double.parseDouble(partes[5]);
            int diasArriendo = Integer.parseInt(partes[6]);
            
            switch(tipo){
                case "CARGA":
                    double capacidadCarga = Double.parseDouble(partes[7]);
                    return new VehiculoCarga(patente, marca, modelo, año, tarifaDiaria, diasArriendo, capacidadCarga);
                case "PASAJEROS":
                    int maxPasajeros = Integer.parseInt(partes[7]);
                    return new VehiculoPasajeros(patente, marca, modelo, año, tarifaDiaria, diasArriendo, maxPasajeros);
                default:
                    throw new DatosVehiculoIncompletosException("Tipo de vehículo desconocido: " + tipo);
            }
        } catch (NumberFormatException e) {
            throw new DatosVehiculoIncompletosException("Fromato numérico inválido en línea: " + linea, e);
        }
    }
    
    private String convertirAVehiculoALinea(Vehiculo vehiculo){
        StringBuilder sb = new StringBuilder();
        
        if(vehiculo instanceof VehiculoCarga){
            VehiculoCarga vc = (VehiculoCarga) vehiculo;
            sb.append("CARGA").append(SEPARADOR)
              .append(vc.getPatente()).append(SEPARADOR)
              .append(vc.getMarca()).append(SEPARADOR)
              .append(vc.getModelo()).append(SEPARADOR)
              .append(vc.getAño()).append(SEPARADOR)
              .append(vc.getTarifaDiaria()).append(SEPARADOR)
              .append(vc.getDiasArriendo()).append(SEPARADOR)
              .append(vc.getCapacidadCarga());
        } else if(vehiculo instanceof VehiculoPasajeros){
            VehiculoPasajeros vp = (VehiculoPasajeros) vehiculo;
            sb.append("PASAJEROS").append(SEPARADOR)
              .append(vp.getPatente()).append(SEPARADOR)
              .append(vp.getMarca()).append(SEPARADOR)
              .append(vp.getModelo()).append(SEPARADOR)
              .append(vp.getAño()).append(SEPARADOR)
              .append(vp.getTarifaDiaria()).append(SEPARADOR)
              .append(vp.getDiasArriendo()).append(SEPARADOR)
              .append(vp.getMaximoPasajeros());
        }
        return sb.toString();
    }
}
