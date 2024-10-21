package ActividadObligatoria;

import java.util.concurrent.Semaphore;

public class Barberia {
    private Semaphore semSillon = new Semaphore(1); // No puede iniciar
    private Semaphore semBarbero = new Semaphore(0);
    private Semaphore semCliente = new Semaphore(0);
    private Semaphore semSillasEspera = new Semaphore(10);// generico

    public Barberia(){}

    public void despertarBarbero(){
        //Este metodo lo usa el cliente para despertar al barbero
        semBarbero.release();
    }

    public void aMimir() throws InterruptedException {
        //Este metodo es el que usa el barbero para esperar a los clientes
        semBarbero.acquire();
    }

    public void sentarseSillon() throws InterruptedException {
        //El cliente se sienta en el sillon de corte y deja un lugar en las sillas de espera
        semSillon.acquire();
        semSillasEspera.release();
    }

    public void salirDelSillon(){
        semSillon.release();
    }

    public boolean sentarseSillaEspera(){
        return semSillasEspera.tryAcquire();
    }

    public void elBarberoHaceSuMagia() throws InterruptedException {
        //El cliente espera a que el barbero termine de cortarle el pelo
        semCliente.acquire();
    }
    public void elBarberoYaHizoSuMagia(){
        //El barbero le avisa al cliente de que ya terminó su corte de pelo
        semCliente.release();
    }

}
