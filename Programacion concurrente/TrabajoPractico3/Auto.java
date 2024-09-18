package TrabajoPractico3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Auto extends Vehiculo implements Runnable {
    private int capacidadTanque;
    private double capacidadActualTanque;
    private double reserva;
    private Surtidor surtidor;

    public Auto(String marca, String modelo, double km, String patente, int capacidadTanque, Surtidor surtidor) {
        super (marca, modelo, km, patente);
        this.capacidadTanque = capacidadTanque;
        this.capacidadActualTanque = capacidadTanque;
        reserva = (double) capacidadTanque /8;
        this.surtidor = surtidor;
    }

    private boolean andarAuto(double km){
        boolean exito = false;
        double combustibleRequerido = km / 15;// 15 km = 1 l de combustible
        if(combustibleRequerido <= this.capacidadActualTanque){
            capacidadActualTanque -= combustibleRequerido;
            sumarKm(km);
            exito = true;
        }
        return exito;
    }

    private void tanqueLleno(){
        //Si hay combustible en el surtidor y la capacidad actual es menor que el estado de reserva entonces recarga
        double cantidadNecesaria = capacidadTanque - capacidadActualTanque;
        if(reserva >= capacidadActualTanque && surtidor.puedeCargarCombustible(cantidadNecesaria)){
            surtidor.cargarCombustible(cantidadNecesaria);
            capacidadActualTanque = capacidadTanque;
        }
    }

    public void run() {
        try{
            while(capacidadActualTanque > 0){
                double recorrerKm = new Random().nextDouble(capacidadTanque*15);
                if (andarAuto(recorrerKm)){
                    System.out.println(this.getMarca() + " " + this.getModelo() + " recorrió " + recorrerKm +" kms");
                } else {
                    System.out.println(this.getMarca() + " " + this.getModelo() + " necesita cargar combustible");
                    tanqueLleno();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
