package TrabajoPractico7;
import java.util.concurrent.locks.*;
public class proyectoSoftware {
    private int cantidadNotebooks;
    private int cantidadLibros;
    private int notebooksEnUso;
    private int librosEnUso;
    private final Lock mutex = new ReentrantLock();
    private final Condition esperaLibro = mutex.newCondition();
    private final Condition esperaNotebook = mutex.newCondition();

    public proyectoSoftware() {
        cantidadNotebooks = 5;
        cantidadLibros = 5;
        notebooksEnUso = 0;
        librosEnUso = 0;
    }

    public void usarNotebook() throws InterruptedException {
        mutex.lock();
        while(notebooksEnUso >= cantidadNotebooks) {
            esperaNotebook.await();
        }
        System.out.println(Thread.currentThread().getName() + " esta usando una notebook");
        notebooksEnUso++;
        mutex.unlock();
    }

    public void usarLibro() throws InterruptedException {
        mutex.lock();
        while(librosEnUso >= cantidadLibros) {
            esperaLibro.await();
        }
        System.out.println(Thread.currentThread().getName() + " esta usando un libro");
        librosEnUso++;
        mutex.unlock();
    }

    public void dejarNotebook(){
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " deja una notebook");
        notebooksEnUso--;
        esperaNotebook.signal();
        mutex.unlock();
    }

    public void dejarLibro(){
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " deja un libro");
        librosEnUso--;
        esperaLibro.signal();
        mutex.unlock();
    }
}
