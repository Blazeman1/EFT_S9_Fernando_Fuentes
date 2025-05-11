/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eft_s9_fernando_fuentes;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;


/**
 *
 * @author ffuen
 */
public class EFT_S9_Fernando_Fuentes {
    // Variables Globales
    static int entradasVendidas = 0;
    static int numeroEntrada = 0;
    static List<Integer> precios = List.of(30000, 25000, 20000, 15000, 10000); // precios de cada ubicacion global
    static List<Double> desc = List.of(0.9, 0.8 , 0.85 , 0.75); // descuentos asociados por edad y genero
    static LinkedList<String> proximosEventos = new LinkedList<>(); // Próximos eventos utilizando LinkedList
    
    // Id de clientes, y Ubicación Global
    // utilizo arrays simples ya que la disponibilidad de asientos en el teatro no son dinámicos.
    static int[] idVip = new int[10];
    static int[] idPal = new int[10];
    static int[] idBaj = new int[10];
    static int[] idAlt = new int[10];
    static int[] idGal = new int[10];
    
    // Asiento de clientes, ubicación particular
    static int[] asVip = new int[10];
    static int[] asPal = new int[10];
    static int[] asBaj = new int[10];
    static int[] asAlt = new int[10];
    static int[] asGal = new int[10];
    
    // Edad del cliente
    static int[] edVip = new int[10];
    static int[] edPal = new int[10];
    static int[] edBaj = new int[10];
    static int[] edAlt = new int[10];
    static int[] edGal = new int[10];
    
    // Genero de cliente
    static int[] genVip = new int[10];
    static int[] genPal = new int[10];
    static int[] genBaj = new int[10];
    static int[] genAlt = new int[10];
    static int[] genGal = new int[10];
    
