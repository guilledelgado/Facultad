package ActividadObligatoria;

public class ClienteBarbero implements Runnable{
    private Sillon sillon;

    public ClienteBarbero(Sillon sillon) {
        this.sillon = sillon;
    }

    public void run() {
        try{
            while(true) {
                sillon.sentarCliente();
                System.out.println("Se sienta el cliente");
                sillon.solicitarCorte();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
