package TrabajoPractico2;

public class ThreadEjemploRunnable implements Runnable{

    public void run() {
        for (int i = 0; i < 10 ; i++){
        System.out.println(i + " " + Thread.currentThread().getName());
        
    }
    System.out.println("Termina thread " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadEjemploRunnable runnable1 = new ThreadEjemploRunnable();
        ThreadEjemploRunnable runnable2 = new ThreadEjemploRunnable();

        Thread hilo1 = new Thread(runnable1, "Maria Jose");
        Thread hilo2 = new Thread(runnable2, "Jose Maria");

        hilo1.start();
        hilo2.start();
    }
}