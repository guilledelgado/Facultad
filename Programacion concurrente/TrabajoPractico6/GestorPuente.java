package TrabajoPractico6;

import lineales.dinamicas.Cola;

public class GestorPuente {
    private int cantMaxima;
    private int cuantosPasaron = 0;
    private int autosActivos = 0;
    private String sentido;
    private Cola colaSur;
    private Cola colaNorte;

    public GestorPuente(int max) {
        this.cantMaxima = max;
        this.sentido = "";
        this.colaSur = new Cola();
        this.colaNorte = new Cola();
    }

    public synchronized void EntrarColaNorte(String auto){
        colaNorte.poner(auto);
    }

    public synchronized void EntrarCocheDelNorte(String auto) throws InterruptedException {
        if(sentido.equals("")){
            sentido = "norte";
        } else {
            while ((!colaSur.esVacia() && cuantosPasaron > cantMaxima) || sentido.equalsIgnoreCase("sur") || autosActivos > 0) {
                this.wait();
            }
        }
        System.out.println(auto+" entra al puente desde el norte");
        colaNorte.sacar();
        cuantosPasaron++;
        autosActivos++;
    }

    public synchronized void SalirCocheDelNorte(String auto){
        System.out.println(auto+" sale del punte desde el norte");
        autosActivos--;
        if(autosActivos == 0 && !colaSur.esVacia() && cuantosPasaron >= cantMaxima){
            cuantosPasaron = 0;
            sentido = "sur";
        }
        this.notifyAll();
    }

    public synchronized void EntrarColaSur(String auto){
        colaSur.poner(auto);
    }

    public synchronized void EntrarCocheDelSur(String auto) throws InterruptedException {
        if(sentido.equals("")){
            sentido = "sur";
        } else {
            while((!colaNorte.esVacia() && cuantosPasaron>cantMaxima)|| sentido.equalsIgnoreCase("norte") || autosActivos > 0) {
                this.wait();
            }
        }
        System.out.println(auto+" entra al puente desde el sur");
        colaSur.sacar();
        cuantosPasaron++;
        autosActivos++;
    }

    public synchronized void SalirCocheDelSur(String auto){
        System.out.println(auto+" sale del punte desde el sur");
        autosActivos--;
        if(autosActivos == 0 && !colaSur.esVacia() && cuantosPasaron >= cantMaxima){
            cuantosPasaron = 0;
            sentido = "norte";
        }
        this.notifyAll();
    }
}
