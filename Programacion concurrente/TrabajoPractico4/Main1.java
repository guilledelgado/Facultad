package TrabajoPractico4;

import java.util.concurrent.Semaphore;

public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
    }
}
