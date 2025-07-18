
package eft_s9_fernando_fuentes.drivequest.interfaces;

import eft_s9_fernando_fuentes.drivequest.modelos.VehiculoCarga;
import eft_s9_fernando_fuentes.drivequest.modelos.VehiculoPasajeros;

public interface CalculadorImpuestos {
    double IVA = 0.19;
    double DESCUENTO_CARGA = 0.07;
    double DESCUENTO_PASAJEROS = 0.12;
    
    default void calcularYMostrarBoleta(double montoBase, int diasArriendo){
        double subtotal = montoBase * diasArriendo;
        double descuento = 0;
        double montoIVA = subtotal * IVA;
        
        if(diasArriendo >= 7){
            if(this instanceof VehiculoCarga){
                descuento = subtotal * DESCUENTO_CARGA;                
            } else if(this instanceof VehiculoPasajeros){
                descuento = subtotal * DESCUENTO_PASAJEROS;
            }
        }
        
        double total = subtotal + montoIVA - descuento;
        
        System.out.println("\n  --- Detalle de Boleta ---");
        System.out.println("Subtotal:  $" + String.format("%.2f", subtotal));
        System.out.println("IVA (19%): $" + String.format("%.2f", montoIVA));
        System.out.println("Descuento: $" + String.format("%.2f", descuento));
        System.out.println("TOTAL:     $" + String.format("%.2f", total));
        System.out.println("  ------------------------\n");
    }
}
