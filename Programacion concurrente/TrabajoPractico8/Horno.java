package TrabajoPractico8;

public class Horno implements Runnable {
    private Mostrador mostrador;
    private char tipo;

    public Horno(Mostrador mostrador, char tipo) {
        this.mostrador = mostrador;
        this.tipo = tipo;
    }

    public void run() {
        try {
            while (true) {
                switch (tipo) {
                    case 'A':
                        mostrador.ponerPastel('A');
                        break;
                        case 'B':
                            mostrador.ponerPastel('B');
                            break;
                            case 'C':
                                mostrador.ponerPastel('C');
                                break;
                                default:
                                    break;
                }
                Thread.sleep((((int)(Math.random() * 8))+1)*1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
