package ActividadObligatoria;

public class ClienteBarbero implements Runnable{
    private Barberia barberia;

    public ClienteBarbero(Barberia bar) {
        this.barberia = bar;
    }

    public void run() {
        try{
            if(barberia.sentarseSillaEspera()){
                barberia.sentarseSillon();
                barberia.despertarBarbero();
                barberia.elBarberoHaceSuMagia();
                barberia.salirDelSillon();
            } else {
                System.out.println("No hay lugar disponible");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
