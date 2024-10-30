package TrabajoPractico7;

public class Programador implements Runnable {
    private proyectoSoftware proyecto;
    public Programador(proyectoSoftware proyecto) {
        this.proyecto = proyecto;
    }
    public void run() {
        try{
            while(true){
                proyecto.usarLibro();
                Thread.sleep((long) ((Math.random()*5)*1000));
                proyecto.dejarLibro();
                proyecto.usarNotebook();
                Thread.sleep((long) (Math.random()*5)*1000);
                proyecto.dejarNotebook();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
