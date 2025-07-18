
package eft_s9_fernando_fuentes.drivequest.excepciones;

public class DatosVehiculoIncompletosException extends Exception{
    public DatosVehiculoIncompletosException(String mensaje){
        super(mensaje);
    }
    
    public DatosVehiculoIncompletosException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
