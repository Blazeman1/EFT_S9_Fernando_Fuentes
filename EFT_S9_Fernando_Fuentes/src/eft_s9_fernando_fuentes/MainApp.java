
package eft_s9_fernando_fuentes;

import eft_s9_fernando_fuentes.drivequest.excepciones.VehiculoDuplicadoException;
import eft_s9_fernando_fuentes.drivequest.modelos.AdministradorFlota;
import eft_s9_fernando_fuentes.drivequest.modelos.Vehiculo;
import eft_s9_fernando_fuentes.drivequest.modelos.VehiculoCarga;
import eft_s9_fernando_fuentes.drivequest.modelos.VehiculoPasajeros;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static AdministradorFlota administradorFlota = new AdministradorFlota();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        inicializarDatosPrueba();
        boolean ejecutando = true;
                
        while(ejecutando){
            System.out.println("\n  --- DriveQuest Rentals ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar todos los vehículos");
            System.out.println("3. Mostrar boleta de arriendo");
            System.out.println("4. Mostrar vehículos con arriendo largo (+7 días)");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch(opcion){
                    case 1 -> agregarVehiculo();
                    case 2 -> listarVehiculos();
                    case 3 -> mostrarBoleta();
                    case 4 -> mostrarArriendosLargos();
                    case 5 -> ejecutando = false;
                    default -> System.out.println("Opción no válida");
                }
            } catch(NumberFormatException e){
                System.out.println("Por favor, ingrese un número válido.");
            } catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
        
        System.out.println("Sistema cerrado. !Vuelva pronto!");
        scanner.close();
    }
    
    private static void inicializarDatosPrueba(){
        try {
            Vehiculo[] vehiculosPrueba = {
                new VehiculoCarga("TEST01", "Mercedes", "Actros", 2021, 180.0, 10, 15.0),
                new VehiculoPasajeros("TEST02", "Toyota", "Hiace", 2022, 120.0, 5, 12)
            };
            
            for(Vehiculo v: vehiculosPrueba){
                administradorFlota.agregarVehiculo(v);
            }
        } catch (VehiculoDuplicadoException e) {
            System.err.println("Error en datos de prueba: " + e.getMessage());
        }
    }
    
    private static void agregarVehiculo(){
        try {
            System.out.println("\nTipo de vehículo: ");
            System.out.println("1. Vehículo de carga");
            System.out.println("2. Vehículo de pasajeros");
            System.out.print("Seleccione: ");
            int tipo = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Patente: ");
            String patente = scanner.nextLine();
            
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            
            System.out.print("Año: ");
            int año = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Tarifa diaria: ");
            double tarifaDiaria = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Días de arriendo: ");
            int diasArriendo = Integer.parseInt(scanner.nextLine());
            
            Vehiculo vehiculo;
            
            if(tipo == 1){
                System.out.println("Capacidad de carga (toneladas): ");
                double capacidadCarga = Double.parseDouble(scanner.nextLine());
                vehiculo = new VehiculoCarga(patente, marca, modelo, año, tarifaDiaria, diasArriendo, capacidadCarga);
            } else{
                System.out.print("Máximo de pasajeros: ");
                int maximoPasajeros = Integer.parseInt(scanner.nextLine());
                vehiculo = new VehiculoPasajeros(patente, marca, modelo, año, tarifaDiaria, diasArriendo, maximoPasajeros);
            }
            
            administradorFlota.agregarVehiculo(vehiculo);
            System.out.println("Vehículo agregado exitosamente!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un valor numérico válido");
        } catch (VehiculoDuplicadoException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void listarVehiculos(){
        List<Vehiculo> vehiculos = administradorFlota.listarTodosVehiculos();
        if(vehiculos.isEmpty()){
            System.out.println("\nNo hay vehículos registrados.");
            return;
        }
        
        System.out.println("\n --- Lista de Vehículos ---");
        vehiculos.forEach(Vehiculo::mostrarDatosVehiculo);
    }
    
    private static void mostrarBoleta(){
        System.out.print("\nIngrese la patente del vehículo: ");
        String patente = scanner.nextLine();
        
        List<Vehiculo> vehiculos = administradorFlota.listarTodosVehiculos();
        Vehiculo vehiculo = vehiculos.stream().filter(v -> v.getPatente().equalsIgnoreCase(patente)).findFirst().orElse(null);
        
        if(vehiculo == null){
            System.out.println("No se encontró un vehiculo con esa patente.");
            return;
        }
        
        vehiculo.calcularYMostrarBoleta(vehiculo.getTarifaDiaria(), vehiculo.getDiasArriendo());
    }
    
    private static void mostrarArriendosLargos(){
        List<Vehiculo> vehiculosLargos = administradorFlota.obtenerArriendosLargos();
        int cantidad = administradorFlota.contarArriendosLargos();
        
        System.out.println("\n --- Vehículos con arriendo largo (+7 días) ---");
        System.out.println("Cantidad: " + cantidad);
        
        if(!vehiculosLargos.isEmpty()){
            vehiculosLargos.forEach(Vehiculo::mostrarDatosVehiculo);
        }
    }
}
