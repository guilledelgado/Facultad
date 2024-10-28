package TrabajoPractico6;

public class SalaEstudio {
    private int mesas;
    private int mesasUsadas;

    public SalaEstudio(int mesas) {
        this.mesas = mesas;
        this.mesasUsadas = 0;
    }

    public synchronized void ocuparMesa() throws InterruptedException {
        while(mesasUsadas > mesas){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ": ocupa una mesa");
        mesasUsadas++;
    }

    public synchronized void desocuparMesa() {
        System.out.println(Thread.currentThread().getName() + ": desocupa una mesa");
        mesasUsadas--;
        this.notify();
    }
}
