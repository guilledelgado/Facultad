package TrabajoPractico4;

import java.awt.*;

public class Cliente implements Runnable {
    GestorImpresoras gestor;

    public Cliente(GestorImpresoras gestor) {
        this.gestor = gestor;
    }

    public void run(){
        try{
            gestor.intentarImprimir();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
