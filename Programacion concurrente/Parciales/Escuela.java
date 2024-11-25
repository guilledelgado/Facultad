package Parciales;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Escuela implements Runnable {
    private int cantAlumnos;
    private ParqueVecinal parque;
    public Escuela(int cantAlumnos, ParqueVecinal parque) {
        this.cantAlumnos = cantAlumnos;
        this.parque = parque;
    }
    public void run() {
        try{
            parque.entraEscuela(cantAlumnos);
            Thread.sleep((int)(((Math.random()*6)+1)*1000));
            parque.saleEscuela(cantAlumnos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