    // Precios de clientes
    static double[] preVip = new double[10];
    static double[] prePal = new double[10];
    static double[] preBaj = new double[10];
    static double[] preAlt = new double[10];
    static double[] preGal = new double[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String teatro = "Teatro Moro";
        boolean continuar = true;
        proximosEventos.add("La Negra Ester - 17 Mayo 2025");
        proximosEventos.add("Concierto de Jazz - 24 Mayo 2025");
        proximosEventos.add("Piano Concerto - 31 Mayo 2025");
        
        // Menú Principal, incluyendo CRUD
        while(continuar){
            System.out.println("\n *** Bienvenido al " + teatro + " ***");
            System.out.println("\n   ====== Menú Principal ======");
            System.out.println("    1. Crear nueva reserva");
            System.out.println("    2. Ver reservas actuales");
            System.out.println("    3. Actualizar reserva");
            System.out.println("    4. Eliminar reserva");
            System.out.println("    5. Salir");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = sc.nextInt();
            
            switch(opcion){
                case 1 -> {
                    escenario();
                    seleccionAsiento();
                }
                case 2 -> mostrarReservas();
                case 3 -> actualizarReserva();
                case 4 -> eliminarReserva();
                case 5 -> {
                    continuar = false;
                    resumenFinal();
                    System.out.println("Gracias por usar el sistema de Reservas del " + teatro);
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    
    public static void resumenFinal(){
        System.out.println("-----------------------------------------");
        System.out.println("\nEntradas Vendidas: " + entradasVendidas);
        int totalVip = 0;
        int totalPal = 0;
        int totalBaj = 0;
        int totalAlt = 0;
        int totalGal = 0;
        for(int i=0; i<10; i++){totalVip +=(int)preVip[i];}
        for(int i=0; i<10; i++){totalPal +=(int)prePal[i];}
        for(int i=0; i<10; i++){totalBaj +=(int)preBaj[i];}
        for(int i=0; i<10; i++){totalAlt +=(int)preAlt[i];}
        for(int i=0; i<10; i++){totalGal +=(int)preGal[i];}
        System.out.println("Recaudación por Ubicación: "); // Revisión con Debug para que la caputa de datos sea correcta
        System.out.println("Vip         : $" + totalVip);
        System.out.println("Palco       : $" + totalPal);
        System.out.println("Platea Baja : $" + totalBaj);
        System.out.println("Platea Alta : $" + totalAlt);
        System.out.println("Galería     : $" + totalGal);
        System.out.println("\nRecaudación Total: $" + (totalVip + totalPal + totalBaj + totalAlt + totalGal)); // revisión con Debug para que se reflejen datos correctamente
        System.out.println("-----------------------------------------");
    }
    
    public static void mostrarProximosEventos(){
        if(proximosEventos.isEmpty()){
            System.out.println("\nNo hay eventos programados por ahora.");
            return;
        }
        System.out.println("\n¡No te pierdas los Próximos Eventos!: ");
        System.out.println("\n    --- Próximos Eventos ---");
        for(String evento: proximosEventos){
            System.out.println("- " + evento);
        }
        System.out.println("    ------------------------");
    }
    
    public static void mostrarReservas(){
        System.out.println("\n          --- Reservas actuales ---");
        for(int i=0; i<10; i++){
            if(idVip[i] != 0)
                System.out.println("Vip         | Asiento " + (i+1) + " | ID: " + idVip[i] + " | Precio: $" + (int)preVip[i]);
            if(idPal[i] != 0)
                System.out.println("Palco       | Asiento " + (i+1) + " | ID: " + idPal[i] + " | Precio: $" + (int)prePal[i]);
            if(idBaj[i] != 0)
                System.out.println("Platea Baja | Asiento " + (i+1) + " | ID: " + idBaj[i] + " | Precio: $" + (int)preBaj[i]);
            if(idAlt[i] != 0)
                System.out.println("Platea Alta | Asiento " + (i+1) + " | ID: " + idAlt[i] + " | Precio: $" + (int)preAlt[i]);
            if(idGal[i] != 0)
                System.out.println("Galería     | Asiento " + (i+1) + " | ID: " + idGal[i] + " | Precio: $" + (int)preGal[i]);
        }
    }
    
    public static void actualizarReserva(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("Ingrese ID de cliente para actualizar: ");
            int id = sc.nextInt();
            sc.nextLine(); // limpiar buffer
        
            int ubic = -1;
            int asien = -1;
            boolean encontrado= false;
            
            for(int i=0; i<10; i++){
            if(idVip[i] == id){ubic = 0; asien = i; encontrado = true; break;}
            if(idPal[i] == id){ubic = 1; asien = i; encontrado = true; break;}
            if(idBaj[i] == id){ubic = 2; asien = i; encontrado = true; break;}
            if(idAlt[i] == id){ubic = 3; asien = i; encontrado = true; break;}
            if(idGal[i] == id){ubic = 4; asien = i; encontrado = true; break;}
            }
        
            if(!encontrado){
                System.out.println("ID no encontrado");
                return;
            }
            
            System.out.println("Actualizando datos del cliente...");
            validacion(ubic, asien); // Reutiliza método para pedir datos
            System.out.println("Reserva actualizada.");
        }catch(InputMismatchException e){ // e incluido varios catch para atrapar cualquier tipo de error que el usuario pueda integrar
            System.out.println("Deber ingresar un número entero válido para el ID.");
            sc.nextLine(); // Limpia buffer para evitar loops infinitos
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Indice de asiento fuera de rango.");
        }catch(Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public static void eliminarReserva(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese ID de cliente a eliminar: ");
        int id = sc.nextInt();
        
        boolean encontrado = false;
        for(int i=0; i<10; i++){
            if(idVip[i] == id){
                idVip[i] = 0; asVip[i] = 0; edVip[i] = 0; genVip[i] = 0; preVip[i] = 0;
                encontrado = true;
                entradasVendidas--;
            }else if(idPal[i] == id){
                idPal[i] = 0; asPal[i] = 0; edPal[i] = 0; genPal[i] = 0; prePal[i] = 0;
                encontrado = true;
                entradasVendidas--;
            }else if(idBaj[i] == id){
                idBaj[i] = 0; asBaj[i] = 0; edBaj[i] = 0; genBaj[i] = 0; preBaj[i] = 0;
                encontrado = true;
                entradasVendidas--;
            }else if(idAlt[i] == id){
                idAlt[i] = 0; asAlt[i] = 0; edAlt[i] = 0; genAlt[i] = 0; preAlt[i] = 0;
                encontrado = true;
                entradasVendidas--;
            }else if(idGal[i] == id){
                idGal[i] = 0; asGal[i] = 0; edGal[i] = 0; genGal[i] = 0; preGal[i] = 0;
                encontrado = true;
                entradasVendidas--;
            }
        }
        
        if(encontrado){
            System.out.println("Reserva eliminada correctamente.");
        }else{
            System.out.println("ID no encontrado.");
        }
    }
    
    public static void seleccionAsiento(){
        Scanner ubicacion = new Scanner(System.in);
        int ubic = -1;
        try{
            System.out.print("Seleccione su ubicación (del 1 al 5):  ");
            ubic = ubicacion.nextInt() - 1;
        }catch(InputMismatchException e){
            System.out.println("Debe ingresar un número válido");
            ubicacion.nextLine(); // limpiar buffer
            return; // termina el método si hubo error
        }
        // validar rango
        if(ubic < 0 || ubic > 4){
            System.out.println("Ubicacion fuera de rago. Intente Nuevamente");
            return;
        }
        
        // Selección de asiento con validación
        Scanner asiento = new Scanner(System.in);
        int asien = -1;
        try{
            System.out.print("Seleccione el asiento de su ubicación (del 1 al 10):  ");
            asien = asiento.nextInt() - 1;
        }catch(InputMismatchException e){
            System.out.println("Debe ingresar un número válido para el asiento");
            asiento.nextLine(); // limpiar buffer
        }
        if(asien < 0 || asien > 9){
            System.out.println("Asiento fuera de rango. Intente Nuevamente.");
            return;
        }     
        
        // Proceso de reserva según ubicación
        switch(ubic){
            case 0 -> {
                if(asVip[asien] != 0){
                    System.out.println("Asiento anteriormente reservado, vuelva a intentar.");
                }else{
                    entradasVendidas++;
                    numeroEntrada++;
                    idVip[asien] = numeroEntrada;
                    asVip[asien] = asien;
                    validacion(ubic, asien);
                    System.out.println("Ubicación Vip, asiento: " + (asien+1) + ", reservado correctamente.");
                    mostrarProximosEventos();
                }
            }
            case 1 ->{
                if(asPal[asien] != 0){
                    System.out.println("Asiento anteriormente reservado, vuelva a intentar.");
                }else{
                    entradasVendidas++;
                    numeroEntrada++;
                    idPal[asien] = numeroEntrada;
                    asPal[asien] = asien;
                    validacion(ubic, asien);
                    System.out.println("Ubicación Platea, asiento: " + (asien+1) + ", reservado correctamente.");
                    mostrarProximosEventos();
                }
            }
            case 2 ->{
                if(asAlt[asien] != 0){
                    System.out.println("Asiento anteriormente reservado, vuelva a intentar.");
                }else{
                    entradasVendidas++;
                    numeroEntrada++;
                    idBaj[asien] = numeroEntrada;
                    asBaj[asien] = asien;
                    validacion(ubic, asien);
                    System.out.println("Ubicación Platea Baja, asiento: " + (asien+1) + ", reservado correctamente.");
                    mostrarProximosEventos();
                }
            }
            case 3 ->{
                if(asBaj[asien] != 0){
                    System.out.println("Asiento anteriormente reservado, vuelva a intentar.");
                }else{
                    entradasVendidas++;
                    numeroEntrada++;
                    idAlt[asien] = numeroEntrada;
                    asAlt[asien] = asien;
                    validacion(ubic, asien);
                    System.out.println("Ubicación Platea Alta, asiento: " + (asien+1) + ", reservado correctamente.");
                    mostrarProximosEventos();
                }
            }
            case 4 ->{
                if(asGal[asien] != 0){
                    System.out.println("Asiento anteriormente reservado, vuelva a intentar.");
                }else{
                    entradasVendidas++;
                    numeroEntrada++;
                    idGal[asien] = numeroEntrada;
                    asGal[asien] = asien;
                    validacion(ubic, asien);
                    System.out.println("Ubicación Galería, asiento: " + (asien+1) + ", reservado correctamente.");
                    mostrarProximosEventos();
                }
            }
        }

    }
    
    public static void validacion(int ubic, int asien){
        Scanner sc = new Scanner(System.in);
        int edad1 = 0;
        char gen = ' ';
        char esEst = ' ';
        
        // Pedir edad con validación
        while(true){
            try{
                System.out.print("\nIndique su edad: ");
                edad1 = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                if(edad1 > 5 && edad1 <= 100){
                    break;
                }else{
                    System.out.println("Edad Inválida. Debe ser mayor que 5 y menor o igual a 100.");
                }
            }catch(InputMismatchException e){
            System.out.println("Debe ingresar una edad válida(número entero).");
            sc.nextLine(); // limpiar buffer
            }
        }
        
        // Pedir género con validación
        while(true) {
            System.out.print("Indique su género (m/f): ");
            String input = sc.nextLine().trim().toLowerCase();
            if(input.length() == 1) {
                char genero = input.charAt(0);
                if(genero == 'm' || genero == 'f') {
                    gen = genero;
            break;
                }
            }
        System.out.println("Error: Debe ingresar solo 'm' o 'f'.");
        }
        
        // Preguntar si es estudiante, con validaciones
        while(true){
            System.out.print("¿Es estudiante? (s/n): ");
            String inputEst = sc.nextLine().trim().toLowerCase();
            if(inputEst.equals("s") || inputEst.equals("n")){
                esEst = inputEst.charAt(0);
                break;
            }
            System.out.println("Entrada inválida, debe ingresar 's' o 'n'.");
        }
        
        double precioBase = precios.get(ubic);
        double descuento = 1.0;
        String tipoDescuento = "Sin descuento";

        // Lógica de descuento
        if (edad1 <= 14) {
            descuento = desc.get(0); // Niño 10%
            tipoDescuento = "Descuento Niño (10%)";
        } else if (edad1 >= 60) {
            descuento = desc.get(3); // Tercera edad 25%
            tipoDescuento = "Descuento Tercera Edad (25%)";
        } else if (gen == 'f') {
            descuento = desc.get(1); // Mujer 20%
            tipoDescuento = "Descuento Mujer (20%)";
        } else if (esEst == 's') {
            descuento = desc.get(2); // Estudiante 15%
            tipoDescuento = "Descuento Estudiante (15%)";
        }

        double precioFinal = precioBase * descuento;

        // Guardar información según ubicación
        switch (ubic) {
            case 0 -> {
                edVip[asien] = edad1;
                genVip[asien] = gen;
                preVip[asien] = precioFinal;
            }
            case 1 -> {
                edPal[asien] = edad1;
                genPal[asien] = gen;
                prePal[asien] = precioFinal;
            }
            case 2 -> {
                edBaj[asien] = edad1;
                genBaj[asien] = gen;
                preBaj[asien] = precioFinal;
            }
            case 3 -> {
                edAlt[asien] = edad1;
                genAlt[asien] = gen;
                preAlt[asien] = precioFinal;
            }
            case 4 -> {
                edGal[asien] = edad1;
                genGal[asien] = gen;
                preGal[asien] = precioFinal;
            }
    }

        // Mostrar boleta
        System.out.println("\nCompra realizada correctamente:");
        System.out.println("----------------------------------");
        System.out.println("ID Cliente: " + numeroEntrada);
        System.out.println("Asiento número: " + (asien + 1));
        System.out.println("Edad: " + edad1);
        System.out.println("Género: " + (gen == 'f' ? "Femenino" : "Masculino"));
        System.out.println("Precio original: $" + precioBase);
        System.out.println(tipoDescuento);
        System.out.println("Precio final: $" + (int)precioFinal);
        System.out.println("----------------------------------");
    }
    
    public static void escenario(){
        System.out.println("");
        System.out.println("    Ubicaciones Disponibles: ");
        System.out.println("");
        System.out.println("                             Escenario");
        System.out.println("                   ------------------------------");
        System.out.print("1 - Vip         :  ");
        int larVip = idVip.length;
        for (int i=0; i<larVip ;i++){
            if(idVip[i] == 0){
                System.out.print("|O|");
            }else{
                System.out.print("|X|");                        
            }
        }
        System.out.println("");
        System.out.print("2 - Palco       :  ");
        int larPal = idPal.length;
        for (int i=0; i<larPal ;i++){
            if(idPal[i] == 0){
                System.out.print("|O|");
            }else{
                System.out.print("|X|");                        
            }
        }
        System.out.println("");
        System.out.print("3 - Platea Baja :  ");
        int larBaj = idBaj.length;
        for (int i=0; i<larBaj ;i++){
            if(idBaj[i] == 0){
                System.out.print("|O|");
            }else{
                System.out.print("|X|");                        
            }
        }
        System.out.println("");
        System.out.print("4 - Platea Alta :  ");
        int larAlt = idAlt.length;
        for (int i=0; i<larAlt ;i++){
            if(idAlt[i] == 0){
                System.out.print("|O|");
            }else{
                System.out.print("|X|");                        
            }
        }
        System.out.println("");
        System.out.print("5 - Galería     :  ");
        int larGal = idGal.length;
        for (int i=0; i<larGal ;i++){
            if(idGal[i] == 0){
                System.out.print("|O|");
            }else{
                System.out.print("|X|");                        
            }
        }
        System.out.println("");
        System.out.println("                   ------------------------------");
        System.out.print("   Asiento ->      ");
        for (int i=1; i<11 ;i++){
            System.out.print("|"+i+"|");
        }
        System.out.println("");
        System.out.println("                   O: disponible      X: Reservado");
        System.out.println("");
    }
}

