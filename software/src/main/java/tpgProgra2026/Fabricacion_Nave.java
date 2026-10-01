package Tp;


public class Fabricacion_Nave {
    
public Nave construirNave(String id, String nombre, String tipo, MotorWarp motor){
    if(tipo.equals("Exploradora"))
        return new Nave_Exploradora(id, nombre, tipo, motor,60,80);

    if(tipo.equals("Carguero"))
        return new Nave_Carguero(id, nombre, tipo, motor,100,60);

    if(tipo.equals("Combate"))
        return new Nave_Combate(id, nombre, tipo, motor,80,100);

    return null;
}
    
}
