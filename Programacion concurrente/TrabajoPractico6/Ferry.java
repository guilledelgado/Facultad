package TrabajoPractico6;

public class Ferry {
    private int capacidadMaxima;
    private int capacidadActual = 0;
    private int capacidadAutos;
    private boolean enDestino = false;
    private boolean enPuerto = true;

    public Ferry(int capacidadMaxima, int capacidadAutos) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadAutos = capacidadAutos;
    }

    public synchronized void subePersonaFerry() throws InterruptedException {
        while (capacidadActual > capacidadMaxima || !enPuerto) {
            this.wait();
        }
        System.out.println("Sube un pj");
        capacidadActual++;
    }

    public synchronized void bajaPersonaFerry() throws InterruptedException {
        while (!enDestino) {
            this.wait();
        }
        System.out.println("Baja un pj");
        capacidadActual--;
        this.notifyAll();
    }

    public synchronized void subeAutoFerry() throws InterruptedException {
        while (capacidadActual + capacidadAutos > capacidadMaxima || !enPuerto){
            this.wait();
        }
        System.out.println("Sube un coche");
        capacidadActual+=capacidadAutos;
    }

    public synchronized void bajaAutoFerry() throws InterruptedException {
        while (!enDestino) {
            this.wait();
        }
        System.out.println("Baja un coche");
        capacidadActual-=capacidadAutos;
        this.notifyAll();
    }

    public synchronized void esperaFerry() throws InterruptedException {
        while (capacidadActual < capacidadMaxima){
            this.wait();
        }
        System.out.println("El Ferry parte hacia el destino");
    }

    public synchronized void llegaFerry() throws InterruptedException {
        enDestino = true;
        enPuerto = false;
        System.out.println("El Ferry está en el destino");
        this.notifyAll();
        while (capacidadActual > 0){
            this.wait();
        }
        enDestino = false;
        System.out.println("El Ferry parte hacia el puerto");
    }

    public synchronized void ferryEnPuerto(){
        enPuerto = true;
        System.out.println("El Ferry está en el puerto");
        this.notifyAll();
    }
}
